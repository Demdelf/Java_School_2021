package shop.dao;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.domain.Category;

@Repository
public class CategoryDao extends AbstractDao<Category> implements Dao<Category>{

    public CategoryDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Category.class);
    }
}
