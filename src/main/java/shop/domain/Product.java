package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "PRODUCTS")
public class Product extends AbstractEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "VOLUME")
    private Double volume;

    @Column(name = "STOCK")
    private Integer stock;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

//    @ManyToMany(mappedBy = "id")
//    private List<Order> orders;
}
