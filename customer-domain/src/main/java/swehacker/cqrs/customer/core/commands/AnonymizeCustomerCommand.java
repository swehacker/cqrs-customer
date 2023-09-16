package swehacker.cqrs.customer.core.commands;

import lombok.Builder;
import swehacker.cqrs.customer.core.vo.CustomerNo;
import swehacker.demo.cqrs.events.BaseEvent;

@Builder
public class AnonymizeCustomerCommand extends BaseEvent<CustomerNo> {

}
