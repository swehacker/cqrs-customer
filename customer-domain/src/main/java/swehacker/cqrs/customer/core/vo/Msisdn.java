package swehacker.cqrs.customer.core.vo;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public class Msisdn {
    private static final Pattern VALIDATE_MSDN =
            Pattern.compile("^(?=^(?:tel\\:)?(?:[+\\ ()]*[0-9]){3,20}\\ *$)(?:tel\\:)?\\ *\\+?[0-9\\ ]+(?:\\([0-9]{1,3}\\)[0-9\\ ]+)?$");
    private final String msisdn;

    public Msisdn(String msisdn) {
        this.msisdn = validate(msisdn);
    }

    public String msisdn() {
        return msisdn;
    }

    private String validate(String msisdn) {
        if (msisdn == null || msisdn.isEmpty()) {
            throw new IllegalArgumentException("Msisdn is not allowed to be null or empty!");
        }

        if (VALIDATE_MSDN.matcher(msisdn).find()) {
            return msisdn;
        }

        throw new IllegalArgumentException(MessageFormat.format("MSISDN {0} does not have the correct format", msisdn));
    }
}
