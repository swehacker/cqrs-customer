package swehacker.cqrs.customer.core.vo;

import java.time.LocalDate;
import java.util.List;

public record Customer(
        CustomerNo customerNo,
        Email email,
        Msisdn mobile,
        String firstName,
        String lastName,
        String preferredName,
        String preferredLanguage,
        String civicNumber,
        LocalDate birthDate,
        List<Address> addresses,
        List<Consent> consents
) {
}
