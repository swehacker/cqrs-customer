package swehacker.cqrs.customer.commands.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.commands.AnonymizeCustomerCommand;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;
import swehacker.demo.cqrs.handlers.EventSourcingHandler;

@Service
@RequiredArgsConstructor
public class AnonymizeCustomerCommandHandler implements CommandHandlerMethod<AnonymizeCustomerCommand> {

    private final EventSourcingHandler<CustomerAggregate> eventSourcingHandler;

    @Override
    public Class<AnonymizeCustomerCommand> getCommandClass() {
        return AnonymizeCustomerCommand.class;
    }

    @Override
    public void handle(BaseCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.anonymizeCustomer(((AnonymizeCustomerCommand) command).getReason());
        eventSourcingHandler.save(aggregate);
    }
}
