package swehacker.cqrs.customer.ports.out;

import swehacker.demo.cqrs.events.BaseEvent;

import java.util.List;
import java.util.UUID;

public interface EventStore {
    void saveEvents(UUID aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<? extends BaseEvent> getEvents(UUID aggregateId);
    List<UUID> getAggregateIds();
}
