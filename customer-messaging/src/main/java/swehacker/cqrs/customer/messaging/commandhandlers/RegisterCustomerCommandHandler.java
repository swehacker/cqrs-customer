package swehacker.cqrs.customer.messaging.commandhandlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;
import swehacker.demo.cqrs.handlers.EventSourcingHandler;

@Service
@RequiredArgsConstructor
public class RegisterCustomerCommandHandler implements CommandHandlerMethod<RegisterCustomerCommand> {

    private final EventSourcingHandler<CustomerAggregate> eventSourcingHandler;

    @Override
    public Class<RegisterCustomerCommand> getCommandClass() {
        return RegisterCustomerCommand.class;
    }

    @Override
    public void handle(BaseCommand command) {
        var aggregate = new CustomerAggregate((RegisterCustomerCommand) command);
        eventSourcingHandler.save(aggregate);
    }
}
