package swehacker.cqrs.customer.configuration.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import swehacker.cqrs.customer.core.vo.Email;

import java.io.IOException;

@SuppressWarnings("PMD.MissingSerialVersionUID")
public class EmailSerializer extends StdSerializer<Email> {

    public EmailSerializer() {
        this(null);
    }

    public EmailSerializer(Class<Email> t) {
        super(t);
    }

    @Override
    public void serialize(Email value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("email", value.email());
        gen.writeEndObject();
    }
}
