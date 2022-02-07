package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.dto.PaymentDto;
import net.guides.springboot2.crud.model.Customer;


public interface PaymentService {
    Customer pay(PaymentDto paymentDto);
}
