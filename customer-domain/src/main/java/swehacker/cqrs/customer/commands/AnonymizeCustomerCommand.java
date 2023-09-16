package swehacker.cqrs.customer.commands;

import lombok.Builder;
import swehacker.cqrs.customer.vo.CustomerNo;
import swehacker.demo.cqrs.events.BaseEvent;

@Builder
public class AnonymizeCustomerCommand extends BaseEvent<CustomerNo> {

}
