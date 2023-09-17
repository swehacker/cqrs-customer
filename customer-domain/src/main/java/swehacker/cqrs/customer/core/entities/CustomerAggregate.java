package swehacker.cqrs.customer.core.entities;

import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;
import swehacker.cqrs.customer.core.vo.Customer;
import swehacker.demo.cqrs.entities.AggregateRoot;

import java.time.Instant;
import java.util.UUID;

public class CustomerAggregate extends AggregateRoot<UUID> {
    private Boolean active = false;

    private Instant removalDate;

    private Customer customer;

    public CustomerAggregate() {
        super();
        super.setVersion(-1);
    }

    public CustomerAggregate(RegisterCustomerCommand command) {
        raiseEvent(CustomerRegisteredEvent.builder()
                .id(command.getId())
                .customer(command.getCustomer())
                .build());
    }

    public boolean isActive() {
        return this.active;
    }

    public void anonymizeCustomer(String reason) {
        raiseEvent(CustomerAnonymizedEvent.builder()
                .reason(reason)
                .build());
    }

    public void apply(CustomerRegisteredEvent event) {
        this.id = event.getId();
        this.customer = event.getCustomer();
        this.active = true;
    }

    public void apply(CustomerAnonymizedEvent event) {
        this.active = false;
        this.removalDate = Instant.now();
    }
}
