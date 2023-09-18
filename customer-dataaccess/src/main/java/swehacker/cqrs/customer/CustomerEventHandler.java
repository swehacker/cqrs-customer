package swehacker.cqrs.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swehacker.cqrs.customer.core.events.CustomerAnonymizedEvent;
import swehacker.cqrs.customer.core.events.CustomerRegisteredEvent;
import swehacker.cqrs.customer.ports.out.EventHandler;
import swehacker.cqrs.customer.repository.CustomerEntity;
import swehacker.cqrs.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerEventHandler implements EventHandler {
    public static final String ANONYMIZED = "Anonymized";
    private final CustomerRepository customerRepository;

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
                .mobile(event.getMobile().msisdn())
                .email(event.getEmail().email())
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
        customer.setFirstName(ANONYMIZED);
        customer.setLastName(ANONYMIZED);
        customer.setPreferredName(ANONYMIZED);
        customer.setCivicNumber(ANONYMIZED);
        customer.setMobile(ANONYMIZED);
        customer.setEmail(ANONYMIZED);
        customer.setAddresses(null);
        customerRepository.save(customer);
    }
}
