package swehacker.cqrs.customer.core.commands;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import swehacker.cqrs.customer.core.vo.Address;
import swehacker.cqrs.customer.core.vo.Consent;
import swehacker.demo.cqrs.commands.BaseCommand;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Data
public class RegisterCustomerCommand extends BaseCommand {
    String email;
    String mobile;
    String firstName;
    String lastName;
    String preferredName;
    String preferredLanguage;
    String civicNumber;
    LocalDate birthDate;
    List<Address> addresses;
    List<Consent> consents;
}
