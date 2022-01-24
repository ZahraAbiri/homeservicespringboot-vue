package net.guides.springboot2.crud.service;


import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.model.enums.OfferStatus;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import net.guides.springboot2.crud.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OfferService offerService;


    public Customer save(Customer customer) {
        customerDao.save(customer);
        return customer;
    }

    public Customer findByEmailAddress(String emailAddress) {
        Optional<Customer> customer = customerDao.findByEmailAddress(emailAddress);
        if (customer.isPresent()) {
            Customer foundedCustomer = customer.get();
            return foundedCustomer;
        } else {
            throw new RuntimeException("emailAddress not exist!");
        }
    }

    public boolean isExist(String emailAddress) {
        Optional<Customer> customer = customerDao.findByEmailAddress(emailAddress);
        if (customer.isPresent()) {
            throw new RuntimeException("this emailAddress exist!");
        } else {
            return false;
        }
    }

    public void deleteCustomer(Customer customer) {
        customerDao.delete(customer);
    }

//    public void updateCustomer(Customer customer) {
//        CustomerDao.update(customer);
//    }


    public List<Customer> findAll() {
        return customerDao.findAll();
    }


    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    public void acceptOfferForOrder(Order order, Expert expert) {
        order.setExpert(expert);
        order.setOrderStatus(OrderStatus.WATINGFORTHEEXPERTTOARRIVE);
        orderService.saveOrder(order);
        Offer acceptedOffer = offerService.findByOrderAndExpert(order, expert);
        Set<Offer> offers = order.getOffers();
        for (Offer offer : offers) {
            if (offer.equals(acceptedOffer)) {
                offer.setOfferStatus(OfferStatus.ACCEPTED);
            } else {
                offer.setOfferStatus(OfferStatus.REJECTED);
            }
            offerService.save(offer);
        }
    }

    public void addnewOrder(Order order) {
        order.setOrderStatus(OrderStatus.WATINGFOREXPERTSUGESTION);
        orderService.save(order);
    }

}
