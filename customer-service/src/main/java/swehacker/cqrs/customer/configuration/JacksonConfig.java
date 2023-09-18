package swehacker.cqrs.customer.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swehacker.cqrs.customer.configuration.serializers.EmailDeserializer;
import swehacker.cqrs.customer.configuration.serializers.EmailSerializer;
import swehacker.cqrs.customer.configuration.serializers.MsisdnDeserializer;
import swehacker.cqrs.customer.configuration.serializers.MsisdnSerializer;
import swehacker.cqrs.customer.core.vo.Email;
import swehacker.cqrs.customer.core.vo.Msisdn;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule serializerModule = new SimpleModule();
        serializerModule.addSerializer(Email.class, new EmailSerializer());
        serializerModule.addDeserializer(Email.class, new EmailDeserializer());
        serializerModule.addSerializer(Msisdn.class, new MsisdnSerializer());
        serializerModule.addDeserializer(Msisdn.class, new MsisdnDeserializer());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new JavaTimeModule())
                .findAndRegisterModules()
                .registerModule(serializerModule);
    }
}
