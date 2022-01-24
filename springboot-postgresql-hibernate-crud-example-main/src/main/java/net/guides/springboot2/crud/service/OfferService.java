package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import net.guides.springboot2.crud.repository.OfferDao;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OfferService {
    @Autowired
    private OfferDao offerDao;
    @Autowired
    private OrderDao orderDao;

    public Offer save(Offer offer) {
        return offerDao.save(offer);
    }

    public Order addOfferToOrder(Offer offer) {
        Set<SubService> expertServices = offer.getExpert().getServices();
        SubService subService = offer.getOrder().getSubService();
        if (expertServices.contains(subService) && subService.getBasePrice() <= offer.getProposedPrice()) {
            Offer savedOffer = save(offer);
            Order order = savedOffer.getOrder();
            order.setOrderStatus(OrderStatus.WATINGFOREXPORTSELECTION);
            order.getOffers().add(offer);
            orderDao.save(order);
            return order;
        } else {
            throw new RuntimeException("your offer is not match for this Order!");
        }
    }

    public List<Offer> findByOrder(Order order) {
        return offerDao.findByOrder(order, Sort.by("expert.score", "proposedPrice").descending());
    }

    public Offer findByOrderAndExpert(Order order, Expert expert) {
        Optional<Offer> offer = offerDao.findByOrderAndExpert(order, expert);
        return offer.orElseThrow(() -> new RuntimeException("offer not found!"));
    }
}
