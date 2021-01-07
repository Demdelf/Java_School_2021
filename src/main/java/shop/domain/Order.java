package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_METHOD_ID")
    private DeliveryMethod deliveryMethod;

//    @ManyToMany(mappedBy = "ID")
//    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_STATUS_ID")
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_STATUS_ID")
    private DeliveryStatus deliveryStatus;

    @OneToMany(mappedBy = "id")
    private List<OrderProduct> orderProducts;

//    @OneToOne(mappedBy = "id")
//    private Bucket bucket;
}
