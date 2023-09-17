package swehacker.cqrs.customer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import swehacker.cqrs.customer.core.commands.AnonymizeCustomerCommand;
import swehacker.cqrs.customer.core.commands.CommandHandler;
import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.demo.cqrs.ports.out.CommandDispatcher;

@SpringBootApplication
public class CustomerServiceApplication {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @Autowired
    private CommandHandler commandHandler;

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(RegisterCustomerCommand.class, command -> commandHandler.handle((RegisterCustomerCommand) command) );
        commandDispatcher.registerHandler(AnonymizeCustomerCommand.class, command -> commandHandler.handle((AnonymizeCustomerCommand)command));
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
