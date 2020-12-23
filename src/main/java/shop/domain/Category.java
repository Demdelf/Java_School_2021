package shop.domain;

import java.util.List;
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

    private String name;

    @ManyToMany(mappedBy = "characteristic")
    private List<Characteristic> characteristics;

    @OneToMany(mappedBy = "products")
    private List<Product> products;
}
