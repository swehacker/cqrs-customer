package swehacker.cqrs.customer.events;

import lombok.Builder;
import swehacker.cqrs.customer.vo.CustomerNo;
import swehacker.demo.cqrs.events.BaseEvent;

@Builder
public class CustomerRegisteredEvent extends BaseEvent<CustomerNo> {
}
