package swehacker.cqrs.customer.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;
import swehacker.cqrs.customer.repository.CustomerEntity;
import swehacker.cqrs.customer.repository.CustomerRepository;

@Service
public class CustomerEventHandler implements EventHandler {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void on(CustomerRegisteredEvent event) {
        var customerEntity = CustomerEntity.builder()
                .id(event.getId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .preferredName(event.getPreferredName())
                .preferredLanguage(event.getPreferredLanguage())
                .consents(event.getConsents())
                .birthDate(event.getBirthDate())
                .mobile(event.getMobile())
                .email(event.getEmail())
                .civicNumber(event.getCivicNumber())
                .addresses(event.getAddresses())
                .build();
        customerRepository.save(customerEntity);
    }

    @Override
    public void on(CustomerAnonymizedEvent event) {
        var optionalCustomer = customerRepository.findById(event.getId());
        if (optionalCustomer.isEmpty()) {
            return;
        }

        CustomerEntity customer = optionalCustomer.get();
        customer.setAddresses(null);
        customer.setEmail("");
        customer.setCivicNumber("");
        customerRepository.save(customer);
    }
}
