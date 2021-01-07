package shop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.DeliveryMethodDao;
import shop.domain.DeliveryMethod;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryMethodService implements shop.service.DeliveryMethodService {
    private final DeliveryMethodDao dao;

    @Override
    public DeliveryMethod findOne(long id) {
        return dao.findOne(id).orElse(null);
    }

    @Override
    public List<DeliveryMethod> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    @Transactional
    public DeliveryMethod create(DeliveryMethod deliveryMethod) {
        return dao.create(deliveryMethod);
    }

    @Override
    @Transactional
    public void delete(DeliveryMethod deliveryMethod) {
        dao.delete(deliveryMethod);
    }

    @Override
    @Transactional
    public DeliveryMethod findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    @Transactional
    public DeliveryMethod update(DeliveryMethod deliveryMethod) {
        return dao.update(deliveryMethod);
    }

}
