package swehacker.cqrs.customer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import swehacker.cqrs.customer.core.commands.AnonymizeCustomerCommand;
import swehacker.cqrs.customer.core.commands.CommandHandler;
import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.ports.out.CommandDispatcher;

@RequiredArgsConstructor
@SpringBootApplication
public class CustomerServiceApplication {
    private final CommandDispatcher commandDispatcher;
    private final CommandHandler commandHandler;

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(RegisterCustomerCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(AnonymizeCustomerCommand.class, commandHandler::handle);
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
