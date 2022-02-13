package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerDao extends JpaRepository<Manager,Integer> {
    Manager findByEmailAddressAndPassword(String emailAddress,String password);
//    Optional<Manager> findByUsername(String username);
//
//    Optional<MainService> findByName(String name);
//
//    Optional<Manager> findByEmailAddressAndPassword(String email, String password);
}
