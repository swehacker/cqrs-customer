package swehacker.cqrs.customer.core.events;

import lombok.Builder;
import swehacker.demo.cqrs.events.BaseEvent;

import java.util.UUID;

@Builder
public class CustomerRegisteredEvent extends BaseEvent<UUID> {
}
