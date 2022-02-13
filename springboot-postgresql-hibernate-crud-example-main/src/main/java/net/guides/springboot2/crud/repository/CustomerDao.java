package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    //    static void update(Customer customer) {
//    }
    Customer findByEmailAddressAndPassword(String email, String password);

    Optional<Customer> findByEmailAddress(String email);
    Customer findByEmailAddressIgnoreCase(String emailId);

//    Optional<Customer> findByEmailAddressAndPassword(String email, String password);
}
