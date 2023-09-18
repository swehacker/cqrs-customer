package swehacker.cqrs.customer.configuration.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import swehacker.cqrs.customer.core.vo.Msisdn;

import java.io.IOException;

@SuppressWarnings("PMD.MissingSerialVersionUID")
public class MsisdnDeserializer extends StdDeserializer<Msisdn> {

    public MsisdnDeserializer() {
        this(null);
    }

    public MsisdnDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Msisdn deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return new Msisdn(node.asText());
    }
}
