package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubserviceDao extends JpaRepository<SubService, Integer> {
    Optional<SubService> findByName(String name);

//    void update(SubService subService);
}
