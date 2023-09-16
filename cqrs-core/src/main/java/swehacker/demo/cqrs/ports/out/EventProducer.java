package swehacker.demo.cqrs.ports.out;


import swehacker.demo.cqrs.events.BaseEvent;

public interface EventProducer<K> {
    void produce(String topic, BaseEvent<K> event);
}
