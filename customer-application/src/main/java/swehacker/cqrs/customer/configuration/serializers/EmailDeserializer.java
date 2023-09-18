package swehacker.cqrs.customer.configuration.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import swehacker.cqrs.customer.core.vo.Email;

import java.io.IOException;

@SuppressWarnings("PMD.MissingSerialVersionUID")
public class EmailDeserializer extends StdDeserializer<Email> {

    public EmailDeserializer() {
        this(null);
    }

    public EmailDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Email deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return new Email(node.asText());
    }
}
