package swehacker.demo.cqrs.handlers;

import java.util.UUID;

public interface EventSourcingHandler<T> {
    void save(T aggregate);
    T getById(UUID id);
    void republishEvents();
}
