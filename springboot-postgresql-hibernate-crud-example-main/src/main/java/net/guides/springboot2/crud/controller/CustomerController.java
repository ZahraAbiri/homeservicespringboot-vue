package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/cus")
public class CustomerController {
	private final CustomerDao customerDao;

	@Autowired
	public CustomerController(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@GetMapping("/custom")
	public List<Customer> getAllCustomer() {
		return customerDao.findAll();
	}
//	@PostMapping("/employees")
//	public List<Employee> getAllEmployees1() {
//		return employeeRepository.findAll();
//	}
//
//	@PostMapping("/test")
//	public String test() {
//		return "test";
//	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getEmployeeById(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@GetMapping("/cu")
	public Customer createCustomr(@RequestBody Customer customer)
	{
		return customerDao.save(customer);
	}

	@PostMapping("/c")
	public String createCustomr(@RequestBody String customer)
	{
		return customer;
	}

	@PutMapping("/custom/{id}")
	public ResponseEntity<Customer> updateEmployee(@PathVariable(value = "id") Integer customerId,
			@RequestBody Customer custommerDetails) throws ResourceNotFoundException {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customer.setEmailAddress(custommerDetails.getEmailAddress());
		customer.setFirstname(custommerDetails.getFirstname());
		customer.setLastname(custommerDetails.getLastname());
		customer.setPassword(custommerDetails.getPassword());
		customer.setCredit(custommerDetails.getCredit());
		customer.setPersonStatuse(custommerDetails.getPersonStatuse());

		final Customer updatedCustomer = customerDao.save(customer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/custom/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customerDao.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
