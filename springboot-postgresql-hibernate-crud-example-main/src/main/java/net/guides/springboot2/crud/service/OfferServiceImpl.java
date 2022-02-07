package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.dto.OrderDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import net.guides.springboot2.crud.repository.OfferDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferDao offerDao;
    @Autowired
    private  OrderServiceImpl orderService;
    private  ModelMapper modelMapper;


    @Override
    public void save(Offer offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        offerDao.save(offer);
    }

    @Override
    public Order addOfferToOrder(Offer offer) throws ResourceNotFoundException {
        Offer offers = new Offer();
        List<SubService> expertServices = offer.getExpert().getServices();
        SubService subService = offer.getOrder().getSubService();
        if (expertServices.contains(subService) && subService.getBasePrice() <= offer.getProposedPrice()) {
            save(offers);
            Order order = offer.getOrder();
            order.setOrderStatus(OrderStatus.WATINGFOREXPORTSELECTION);
            order.getOffers().add(offer);
            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            orderService.save(orderDto);
            return order;
        } else {
            throw new ResourceNotFoundException("your offer is not match for this Order!");
        }

    }

    @Override
    public List<Offer> findByOrder(Order orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        List<Offer> offers = offerDao.findByOrder(order, Sort.by("expert.score", "proposedPrice").descending());
        return offers;

    }

    @Override
    public Offer findByOrderAndExpert(Order orderDto, Expert expertDto) throws ResourceNotFoundException {
        Order order = modelMapper.map(orderDto, Order.class);
        Expert expert = modelMapper.map(expertDto, Expert.class);
        Optional<Offer> optionalOffer = offerDao.findByOrderAndExpert(order, expert);
        Offer offer = optionalOffer.orElseThrow(() -> new ResourceNotFoundException("offer not found!"));
        return offer;
    }
}
