package swehacker.cqrs.customer.core.commands;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import swehacker.demo.cqrs.commands.BaseCommand;

@Getter
@SuperBuilder
public class AnonymizeCustomerCommand extends BaseCommand {
    private String reason;
}
