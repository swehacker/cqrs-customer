package swehacker.cqrs.customer.core.events;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import swehacker.cqrs.customer.core.vo.Customer;
import swehacker.demo.cqrs.events.BaseEvent;

import java.util.UUID;

@SuperBuilder
@Getter
public class CustomerRegisteredEvent extends BaseEvent<UUID> {
    private Customer customer;
}
