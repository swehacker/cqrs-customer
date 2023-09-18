package swehacker.demo.cqrs.ports.out;


import swehacker.demo.cqrs.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
