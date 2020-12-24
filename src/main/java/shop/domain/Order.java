package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ADDRESS_ID")
    private CustomerAddress address;

    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Column(name = "DELIVERY_METHOD")
    private DeliveryMethod deliveryMethod;

//    @ManyToMany(mappedBy = "ID")
//    private List<Product> products;

    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;

    @Column(name = "DELIVERY_STATUS")
    private DeliveryStatus deliveryStatus;

//    @OneToOne(mappedBy = "id")
//    private Bucket bucket;
}
