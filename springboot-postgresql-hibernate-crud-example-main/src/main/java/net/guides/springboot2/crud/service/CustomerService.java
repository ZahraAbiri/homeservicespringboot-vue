package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.dto.OrderDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;

public interface CustomerService {

    public void save(Customer customer) throws ResourceNotFoundException;

    public Customer findByEmailAddress(String emailAddress) throws ResourceNotFoundException;

    //    public void acceptOfferForOrder(OrderDto orderDto, Expert expertDto);
    Customer payByCredit(Customer customerDto, double price);

    Customer findByEmailAddressAndPassword(String email, String password) throws ResourceNotFoundException;
}
