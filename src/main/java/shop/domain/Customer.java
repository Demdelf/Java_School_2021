package shop.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CUSTOMERS")
public class Customer extends AbstractEntity{

    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;

    @OneToMany(mappedBy = "id")
    private List<Order> orders;

    @OneToMany(mappedBy = "id")
    private List<CustomerAddress> addresses;


}
