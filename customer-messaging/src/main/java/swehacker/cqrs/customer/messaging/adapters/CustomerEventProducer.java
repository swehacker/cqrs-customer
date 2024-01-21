package swehacker.cqrs.customer.messaging.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import swehacker.demo.cqrs.events.BaseEvent;
import swehacker.cqrs.customer.ports.out.EventPublisher;

@Service
@RequiredArgsConstructor
public class CustomerEventProducer implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
}
