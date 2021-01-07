package shop.dao.impl;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.domain.DeliveryMethod;

@Repository
public class DeliveryMethodDao extends AbstractDao<DeliveryMethod> implements shop.dao.DeliveryMethodDao {

    public DeliveryMethodDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(DeliveryMethod.class);
    }
}
