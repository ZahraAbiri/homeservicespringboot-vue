package net.guides.springboot2.crud.controller;


import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.repository.MainServiceDao;
import net.guides.springboot2.crud.service.MainServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mainService")
public class MainServiceController {
    @Autowired
    private MainServiceDao mainServiceDao;
    @Autowired
    MainServiceService mainServiceService;
    @GetMapping(value = "/Mservice")
    @ApiOperation(value = "get MainService",
            response = MainService.class)
    public List<MainService> getAllMainService() {

        return mainServiceService.findAll();
    }


    @GetMapping("/mai/{id}")
    @ApiOperation(value = "get MainService by id",
            response = MainService.class)
    public ResponseEntity<MainService> getMServiceById(@PathVariable(value = "id") Integer mainServiceId)
            throws ResourceNotFoundException {
        MainService mainService = mainServiceDao.findById(mainServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mainServiceId));
        return ResponseEntity.ok().body(mainService);
    }

    @PostMapping("/mSer")
    @ApiOperation(value = "save MainService",
            response = MainService.class)
    public MainService createmainservice(@Valid @RequestBody MainService mainService) {
        return mainServiceDao.save(mainService);
    }

    @PutMapping("/mServ/{id}")
    @ApiOperation(value = "update MainService by id",
            response = MainService.class)
    public ResponseEntity<MainService> updateMainService(@PathVariable(value = "id") Integer mserviceId,
                                                         @Valid @RequestBody MainService mainService1) throws ResourceNotFoundException {
        MainService mainService = mainServiceDao.findById(mserviceId)
                .orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mserviceId));
        List<SubService> list = new ArrayList<>();
        for (SubService s : mainService1.getSubServices()) {
            list.add(s);
        }
        mainService.getSubServices().add(list.get(0));

        final MainService updatedmainservice = mainServiceDao.save(mainService);
        return ResponseEntity.ok(updatedmainservice);
    }

    @DeleteMapping("/ma/{id}")
    @ApiOperation(value = "delete MainService by id",
            response = MainService.class)
    public Map<String, Boolean> deletemainService(@PathVariable(value = "id") Integer mainServiceId)
            throws ResourceNotFoundException {
        MainService mainService = mainServiceDao.findById(mainServiceId)
                .orElseThrow(() -> new ResourceNotFoundException("MainService not found for this id :: " + mainServiceId));

        mainServiceDao.delete(mainService);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
