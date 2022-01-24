package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.MainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainServiceDao extends JpaRepository<MainService,Integer> {
    MainService findByName(String name);

}
