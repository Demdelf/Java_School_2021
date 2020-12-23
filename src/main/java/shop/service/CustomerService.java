package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dao.CustomerDao;
import shop.dao.Dao;
import shop.domain.Customer;

@Service
public class CustomerService extends AbstractService<Customer> {
}
