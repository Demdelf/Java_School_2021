package shop.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import shop.dto.ProductDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PROPERTIES")
public class Property extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private List<PropertyValue> values;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
        }
        Property property = (Property) obj;
        return (property.name.equals(this.name) && ((property.type == null && this.type == null) || (property.type.equals(this.type))));
    }

    @Override
    public int hashCode() {
        int hash = 1;
        if(this.name != null){
            hash *= this.name.hashCode();
        }
        if(this.type != null){
            hash *= this.type.hashCode();
        }
        return Math.toIntExact(hash);
    }
}
