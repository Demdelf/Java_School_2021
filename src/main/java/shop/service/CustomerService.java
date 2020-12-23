package shop.service;

import org.springframework.stereotype.Service;
import shop.dao.CustomerDao;
import shop.domain.Customer;

@Service
public class CustomerService extends AbstractService<Customer> {
    CustomerDao dao;
}
