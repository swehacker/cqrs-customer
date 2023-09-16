package swehacker.demo.cqrs.handlers;

import swehacker.demo.cqrs.entities.AggregateRoot;

public interface EventSourcingHandler<T,K> {
    void save(AggregateRoot<K> aggregate);
    T getById(K id);
    void republishEvents();
}
