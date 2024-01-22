package swehacker.cqrs.customer.messaging.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import swehacker.demo.cqrs.events.BaseEvent;
import swehacker.cqrs.customer.ports.out.EventPublisher;

@Service
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "cqrs.demo.events";

    @Override
    public void publish(BaseEvent event) {
        this.kafkaTemplate.send(TOPIC, event);
    }
}
