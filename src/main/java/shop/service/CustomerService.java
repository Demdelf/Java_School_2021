package shop.service;

import org.springframework.stereotype.Service;
import shop.dao.CustomerDao;
import shop.domain.User;

@Service
public class CustomerService extends AbstractService<User> {
    CustomerDao dao;
}
