package swehacker.cqrs.customer.core.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import swehacker.cqrs.customer.core.vo.Address;
import swehacker.cqrs.customer.core.vo.Consent;
import swehacker.cqrs.customer.core.vo.Email;
import swehacker.cqrs.customer.core.vo.Msisdn;
import swehacker.demo.cqrs.events.BaseEvent;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerRegisteredEvent extends BaseEvent {
    Email email;
    Msisdn mobile;
    String firstName;
    String lastName;
    String preferredName;
    String preferredLanguage;
    String civicNumber;
    LocalDate birthDate;
    List<Address> addresses;
    List<Consent> consents;
}
