package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.repository.SubserviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/v1")
public class SubserviceController {
	@Autowired
	private SubserviceDao subserviceDao;

	@GetMapping("/getsubs")
	public List<SubService> getAllsubservices() {
		return subserviceDao.findAll();
	}


	@GetMapping("/sub/{id}")
	public ResponseEntity<SubService> getsubserviceById(@PathVariable(value = "id") Integer subserviceId)
			throws ResourceNotFoundException {
        SubService subService = subserviceDao.findById(subserviceId)
				.orElseThrow(() -> new ResourceNotFoundException("SubService not found for this id :: " + subserviceId));
		return ResponseEntity.ok().body(subService);
	}

	@PostMapping("/subs")
	public SubService createsubservice(@Valid @RequestBody SubService subService) {
		return subserviceDao.save(subService);
	}

	@PutMapping("/subb/{id}")
	public ResponseEntity<SubService> updatesubservice(@PathVariable(value = "id") Integer subserviceId,
			@Valid @RequestBody SubService suvserviceDetails) throws ResourceNotFoundException {
        SubService subService = subserviceDao.findById(subserviceId)
				.orElseThrow(() -> new ResourceNotFoundException("subservice not found for this id :: " + subserviceId));

		subService.setBasePrice(suvserviceDetails.getBasePrice());

		final SubService updatedsubservice = subserviceDao.save(subService);
		return ResponseEntity.ok(updatedsubservice);
	}

	@DeleteMapping("/su/{id}")
	public Map<String, Boolean> deletesubservice(@PathVariable(value = "id") Integer subserviceId)
			throws ResourceNotFoundException {
        SubService subService = subserviceDao.findById(subserviceId)
				.orElseThrow(() -> new ResourceNotFoundException("subservice not found for this id :: " + subserviceId));

		subserviceDao.delete(subService);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
