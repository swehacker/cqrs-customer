package swehacker.cqrs.customer.ports.out;


import swehacker.demo.cqrs.events.BaseEvent;

public interface EventPublisher {
    void produce(String topic, BaseEvent event);
}
