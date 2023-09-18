package swehacker.cqrs.customer.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;

@RequiredArgsConstructor
@Service
public class CustomerEventConsumer implements EventConsumer {
    private final EventHandler eventHandler;

    @Override
    @KafkaListener(topics = "CustomerRegisteredEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload CustomerRegisteredEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @Override
    @KafkaListener(topics = "CustomerAnonymizedEvent", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload CustomerAnonymizedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
}
