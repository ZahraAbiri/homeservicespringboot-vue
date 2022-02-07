package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.Order;

import java.util.List;

public interface OfferService {

    public void save(Offer offerDto);

    public Order addOfferToOrder(Offer offer) throws ResourceNotFoundException;

    public List<Offer> findByOrder(Order orderDto);

    public Offer findByOrderAndExpert(Order orderDto, Expert expertDto) throws ResourceNotFoundException;
}
