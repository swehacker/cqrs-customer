package swehacker.demo.cqrs.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T> {
    void handle(BaseCommand command);
}
