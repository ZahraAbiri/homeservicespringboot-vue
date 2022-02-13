package net.guides.springboot2.crud.controller;//package net.guides.springboot2.crud.controller;

import io.swagger.annotations.ApiOperation;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Address;
import net.guides.springboot2.crud.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/add")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/ad")
    @ApiOperation(value = "get address ",
            response = Address.class)
    public List<Address> getAllAddress() {
        return addressService.get();
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Integer addressId) throws ResourceNotFoundException {
        Address address = addressService.getById(addressId);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping("/address")
    @ApiOperation(value = "save address ",
            response = Address.class)
    public Address createAddress(@RequestBody Address address) {
        return addressService.save(address);
    }

    @PutMapping("/add/{id}")
    @ApiOperation(value = "update address ",
            response = Address.class)
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Integer addressId,
                                                 @RequestBody Address addressDetails) throws ResourceNotFoundException {
        Address address = addressService.updateById(addressId, addressDetails);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/addres/{id}")
    @ApiOperation(value = "delete address ",
            response = Address.class)
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer addressId)
            throws ResourceNotFoundException {
        Address address = addressService.deleteById(addressId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
