package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CATEGORIES")
public class Category extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "id")
    private List<Property> properties;

    @OneToMany(mappedBy = "id")
    private List<Product> products;
}
