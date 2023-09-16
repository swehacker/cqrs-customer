package swehacker.cqrs.customer.vo;

public record Consent(
        ConsentType type,
        Boolean optIn
) {
}
