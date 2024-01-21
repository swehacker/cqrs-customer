package swehacker.cqrs.customer.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.demo.cqrs.events.BaseEvent;
import swehacker.demo.cqrs.handlers.EventSourcingHandler;
import swehacker.cqrs.customer.ports.out.EventPublisher;
import swehacker.cqrs.customer.ports.out.EventStore;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerEventSourcingHandler implements EventSourcingHandler<CustomerAggregate> {
    private final EventStore eventStore;
    private final EventPublisher eventProducer;

    @Override
    public void save(CustomerAggregate aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public CustomerAggregate getById(UUID id) {
        var aggregate = new CustomerAggregate();
        List<? extends BaseEvent> events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for(var aggregateId: aggregateIds) {
            var aggregate = getById(aggregateId);
            if (aggregate == null || !aggregate.isActive()) continue;
            var events = eventStore.getEvents(aggregateId);
            for(var event: events) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }
}
