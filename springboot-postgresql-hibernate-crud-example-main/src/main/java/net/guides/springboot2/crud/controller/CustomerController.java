package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;
//

import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.dto.Customerdto;
import net.guides.springboot2.crud.dto.PaymentDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Payment;
import net.guides.springboot2.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cus")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @GetMapping("/custom")
    @ApiOperation(value = "get all customers",
            notes = "return list of customers",
            response = Customer.class)
    public List<Customer> getAllCustomer() {
        return customerService.get();
    }

    @GetMapping("/customer/{id}")
    @ApiOperation(value = "get customer by id",
            notes = "return list of customers",
            response = Customer.class)
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer customerId)
            throws ResourceNotFoundException {
        Customer customer = customerService.getById(customerId);
        return ResponseEntity.ok().body(customer);
    }

//    @PostMapping("/cu")
//    @ApiOperation(value = "save customer use dto",
//            response = Customer.class)
//    public Customer createCustomr(@RequestBody Customerdto customerdto) throws ResourceNotFoundException {
//        return customerService.save(customerdto);
//    }


    @PutMapping("/custom/{id}")
    @ApiOperation(value = "update  customer by id",
            response = Customer.class)
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Integer customerId,
                                                   @RequestBody Customer custommerDetails) throws ResourceNotFoundException {
        Customer updatedCustomer = customerService.updateById(customerId, custommerDetails);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/custom/{id}")
    @ApiOperation(value = "delete customer by id",
            response = Customer.class)
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Integer customerId)
            throws ResourceNotFoundException {
        Customer customer = customerService.deleteById(customerId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/payByCard")
    @ApiOperation(value = "customer pay order use credit card",
            response = PaymentDto.class)
    public Payment paymentByCard(PaymentDto paymentDto) throws ResourceNotFoundException {
        return customerService.paymentByCard(paymentDto);

    }

    @PostMapping("/payByCredit")
    @ApiOperation(value = "customer pay order by own credit credit ",
            response = PaymentDto.class)
    public Payment payByCredit(PaymentDto paymentDto) throws ResourceNotFoundException {
        return customerService.paymentByCredit(paymentDto);


    }
}
