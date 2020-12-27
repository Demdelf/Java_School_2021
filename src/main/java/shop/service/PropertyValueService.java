package shop.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dao.PropertyValueDao;
import shop.domain.PropertyValue;

@Service
@RequiredArgsConstructor
public class PropertyValueService implements shop.service.Service<PropertyValue> {

    private final PropertyValueDao dao;

    @Override
    public PropertyValue findOne(long id) {
        return dao.findOne(id).get();
    }

    @Override
    public List<PropertyValue> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    public PropertyValue create(PropertyValue propertyValue) {
        return dao.create(propertyValue);
    }

    @Override
    public void delete(PropertyValue propertyValue) {
        dao.delete(propertyValue);
    }
}
