package shop.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address")
    private CustomerAddress address;

    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;

    @ManyToMany(mappedBy = "products")
    private List<Product> products;

    private PaymentStatus paymentStatus;
    private DeliveryStatus deliveryStatus;
}
