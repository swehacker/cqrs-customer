package swehacker.cqrs.customer.api.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import swehacker.cqrs.customer.core.vo.Address;
import swehacker.cqrs.customer.core.vo.Consent;

import java.time.LocalDate;
import java.util.List;

public record RegisterCustomerDto(
        @Email String email,
        @NotBlank String mobile,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String preferredName,
        @NotBlank String preferredLanguage,
        @NotBlank String civicNumber,
        @NotNull LocalDate birthDate,
        @NotNull List<Address> addresses,
        @NotNull List<Consent> consents
) {
}