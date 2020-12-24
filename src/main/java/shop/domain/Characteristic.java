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
@Table(name = "CHARACTERISTICS")
public class Characteristic extends AbstractEntity{

    private String name;
    private String type;

    @OneToMany(mappedBy = "id")
    private List<CharacteristicValue> values;

    @ManyToMany(mappedBy = "id")
    private List<Category> categories;
}
