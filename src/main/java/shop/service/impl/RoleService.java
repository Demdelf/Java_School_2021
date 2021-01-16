package shop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dao.impl.RoleDao;
import shop.domain.Role;

@Service
@RequiredArgsConstructor
public class RoleService implements shop.service.RoleService {

    private final RoleDao roleDao;

    @Override
    public Role findOne(long id) {
        return roleDao.findOne(id).orElse(null);
    }

    @Override
    public List<Role> findAll(int pageSize) {
        return null;
    }

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public void delete(Role role) {

    }

    @Override
    public Role findByName(String name) {
        return null;
    }

    @Override
    public Role update(Role p) {
        return null;
    }
}
