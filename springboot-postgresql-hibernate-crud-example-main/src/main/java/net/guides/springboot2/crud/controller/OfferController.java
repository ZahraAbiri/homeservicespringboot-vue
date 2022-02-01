package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

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
    public List<Offer> getAllOffers() {
        return offerDao.findAll();
    }


    @GetMapping("/off/{id}")
    public ResponseEntity<Offer> getEmployeeById(@PathVariable(value = "id") Integer offerId)
            throws ResourceNotFoundException {
        Offer offer = offerDao.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found for this id :: " + offerId));
        return ResponseEntity.ok().body(offer);
    }

    @PostMapping("/offer")
    public Offer createOffer(@Valid @RequestBody Offer offer) {

        return offerDao.save(offer);
    }


    @PostMapping("/offers")
    public Offer createEmployee(@Valid @RequestBody OfferDto offerDto) {
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
    public ResponseEntity<Offer> updateOffer(@PathVariable(value = "id") Integer offerId,
                                             @Valid @RequestBody Offer offerDeyails) throws ResourceNotFoundException {
        Offer offer = offerDao.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found for this id :: " + offerId));

        offer.setExpert(offerDeyails.getExpert());
        offer.setProposedPrice(offerDeyails.getProposedPrice());
        offer.setDurationOfWork(offerDeyails.getDurationOfWork());
        final Offer updatedEmployee = offerDao.save(offer);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/of/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer offerId)
            throws ResourceNotFoundException {
        Offer offer = offerDao.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + offerId));

        offerDao.delete(offer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
