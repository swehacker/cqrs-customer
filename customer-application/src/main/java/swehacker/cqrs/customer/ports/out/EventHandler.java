package swehacker.cqrs.customer.ports.out;

import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;

public interface EventHandler {
    void on(CustomerRegisteredEvent event);
    void on(CustomerAnonymizedEvent event);
}
