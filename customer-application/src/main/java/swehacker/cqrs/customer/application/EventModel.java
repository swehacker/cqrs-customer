package swehacker.cqrs.customer.application;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import swehacker.demo.cqrs.events.BaseEvent;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventstore")
public class EventModel {
    @Id
    private UUID id;
    private Instant timeStamp;
    private String aggregateIdentifier;
    private String aggregateType;
    private int version;
    private String eventType;
    @JdbcTypeCode(SqlTypes.JSON)
    private BaseEvent<?> eventData;
}
