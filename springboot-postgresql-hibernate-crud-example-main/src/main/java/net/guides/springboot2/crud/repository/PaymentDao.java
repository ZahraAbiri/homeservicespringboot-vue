package net.guides.springboot2.crud.repository;


import net.guides.springboot2.crud.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends CrudRepository<Payment, Integer> {

}
