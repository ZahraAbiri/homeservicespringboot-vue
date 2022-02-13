package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.dto.OfferDto;
import net.guides.springboot2.crud.dto.OrderDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.*;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.repository.ExpertDao;
import net.guides.springboot2.crud.repository.OfferDao;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/off")
public class OfferController {
    @Autowired
    private OfferDao offerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ExpertDao expertDao;
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/ofer")
    @ApiOperation(value = "get offers",
            response = Offer.class)
    public List<Offer> getAllOffers() {
        return offerDao.findAll();
    }


    @GetMapping("/off/{id}")
    @ApiOperation(value = "get offers by id",
            response = Offer.class)
    public ResponseEntity<Offer> getofferById(@PathVariable(value = "id") Integer offerId)
            throws ResourceNotFoundException {
        Offer offer = offerDao.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found for this id :: " + offerId));
        return ResponseEntity.ok().body(offer);
    }

    @PostMapping("/offer")
    @ApiOperation(value = "save offers ",
            response = Offer.class)
    public Offer createOffer(@Valid @RequestBody Offer offer) {

        return offerDao.save(offer);
    }


    @PostMapping("/offers")
    @ApiOperation(value = "expert add his offer",
            response = Offer.class)
    public Offer createOffer(@Valid @RequestBody OfferDto offerDto) {
        Optional<Expert> expert = expertDao.findByEmailAddress(offerDto.getExpertEmailAddress());
        Expert expert1 = new Expert();
        if (expert.isPresent()) {
            expert1 = expert.get();
        }
        Order order = new Order();
        Optional<Order> ord = orderDao.findById(offerDto.getOrderId());
        if (ord.isPresent()) {
            order = ord.get();
        }
       Offer offer=new Offer();
        offer.setExpert(expert1);
        offer.setOrder(order);
        offer.setDurationOfWork(offerDto.getDurationOfWork());
        offer.setDescription(offerDto.getDescription());

        return offerDao.save(offer);
    }


    @PutMapping("/ofer/{id}")
    @ApiOperation(value = "update offers by id",
            response = Offer.class)
    public ResponseEntity<Offer> updateOffer(@PathVariable(value = "id") Integer offerId,
                                             @Valid @RequestBody Offer offerDeyails) throws ResourceNotFoundException {
        Offer offer = offerDao.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found for this id :: " + offerId));

        offer.setExpert(offerDeyails.getExpert());
        offer.setProposedPrice(offerDeyails.getProposedPrice());
        offer.setDurationOfWork(offerDeyails.getDurationOfWork());
        final Offer updatedOffers = offerDao.save(offer);
        return ResponseEntity.ok(updatedOffers);
    }

    @DeleteMapping("/of/{id}")
    @ApiOperation(value = "delete offers by id",
            response = Offer.class)
    public Map<String, Boolean> deleteOffers(@PathVariable(value = "id") Integer offerId)
            throws ResourceNotFoundException {
        Offer offer = offerDao.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("offer not found for this id :: " + offerId));

        offerDao.delete(offer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
