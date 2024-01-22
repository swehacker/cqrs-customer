package swehacker.cqrs.customer.messaging.adapters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import swehacker.cqrs.customer.core.commands.AnonymizeCustomerCommand;
import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.core.entities.CustomerAggregate;
import swehacker.cqrs.customer.commands.handlers.AnonymizeCustomerCommandHandler;
import swehacker.cqrs.customer.commands.handlers.RegisterCustomerCommandHandler;
import swehacker.cqrs.customer.commands.handlers.RestoreReadDbCommandHandler;
import swehacker.cqrs.customer.ports.in.CommandDispatcher;
import swehacker.demo.cqrs.handlers.EventSourcingHandler;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CommandDispatcherService.class, AnonymizeCustomerCommandHandler.class, RegisterCustomerCommandHandler.class, RestoreReadDbCommandHandler.class})
@ExtendWith(MockitoExtension.class)
class CommandDispatcherServiceTest {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @MockBean
    private EventSourcingHandler<CustomerAggregate> eventSourcingHandler;

    @Test
    void testSendAnonymizeCustomerCommand() {
        when(eventSourcingHandler.getById(any())).thenReturn(new CustomerAggregate());

        commandDispatcher.send(AnonymizeCustomerCommand.builder()
                .id(UUID.randomUUID())
                .build());

        verify(eventSourcingHandler).save(any());
    }

    @Test
    void testSendRegisterCustomerCommand() {
        commandDispatcher.send(RegisterCustomerCommand.builder()
                .id(UUID.randomUUID())
                .build());

        verify(eventSourcingHandler).save(any());
    }
}