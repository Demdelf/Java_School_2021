package shop.dao.impl;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Role;

@Repository
public class RoleDao extends AbstractDao<Role> implements Dao<Role> {
    public RoleDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Role.class);
    }
}


