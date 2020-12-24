package shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PROPERTY_VALUES")
public class PropertyValue extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Object value;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;
}
