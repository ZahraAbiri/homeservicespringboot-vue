package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.guides.springboot2.crud.dto.Customerdto;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
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

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getEmployeeById(@PathVariable(value = "id") Integer customerId)
			throws ResourceNotFoundException {
		Customer customer = customerDao.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/cu")
	public Customer createCustomr(@RequestBody Customerdto customerdto)

	{
		Customer customer=new Customer();
		customer.setFirstname(customerdto.getFirstname());
		customer.setLastname(customerdto.getLastname());
		customer.setPersonStatuse(customerdto.getPersonStatuse());
		customer.setCredit(customerdto.getCredit());
		customer.setEmailAddress(customerdto.getEmailAddress());
		customer.setPassword(customerdto.getPassword());
		customer.setRegistrationDate(customerdto.getRegistrationDate());


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
