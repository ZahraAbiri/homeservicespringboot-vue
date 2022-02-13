package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.model.Manager;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.repository.ExpertDao;
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
    @Autowired
    private ExpertDao expertDao;
    @Autowired
    private CustomerDao customerDao;

    @GetMapping("/man")
    @ApiOperation(value = "get Manager",
            response = Manager.class)
    public List<Manager> getAllManagers() {
        return managerDao.findAll();
    }


    @GetMapping("/man/{id}")
    @ApiOperation(value = "get Manager by id",
            response = Manager.class)
    public ResponseEntity<Manager> getManagerById(@PathVariable(value = "id") Integer managerId)
            throws ResourceNotFoundException {
        Manager manager = managerDao.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found for this id :: " + managerId));
        return ResponseEntity.ok().body(manager);
    }

    @PostMapping("/mans")
    @ApiOperation(value = "save Manager",
            response = Manager.class)
    public Manager createManager(@Valid @RequestBody Manager manager) {
        return managerDao.save(manager);
    }

    @PutMapping("/manag/{id}")
    @ApiOperation(value = "update Manager",
            response = Manager.class)
    public ResponseEntity<Manager> updateManager(@PathVariable(value = "id") Integer managerId,
                                                 @Valid @RequestBody Manager managerDetails) throws ResourceNotFoundException {
        Manager manager = managerDao.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found for this id :: " + managerId));

        manager.setFirstname(managerDetails.getFirstname());

        final Manager updatedExpert = managerDao.save(manager);
        return ResponseEntity.ok(updatedExpert);
    }

    @DeleteMapping("/Exp/{id}")
    @ApiOperation(value = "delete Manager by id",
            response = Manager.class)
    public Map<String, Boolean> deleteManager(@PathVariable(value = "id") Integer managerId)
            throws ResourceNotFoundException {
        Manager manager = managerDao.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found for this id :: " + managerId));

        managerDao.delete(manager);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("findM")
    @ApiOperation(value = "find Manager",
            notes = "it use for login page",
            response = Manager.class)
    public Manager findManager(@RequestBody Manager manager) {
        Manager findmanger = managerDao.findByEmailAddressAndPassword(
                manager.getEmailAddress(), manager.getPassword());
        return findmanger != null ? findmanger : new Manager();
    } @PostMapping("findE")
    @ApiOperation(value = "find Expert",
            notes = "it use for login page",
            response = Expert.class)
    public Expert findExpert(@RequestBody Expert expert) {
        Expert findexpert = expertDao.findByEmailAddressAndPassword(
                expert.getEmailAddress(), expert.getPassword());
        return findexpert != null ? findexpert : new Expert();
    } @PostMapping("findC")
    @ApiOperation(value = "find Customer",
            notes = "it use for login page",
            response = Customer.class)
    public Customer findCustomer(@RequestBody Customer customer) {
        Customer findcustomer = customerDao.findByEmailAddressAndPassword(
                customer.getEmailAddress(), customer.getPassword());
        return findcustomer != null ? findcustomer : new Customer();
    }
}
