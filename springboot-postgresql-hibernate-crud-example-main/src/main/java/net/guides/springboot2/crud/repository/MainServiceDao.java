package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.MainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainServiceDao extends JpaRepository<MainService,Integer> {
    MainService findByName(String name);
    @Query(value="select m.name from MainService m ")
    List<String> findAllNames();

}
