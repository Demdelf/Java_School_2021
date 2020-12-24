package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PROPERTIES")
public class Property extends AbstractEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "id")
    private List<PropertyValue> values;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
