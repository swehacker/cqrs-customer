package swehacker.cqrs.customer.commands.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.commands.RestoreReadDbCommand;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;
import swehacker.demo.cqrs.handlers.EventSourcingHandler;

@Service
@RequiredArgsConstructor
public class RestoreReadDbCommandHandler implements CommandHandlerMethod<RestoreReadDbCommand> {

    private final EventSourcingHandler<CustomerAggregate> eventSourcingHandler;

    @Override
    public Class<RestoreReadDbCommand> getCommandClass() {
        return RestoreReadDbCommand.class;
    }

    @Override
    public void handle(BaseCommand command) {
        eventSourcingHandler.republishEvents();
    }
}
