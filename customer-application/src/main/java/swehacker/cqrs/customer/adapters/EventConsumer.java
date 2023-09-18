package swehacker.cqrs.customer.adapters;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;

public interface EventConsumer {
    void consume(@Payload CustomerRegisteredEvent event, Acknowledgment ack);
    void consume(@Payload CustomerAnonymizedEvent event, Acknowledgment ack);
}
