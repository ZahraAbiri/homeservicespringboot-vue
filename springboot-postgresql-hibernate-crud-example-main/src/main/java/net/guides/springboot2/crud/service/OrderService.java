package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.model.enums.OfferStatus;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private  OrderDao orderDao;

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    public Order findById(Integer id) {
        Optional<Order> order = orderDao.findById(id);
        return order.orElseThrow(() -> new RuntimeException("this order not exist!"));
    }

    public Offer findAcceptedOfferOfOrder(Order order) {
        Offer acceptedOffer = null;
        if (order.getOrderStatus().equals(OrderStatus.PAID)) {
            Set<Offer> offers = order.getOffers();
            for (Offer offer : offers) {
                if (offer.getOfferStatus().equals(OfferStatus.ACCEPTED)) {
                    acceptedOffer = offer;
                }
            }
            return acceptedOffer;
        } else {
            throw new RuntimeException("Order not Paid!");
        }
    }


    public Order save(Order order) {
       return orderDao.save(order);
    }
}
