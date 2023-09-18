package swehacker.cqrs.customer.ports.out;

import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
