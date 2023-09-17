package swehacker.demo.cqrs.ports.out;

import swehacker.demo.cqrs.commands.BaseCommand;
import swehacker.demo.cqrs.commands.CommandHandlerMethod;

import java.util.UUID;

public interface CommandDispatcher {
    <T extends BaseCommand<UUID>> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
