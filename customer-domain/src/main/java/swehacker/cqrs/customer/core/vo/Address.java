package swehacker.cqrs.customer.core.vo;

public record Address(
        boolean preferred,
        AddressType type,
        String streetAddress,
        String appartmentNo,
        String zipCode,
        String city,
        String country
) {
}
