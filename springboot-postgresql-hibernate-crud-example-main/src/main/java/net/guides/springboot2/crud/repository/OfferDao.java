package net.guides.springboot2.crud.repository;

import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferDao extends JpaRepository<Offer,Integer> {
    List<Offer> findByOrder(Order order, Sort proposedPrice);

    Optional<Offer> findByOrderAndExpert(Order order, Expert expert);
}
