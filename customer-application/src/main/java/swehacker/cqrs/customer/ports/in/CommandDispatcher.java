package swehacker.cqrs.customer.ports.in;

import swehacker.demo.cqrs.commands.BaseCommand;

public interface CommandDispatcher {
    int size();
    void send(BaseCommand command);
}
