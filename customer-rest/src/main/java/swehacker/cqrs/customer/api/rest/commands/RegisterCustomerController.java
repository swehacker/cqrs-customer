package swehacker.cqrs.customer.api.rest.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swehacker.cqrs.customer.api.rest.dto.BaseResponse;
import swehacker.cqrs.customer.api.rest.dto.RegisterCustomerDto;
import swehacker.cqrs.customer.api.rest.dto.CreatedResponse;
import swehacker.cqrs.customer.core.commands.RegisterCustomerCommand;
import swehacker.cqrs.customer.core.vo.Email;
import swehacker.cqrs.customer.core.vo.Msisdn;
import swehacker.cqrs.customer.ports.out.CommandDispatcher;

import java.text.MessageFormat;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/customer")
public class RegisterCustomerController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterCustomerDto customer) {
        var id = UUID.randomUUID();
        try {
            commandDispatcher.send(RegisterCustomerCommand.builder()
                    .id(id)
                    .firstName(customer.firstName())
                    .lastName(customer.lastName())
                    .preferredName(customer.preferredName())
                    .civicNumber(customer.civicNumber())
                    .birthDate(customer.birthDate())
                    .preferredLanguage(customer.preferredLanguage())
                    .addresses(customer.addresses())
                    .mobile(new Msisdn(customer.mobile()))
                    .email(new Email(customer.email()))
                    .consents(customer.consents())
                    .build());
            return new ResponseEntity<>(new CreatedResponse("Register customer request completed successfully!", id), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            log.warn(MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to register customer for id - {0}.", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new CreatedResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
