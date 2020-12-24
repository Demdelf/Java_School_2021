package shop.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CUSTOMERSADDRESSES")
public class CustomerAddress extends AbstractEntity {

    private String country;
    private String city;
    private String postcode;
    private String street;
    private String house;
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "id" )
    private User user;

    @OneToMany(mappedBy = "id")
    private List<Order> orders;
}
