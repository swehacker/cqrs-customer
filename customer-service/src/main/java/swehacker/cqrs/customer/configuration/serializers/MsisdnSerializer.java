package swehacker.cqrs.customer.configuration.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import swehacker.cqrs.customer.core.vo.Msisdn;

import java.io.IOException;

@SuppressWarnings("PMD.MissingSerialVersionUID")
public class MsisdnSerializer extends StdSerializer<Msisdn> {

    public MsisdnSerializer() {
        this(null);
    }

    public MsisdnSerializer(Class<Msisdn> t) {
        super(t);
    }

    @Override
    public void serialize(Msisdn value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("msisdn", value.msisdn() );
        gen.writeEndObject();
    }
}
