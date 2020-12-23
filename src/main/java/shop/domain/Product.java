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
@Table(name = "PRODUCTS")
public class Product extends AbstractEntity{

    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    private Double weight;
    private Double volume;
    private Integer stock;

    @ManyToMany(mappedBy = "orders")
    private List<Order> orders;
}
