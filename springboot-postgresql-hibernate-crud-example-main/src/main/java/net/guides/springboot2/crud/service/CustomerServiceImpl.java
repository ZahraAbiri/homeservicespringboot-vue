package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.repository.ExpertDao;
import net.guides.springboot2.crud.repository.OfferDao;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OfferDao offerDao;
    @Autowired
    private ExpertDao expertDao;

    @Override
    public void save(Customer customer) throws ResourceNotFoundException {
        Optional<Customer> foundedCustomer = customerDao.findByEmailAddress(customer.getEmailAddress());
        if (foundedCustomer.isPresent()) {
            throw new ResourceNotFoundException("not found");
        } else {
            customerDao.save(customer);
        }
    }

    @Override
    public Customer findByEmailAddress(String emailAddress) throws ResourceNotFoundException {
        Optional<Customer> optionalCustomer = customerDao.findByEmailAddress(emailAddress);
        Customer customer = optionalCustomer.orElseThrow(() -> new ResourceNotFoundException("emailAddress not exist!"));
        return customer;
    }

    @Override
    public Customer payByCredit(Customer customerDto, double price) {
        return null;
    }




    @Override
    public Customer findByEmailAddressAndPassword(String email, String password) throws ResourceNotFoundException {
        Optional<Customer> optionalCustomer = customerDao.findByEmailAddressAndPassword(email,password);
        Customer customer = optionalCustomer.orElseThrow(() -> new ResourceNotFoundException("not found"));
        return customer;
    }
}
