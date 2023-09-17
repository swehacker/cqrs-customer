package swehacker.cqrs.customer.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Override
    public void produce(String topic, BaseEvent<UUID> event) {
        try {
            this.kafkaTemplate.send(topic, objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
