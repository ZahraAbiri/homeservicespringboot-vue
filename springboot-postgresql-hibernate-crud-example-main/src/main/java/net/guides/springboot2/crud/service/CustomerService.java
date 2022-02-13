package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.dto.Customerdto;
import net.guides.springboot2.crud.dto.PaymentDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Payment;

import java.util.List;

public interface CustomerService {

   Customer findByEmailCustomerAndPassword(String email, String password) throws ResourceNotFoundException;
//public Customer save(Customerdto customerdto) throws ResourceNotFoundException;
    public Customer getById(Integer customerId) throws ResourceNotFoundException;
    public Customer deleteById(Integer customerId) throws ResourceNotFoundException;
    public Customer updateById(Integer customerId,Customer customerDetails) throws ResourceNotFoundException;
    public Payment paymentByCard(PaymentDto paymentDto) throws ResourceNotFoundException;
    public Payment paymentByCredit(PaymentDto paymentDto) throws ResourceNotFoundException;
    public List<Customer> get();


}
