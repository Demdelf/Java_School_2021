package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CUSTOMERS_ADDRESSES")
@ToString
public class CustomerAddress extends AbstractEntity {

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTCODE")
    private String postcode;

    @Column(name = "STREET")
    private String street;

    @Column(name = "HOUSE")
    private String house;

    @Column(name = "APARTMENT")
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "USER_ID" )
    private User user;

//    @OneToMany(mappedBy = "id")
//    private List<Order> orders;
}
