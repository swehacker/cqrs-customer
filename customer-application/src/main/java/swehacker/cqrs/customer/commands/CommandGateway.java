package swehacker.cqrs.customer.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.ports.in.CommandDispatcher;
import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandGateway implements CommandDispatcher {

    private final List<CommandHandlerMethod<?>> commandHandlerMethods;

    @Override
    public int size() {
        return commandHandlerMethods.size();
    }

    @Override
    public void send(BaseCommand command) {
        commandHandlerMethods.forEach(handler -> {
            if (command.getClass().equals(handler.getCommandClass())) {
                handler.handle(command);
            }
        });
    }
}
