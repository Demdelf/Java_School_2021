package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CATEGORIES")
public class Category extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Property> properties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;
}
