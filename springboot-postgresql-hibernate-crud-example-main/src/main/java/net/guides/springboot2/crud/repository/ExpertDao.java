package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpertDao extends JpaRepository<Expert,Integer> {
    Optional<Expert> findByEmailAddress(String email);
    @Modifying
    @Query(value = "UPDATE Expert e set e.password =:password where e.id=:id")
    void UpdatePassword(@Param("password") String password, @Param("id") int id);

    Expert findByEmailAddressAndPassword(String email, String password);

//    void update(Expert expert);
}
