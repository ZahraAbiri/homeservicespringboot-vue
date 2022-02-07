package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Address;
import net.guides.springboot2.crud.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public Address save(Address address) {
        Optional<Address> foundedAddress = addressDao.findByZipCode(address.getZipCode());
        Address savedAddress = foundedAddress.orElseGet(() -> addressDao.save(address));
        return savedAddress;
    }

    @Override
    public Address findByZipCode(Long zipCode) throws ResourceNotFoundException {
        Optional<Address> optionalAddress = addressDao.findByZipCode(zipCode);
      return   optionalAddress.orElseThrow(() -> new ResourceNotFoundException("address not exist!"));

    }
}
