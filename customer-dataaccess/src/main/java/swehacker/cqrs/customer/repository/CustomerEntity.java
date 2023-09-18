package swehacker.cqrs.customer.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import swehacker.cqrs.customer.core.vo.Address;
import swehacker.cqrs.customer.core.vo.Consent;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity {
    @Id
    UUID id;
    String email;
    String mobile;
    String firstName;
    String lastName;
    String preferredName;
    String preferredLanguage;
    String civicNumber;
    LocalDate birthDate;
    @JdbcTypeCode(value = SqlTypes.JSON)
    List<Address> addresses;
    @JdbcTypeCode(value = SqlTypes.JSON)
    List<Consent> consents;
}
