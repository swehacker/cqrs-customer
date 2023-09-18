package swehacker.cqrs.customer.adapters;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.cqrs.customer.repository.EventModel;
import swehacker.cqrs.customer.repository.EventStoreRepository;
import swehacker.demo.cqrs.events.BaseEvent;
import swehacker.demo.cqrs.exceptions.AggregateNotFoundException;
import swehacker.demo.cqrs.exceptions.ConcurrencyException;
import swehacker.demo.cqrs.ports.out.EventProducer;
import swehacker.demo.cqrs.ports.out.EventStore;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerEventStore implements EventStore {

    private final EventProducer eventProducer;
    private final EventStoreRepository eventStoreRepository;
    private final ObjectMapper objectMapper;

    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void saveEvents(UUID aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        try {
            var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
            if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
                throw new ConcurrencyException();
            }
            var version = expectedVersion;
            for (var event : events) {
                version++;
                event.setVersion(version);
                var eventModel = EventModel.builder()
                        .created(Instant.now())
                        .aggregateIdentifier(aggregateId)
                        .aggregateType(CustomerAggregate.class.getTypeName())
                        .version(version)
                        .eventType(event.getClass().getTypeName())
                        .eventData(objectMapper.writeValueAsString(event))
                        .build();
                var persistedEvent = eventStoreRepository.save(eventModel);
                if (persistedEvent.getId() != -1) {
                    eventProducer.produce(event.getClass().getSimpleName(), event);
                }
            }
        } catch (JacksonException jacksonException) {
            throw new RuntimeException(jacksonException);
        }
    }

    @Override
    public List<? extends BaseEvent> getEvents(UUID aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect Customer ID provided!");
        }

        return (List<? extends BaseEvent>) eventStream.stream()
                .map(event -> {
                    try {
                        return objectMapper.readValue(event.getEventData(), Class.forName(event.getEventType()));
                    } catch (JsonProcessingException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    @Override
    public List<UUID> getAggregateIds() {
        var eventStream = eventStoreRepository.findAll();
        if (eventStream.isEmpty()) {
            throw new IllegalStateException("Could not retrieve event stream from the event store!");
        }
        return eventStream.stream()
                .map(EventModel::getAggregateIdentifier)
                .distinct()
                .toList();
    }
}
