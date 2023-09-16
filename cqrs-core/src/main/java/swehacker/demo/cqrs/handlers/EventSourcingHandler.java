package swehacker.demo.cqrs.handlers;

public interface EventSourcingHandler<T,K> {
    void save(T aggregate);
    T getById(K id);
    void republishEvents();
}
