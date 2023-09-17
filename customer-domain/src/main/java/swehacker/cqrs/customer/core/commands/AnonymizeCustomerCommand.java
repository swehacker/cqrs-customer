package swehacker.cqrs.customer.core.commands;

import swehacker.demo.cqrs.commands.BaseCommand;

import java.util.UUID;

public class AnonymizeCustomerCommand extends BaseCommand<UUID> {

    public AnonymizeCustomerCommand(UUID id) {
        super.setId(id);
    }
}
