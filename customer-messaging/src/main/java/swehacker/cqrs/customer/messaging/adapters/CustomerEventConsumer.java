package swehacker.cqrs.customer.messaging.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;
import swehacker.cqrs.customer.ports.out.EventHandler;

@RequiredArgsConstructor
@Service
@KafkaListener(topics = "cqrs.demo.events", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
public class CustomerEventConsumer implements EventConsumer {
    private final EventHandler eventHandler;

    @Override
    @KafkaHandler
    public void consume(@Payload CustomerRegisteredEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @Override
    @KafkaHandler
    public void consume(@Payload CustomerAnonymizedEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
}
