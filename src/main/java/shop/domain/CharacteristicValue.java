package shop.domain;

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
@Table(name = "CHARACTERISTICVALUES")
public class CharacteristicValue extends AbstractEntity {

    private String name;
    private Object value;

    @ManyToOne
    @JoinColumn(name = "id")
    private Characteristic characteristic;
}
