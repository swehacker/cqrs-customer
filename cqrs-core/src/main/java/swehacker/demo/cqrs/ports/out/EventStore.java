package swehacker.demo.cqrs.ports.out;

import swehacker.demo.cqrs.events.BaseEvent;

import java.util.List;
import java.util.UUID;

public interface EventStore {
    void saveEvents(UUID aggregateId, Iterable<BaseEvent<UUID>> events, int expectedVersion);
    List<BaseEvent<UUID>> getEvents(UUID aggregateId);
    List<UUID> getAggregateIds();
}
