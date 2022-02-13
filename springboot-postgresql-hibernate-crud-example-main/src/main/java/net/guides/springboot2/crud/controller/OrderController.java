package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.dto.OrderDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.*;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.repository.OrderDao;
import net.guides.springboot2.crud.repository.SubserviceDao;
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
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private SubserviceDao subserviceDao;

	@GetMapping("/ordersfind")
	@ApiOperation(value = "get orders ",
			response = Order.class)
	public List<Order> getAllOrderss() {
		return orderDao.findAll();
	}


	@GetMapping("/ord/{id}")
	@ApiOperation(value = "get orders by id ",
			response = Order.class)
	public ResponseEntity<Order> getorderById(@PathVariable(value = "id") Integer orderId)
			throws ResourceNotFoundException {
        Order order = orderDao.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + orderId));
		return ResponseEntity.ok().body(order);
	}

	@PostMapping("/order")
	@ApiOperation(value = "save order ",
			response = Order.class)
	public Order createorder(@Valid @RequestBody Order order) {
		return orderDao.save(order);
	}
	@PostMapping("/orders")
	@ApiOperation(value = "customer save order ",
			response = Order.class)
	public Order createorders(@Valid @RequestBody OrderDto orderDto) {
		Optional<Customer> customer=customerDao.findByEmailAddress(orderDto.getCustomerEmailAddrress());
		Customer customer1=new Customer();
		if(customer.isPresent()){
			customer1=customer.get();
		}
		SubService subService=new SubService();
		Optional<SubService> sub=subserviceDao.findById(orderDto.getSubServiceId());
		if(sub.isPresent()){
			subService=sub.get();
		}
		Order order=new Order();
		order.setCustomer(customer1);
		order.setSubService(subService);
		Address address=new Address();
		address.setStreetAddress(orderDto.getStreetAddress());

		return orderDao.save(order);
	}

	@PutMapping("/order/{id}")
	@ApiOperation(value = "update orders ",
			response = Order.class)
	public ResponseEntity<Order> updateOrdes(@PathVariable(value = "id") Integer orderId,
											 @Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
        Order order = orderDao.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + orderId));

		final Order updatedOrder = orderDao.save(order);
		return ResponseEntity.ok(updatedOrder);
	}

	@DeleteMapping("/ord/{id}")
	@ApiOperation(value = "delete orders ",
			response = Order.class)
	public Map<String, Boolean> deleteorder(@PathVariable(value = "id") Integer orderId)
			throws ResourceNotFoundException {
        Order order = orderDao.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("order not found for this id :: " + orderId));

		orderDao.delete(order);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
