package swehacker.cqrs.customer.api.rest.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swehacker.cqrs.customer.api.rest.dto.BaseResponse;
import swehacker.cqrs.customer.core.commands.AnonymizeCustomerCommand;
import swehacker.demo.cqrs.exceptions.AggregateNotFoundException;
import swehacker.cqrs.customer.ports.out.CommandDispatcher;

import java.text.MessageFormat;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/customer/")
public class AnonymizeCustomerController {

    private final CommandDispatcher commandDispatcher;

    @DeleteMapping(path = "/{id}/anonymize")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") UUID id) {
        try {
            commandDispatcher.send(AnonymizeCustomerCommand.builder()
                    .id(id)
                    .reason("Whatever")
                    .build());
            return new ResponseEntity<>(new BaseResponse("Anonymize customer successfully completed!"), HttpStatus.OK);
        } catch (IllegalStateException | AggregateNotFoundException e) {
            log.warn(MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request to anonymize customer with id - {0}.", id);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
