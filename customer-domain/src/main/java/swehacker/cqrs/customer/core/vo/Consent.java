package swehacker.cqrs.customer.core.vo;

public record Consent(
        ConsentType type,
        Boolean optIn
) {
}
