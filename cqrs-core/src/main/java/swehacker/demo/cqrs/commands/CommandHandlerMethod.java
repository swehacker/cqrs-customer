package swehacker.demo.cqrs.commands;

public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(BaseCommand command);
    Class<T> getCommandClass();
}
