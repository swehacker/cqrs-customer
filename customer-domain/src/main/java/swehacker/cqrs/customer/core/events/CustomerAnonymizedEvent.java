package swehacker.cqrs.customer.core.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import swehacker.demo.cqrs.events.BaseEvent;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAnonymizedEvent extends BaseEvent {
    private String reason;
}
