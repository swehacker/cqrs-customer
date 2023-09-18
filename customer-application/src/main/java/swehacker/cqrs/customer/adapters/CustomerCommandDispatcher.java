package swehacker.cqrs.customer.adapters;

import org.springframework.stereotype.Service;
import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;
import swehacker.demo.cqrs.ports.out.CommandDispatcher;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerCommandDispatcher implements CommandDispatcher {
    private static final int MAX_NUMBER_OF_HANDLERS = 1;
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered!");
        }
        if (handlers.size() > MAX_NUMBER_OF_HANDLERS) {
            throw new RuntimeException("Cannot send command to more than one handler!");
        }
        handlers.get(0).handle(command);
    }
}
