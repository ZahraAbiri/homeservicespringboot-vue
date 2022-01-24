package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.repository.MainServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")
//Access-Control-Allow-Origin
@RestController
@RequestMapping("/mainService")
public class MainServiceController {
	@Autowired
	private MainServiceDao mainServiceDao;

	@GetMapping("/Mservice")
	public List<MainService> getAllMainService()
	{
		return mainServiceDao.findAll();
	}


	@GetMapping("/employees/{id}")
	public ResponseEntity<MainService> getMServiceById(@PathVariable(value = "id") Integer mainServiceId)
			throws ResourceNotFoundException {
		MainService mainService = mainServiceDao.findById(mainServiceId)
				.orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mainServiceId));
		return ResponseEntity.ok().body(mainService);
	}

	@PostMapping("/mSer")
	public MainService createEmployee(@Valid @RequestBody MainService mainService) {
		return mainServiceDao.save(mainService);
	}

	@PutMapping("/mServ/{id}")
	public ResponseEntity<MainService> updateMainService(@PathVariable(value = "id") Integer mserviceId,
			@Valid @RequestBody MainService employeeDetails) throws ResourceNotFoundException {
		MainService mainService = mainServiceDao.findById(mserviceId)
				.orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mserviceId));

		mainService.setName(employeeDetails.getName());

		final MainService updatedEmployee = mainServiceDao.save(mainService);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/ma/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer mainServiceId)
			throws ResourceNotFoundException {
		MainService mainService = mainServiceDao.findById(mainServiceId)
				.orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mainServiceId));

		mainServiceDao.delete(mainService);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
