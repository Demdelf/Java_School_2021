package shop.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "CARTS")
public class Cart extends AbstractEntity{

    @ManyToMany(mappedBy = "carts")
    private List<Product> products;

//    @OneToOne(mappedBy = "id")
//    @JoinColumn(name = "ORDER_ID")
//    private Order order;
}
