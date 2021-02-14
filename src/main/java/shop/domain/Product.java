package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

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

    @Column(name = "VISIBLE")
    private Boolean visible;

    @Column(name = "IMAGE")
    @Lob
    @Type(type="org.hibernate.type.ImageType")
    private byte[] image;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PropertyValue> propertyValues;
}
