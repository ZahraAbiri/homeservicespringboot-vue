package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Address;
import net.guides.springboot2.crud.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/add")
public class AddressController {
	@Autowired
	private AddressDao addressDao;

	@GetMapping("/ad")
	public List<Address> getAllEmployees() {
		return addressDao.findAll();
	}
//	@PostMapping("/employees")
//	public List<Employee> getAllEmployees1() {
//		return employeeRepository.findAll();
//	}
//
	@PostMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/address/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Integer addressId)
			throws ResourceNotFoundException {
		Address address = addressDao.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("address not found for this id :: " + addressId));
		return ResponseEntity.ok().body(address);
	}

	@PostMapping("/address")
	public Address createAddress( @RequestBody Address address) {
		return addressDao.save(address);
	}

	@PutMapping("/add/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Integer addressId,
			 @RequestBody Address addressDetails) throws ResourceNotFoundException {
		Address address = addressDao.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));

		address.setCity(addressDetails.getCity());
		address.setHouseNumber(addressDetails.getHouseNumber());
		address.setStreetAddress(addressDetails.getStreetAddress());
		address.setZipCode(addressDetails.getZipCode());
		final Address updatedAddress = addressDao.save(address);
		return ResponseEntity.ok(updatedAddress);
	}

	@DeleteMapping("/addres/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer addressId)
			throws ResourceNotFoundException {
		Address address = addressDao.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + addressId));

		addressDao.delete(address);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
