package swehacker.cqrs.customer.core.commands;

public interface CommandHandler {
    void handle(RegisterCustomerCommand command);
    void handle(AnonymizeCustomerCommand command);
    void handle(RestoreReadDbCommand command);
}