package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Address;


public interface AddressService {

    public Address save(Address address);

    public Address findByZipCode(Long zipCode) throws ResourceNotFoundException;
}
