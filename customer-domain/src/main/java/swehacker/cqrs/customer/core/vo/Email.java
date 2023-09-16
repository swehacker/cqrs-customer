package swehacker.cqrs.customer.core.vo;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public class Email {
    private static final Pattern VALIDATION_REGEXP = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
    private final String email;

    public Email(String email) {
        this.email = validate(email);
    }

    public String email() {
        return email;
    }

    private String validate(String email) {
        if ( VALIDATION_REGEXP.matcher(email).find() ) {
            return email;
        }

        throw new IllegalArgumentException(MessageFormat.format("The email {0} is not a valid address!", email));
    }
}
