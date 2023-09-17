package swehacker.cqrs.customer.adapters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.commands.AnonymizeCustomerCommand;
import swehacker.cqrs.customer.core.commands.CommandHandler;
import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.core.commands.RestoreReadDbCommand;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.demo.cqrs.handlers.EventSourcingHandler;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerCommandHandler implements CommandHandler {

    private final EventSourcingHandler<CustomerAggregate, UUID> eventSourcingHandler;

    @Override
    public void handle(RegisterCustomerCommand command) {
        var aggregate = new CustomerAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(AnonymizeCustomerCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(RestoreReadDbCommand command) {
        eventSourcingHandler.republishEvents();
    }
}
