package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Address;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface AddressService {

    public Address save(Address address);
    public Address getById(Integer addressId) throws ResourceNotFoundException;
    public Address deleteById(Integer addressId) throws ResourceNotFoundException;
    public Address updateById(Integer addressId,Address addressDetails) throws ResourceNotFoundException;
    public List<Address> get();

    public Address findByZipCode(Long zipCode) throws ResourceNotFoundException;
}
