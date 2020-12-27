package shop.dao;

import javax.persistence.EntityManager;
import shop.domain.Product;

public class ProductDao  extends AbstractDao<Product> implements Dao<Product>{

    public ProductDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Product.class);
    }
}
