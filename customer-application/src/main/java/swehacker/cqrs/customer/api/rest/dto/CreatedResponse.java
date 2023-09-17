package swehacker.cqrs.customer.api.rest.dto;

import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public class CreatedResponse extends BaseResponse {
    private final UUID id;

    public CreatedResponse(String message, UUID id) {
        super(message);
        this.id = id;
    }
}
