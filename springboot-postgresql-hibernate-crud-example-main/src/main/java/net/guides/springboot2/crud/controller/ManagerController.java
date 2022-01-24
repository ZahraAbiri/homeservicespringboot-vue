package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Manager;
import net.guides.springboot2.crud.repository.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/ma")
public class ManagerController {
	@Autowired
	private ManagerDao managerDao;

	@GetMapping("/man")
	public List<Manager> getAllManagers() {
		return managerDao.findAll();
	}


	@GetMapping("/man/{id}")
	public ResponseEntity<Manager> getManagerById(@PathVariable(value = "id") Integer managerId)
			throws ResourceNotFoundException {
        Manager manager = managerDao.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager not found for this id :: " + managerId));
		return ResponseEntity.ok().body(manager);
	}

	@PostMapping("/mans")
	public Manager createManager(@Valid @RequestBody Manager manager)
	{
		return managerDao.save(manager);
	}

	@PutMapping("/manag/{id}")
	public ResponseEntity<Manager> updateManager(@PathVariable(value = "id") Integer managerId,
			@Valid @RequestBody Manager managerDetails) throws ResourceNotFoundException {
        Manager manager = managerDao.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager not found for this id :: " + managerId));

		manager.setFirstname(managerDetails.getFirstname());

		final Manager updatedEmployee = managerDao.save(manager);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteManager(@PathVariable(value = "id") Integer managerId)
			throws ResourceNotFoundException {
        Manager manager = managerDao.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager not found for this id :: " + managerId));

		managerDao.delete(manager);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
