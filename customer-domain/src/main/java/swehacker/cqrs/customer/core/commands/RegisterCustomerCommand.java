package swehacker.cqrs.customer.core.commands;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import swehacker.cqrs.customer.core.vo.Customer;
import swehacker.demo.cqrs.commands.BaseCommand;

import java.util.UUID;

@SuperBuilder
@Getter
public class RegisterCustomerCommand extends BaseCommand<UUID> {
    private Customer customer;
}
