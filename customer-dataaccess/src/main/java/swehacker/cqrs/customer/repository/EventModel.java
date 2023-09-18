package swehacker.cqrs.customer.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "aggregate_id")
    private UUID aggregateIdentifier;

    @Column(name = "aggregate_type")
    private String aggregateType;

    private int version;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Instant created;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_data")
    @JdbcTypeCode(SqlTypes.JSON)
    private String eventData;
}
