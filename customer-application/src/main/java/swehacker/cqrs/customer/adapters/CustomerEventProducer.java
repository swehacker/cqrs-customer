package swehacker.cqrs.customer.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import swehacker.demo.cqrs.events.BaseEvent;
import swehacker.demo.cqrs.ports.out.EventProducer;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerEventProducer implements EventProducer<UUID> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, BaseEvent<UUID> event) {
        this.kafkaTemplate.send(topic, event);
    }
}
