package swehacker.cqrs.customer.api.rest.dto;

import swehacker.cqrs.customer.core.vo.Address;
import swehacker.cqrs.customer.core.vo.Consent;

import java.time.LocalDate;
import java.util.List;

public record RegisterCustomerDto(
        String email,
        String mobile,
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