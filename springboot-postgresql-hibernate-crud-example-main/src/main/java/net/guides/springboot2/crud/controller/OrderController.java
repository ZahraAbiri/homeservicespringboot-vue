package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderDao orderDao;

	@GetMapping("/orders")
	public List<Order> getAllEmployees() {
		return orderDao.findAll();
	}


	@GetMapping("/ord/{id}")
	public ResponseEntity<Order> getEmployeeById(@PathVariable(value = "id") Integer orderId)
			throws ResourceNotFoundException {
        Order order = orderDao.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + orderId));
		return ResponseEntity.ok().body(order);
	}

	@PostMapping("/order")
	public Order createEmployee(@Valid @RequestBody Order order) {
		return orderDao.save(order);
	}

	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateEmployee(@PathVariable(value = "id") Integer orderId,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
        Order order = orderDao.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + orderId));

		final Order updatedEmployee = orderDao.save(order);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/ord/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer orderId)
			throws ResourceNotFoundException {
        Order order = orderDao.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + orderId));

		orderDao.delete(order);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
