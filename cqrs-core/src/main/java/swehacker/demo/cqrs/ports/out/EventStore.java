package swehacker.demo.cqrs.ports.out;

import swehacker.demo.cqrs.events.BaseEvent;

import java.util.List;

public interface EventStore<K> {
    void saveEvents(K aggregateId, Iterable<BaseEvent<K>> events, int expectedVersion);
    List<BaseEvent<K>> getEvents(K aggregateId);
    List<K> getAggregateIds();
}
