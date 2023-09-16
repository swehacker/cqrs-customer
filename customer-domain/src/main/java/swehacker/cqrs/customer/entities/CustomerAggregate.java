package swehacker.cqrs.customer.entities;

import swehacker.cqrs.customer.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.events.CustomerRegisteredEvent;
import swehacker.cqrs.customer.vo.Customer;
import swehacker.cqrs.customer.vo.CustomerNo;
import swehacker.demo.cqrs.entities.AggregateRoot;

import java.time.Instant;

public class CustomerAggregate extends AggregateRoot<CustomerNo> {
    private Boolean active = false;

    private Instant removalDate;

    private Customer customer;

    public CustomerAggregate(RegisterCustomerCommand command) {
        raiseEvent(CustomerRegisteredEvent.builder()
                .build());
    }

    public void anonymizeCustomer(String reason) {
        raiseEvent(CustomerAnonymizedEvent.builder()
                .reason(reason)
                .build());
    }

    public void apply(CustomerRegisteredEvent event) {
        this.id = event.getId();
        this.active = true;
    }

    public void apply(CustomerAnonymizedEvent event) {
        this.active = false;
        this.removalDate = Instant.now();
    }
}
