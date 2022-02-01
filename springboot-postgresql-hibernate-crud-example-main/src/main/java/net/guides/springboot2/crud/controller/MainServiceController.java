package net.guides.springboot2.crud.controller;


import net.guides.springboot2.crud.Application;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.repository.MainServiceDao;
import net.guides.springboot2.crud.service.MainServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mainService")//admin
public class MainServiceController {
    @Autowired
    private MainServiceDao mainServiceDao;
    @Autowired
    MainServiceService mainServiceService;
//, produces = MediaType.APPLICATION_JSON_VALUE
    @GetMapping(value = "/Mservice")
    public List<MainService> getAllMainService() {

        return mainServiceService.findAll();
    }


    @GetMapping("/mai/{id}")
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
                                                         @Valid @RequestBody MainService mainService1) throws ResourceNotFoundException {
        MainService mainService = mainServiceDao.findById(mserviceId)
                .orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mserviceId));
        List<SubService> list = new ArrayList<>();
        for (SubService s : mainService1.getSubServices()) {
            list.add(s);
        }
        mainService.getSubServices().add(list.get(0));

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
