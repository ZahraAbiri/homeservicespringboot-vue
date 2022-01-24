package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.repository.ExpertDao;
import net.guides.springboot2.crud.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/exp")
public class ExpertController {
	@Autowired
	private ExpertDao expertDao;

	@GetMapping("/expert")
	public List<Expert> getAllEmployees() {
		return expertDao.findAll();
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

	@GetMapping("/expert/{id}")
	public ResponseEntity<Expert> getExpertById(@PathVariable(value = "id") Integer expertId)
			throws ResourceNotFoundException {
		Expert expert = expertDao.findById(expertId)
				.orElseThrow(() -> new ResourceNotFoundException("Expert not found for this id :: " + expertId));
		return ResponseEntity.ok().body(expert);
	}

	@PostMapping("/experts")
	public Expert createExpert(@Valid @RequestBody Expert expert) {
		return expertDao.save(expert);
	}

	@PutMapping("/experts/{id}")
	public ResponseEntity<Expert> updateExpert(@PathVariable(value = "id") Integer expertId,
			@Valid @RequestBody Expert expertDetails) throws ResourceNotFoundException {
		Expert expert = expertDao.findById(expertId)
				.orElseThrow(() -> new ResourceNotFoundException("Expert not found for this id :: " + expertId));
//add others
		expert.setPhoto(expertDetails.getPhoto());
		expert.setFirstname(expertDetails.getFirstname());
		expert.setLastname(expertDetails.getLastname());
		expert.setEmailAddress(expertDetails.getEmailAddress());
		expert.setLastname(expertDetails.getLastname());
		expert.setLastname(expertDetails.getLastname());
		expert.setLastname(expertDetails.getLastname());
		final Expert updatedEmployee = expertDao.save(expert);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/exper/{id}")
	public Map<String, Boolean> deleteExpert(@PathVariable(value = "id") Integer expertId)
			throws ResourceNotFoundException {
		Expert expert = expertDao.findById(expertId)
				.orElseThrow(() -> new ResourceNotFoundException("Expert not found for this id :: " + expertId));

		expertDao.delete(expert);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
