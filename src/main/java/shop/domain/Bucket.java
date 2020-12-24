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
@NoArgsConstructor
@Table(name = "BUCKETS")
public class Bucket extends AbstractEntity{

    @ManyToMany(mappedBy = "id")
    private List<Product> products;

    @OneToOne(mappedBy = "id")
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
