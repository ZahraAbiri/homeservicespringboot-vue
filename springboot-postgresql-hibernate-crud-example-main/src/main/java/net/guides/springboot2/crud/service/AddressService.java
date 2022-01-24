package net.guides.springboot2.crud.service;


import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.model.Address;
import net.guides.springboot2.crud.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    @Autowired
    private  AddressDao addressRepository;

    public Address save(Address address) {
        Optional<Address> foundedAddress = addressRepository.findByZipCode(address.getZipCode());
        return foundedAddress.orElseGet(() -> addressRepository.save(address));
    }

    public Address findByZipCode(Long zipCode) {
        Optional<Address> address = addressRepository.findByZipCode(zipCode);
        return address.orElseThrow(() -> new RuntimeException("address not exist!"));
    }
}
