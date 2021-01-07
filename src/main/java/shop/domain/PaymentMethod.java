package shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PAYMENT_METHODS")
public class PaymentMethod extends AbstractEntity{
    @Column(name = "NAME")
    private String name;
}
