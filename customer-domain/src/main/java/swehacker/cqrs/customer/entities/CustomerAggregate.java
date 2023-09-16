package swehacker.cqrs.customer.entities;

import swehacker.cqrs.customer.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.events.CustomerRegisteredEvent;
import swehacker.cqrs.customer.vo.Customer;
import swehacker.cqrs.customer.vo.CustomerNo;
import swehacker.demo.cqrs.entities.AggregateRoot;

import java.time.Instant;

public class CustomerAggregate extends AggregateRoot<CustomerNo> {
    private Boolean active;

    private Instant removalDate;

    private Customer customer;

    public CustomerAggregate(RegisterCustomerCommand command) {
        raiseEvent(CustomerRegisteredEvent.builder()
                .build());
    }

    public void apply(CustomerRegisteredEvent event) {
        this.id = event.getId();
        this.active = true;
    }
}
