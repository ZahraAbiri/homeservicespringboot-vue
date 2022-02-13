package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Address;
import net.guides.springboot2.crud.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Address getById(Integer addressId) throws ResourceNotFoundException {
        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("address not found for this id :: " + addressId));
        return address;

    }

    @Override
    public Address deleteById(Integer addressId) throws ResourceNotFoundException {
        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + addressId));
        addressDao.delete(address);
        return address;
    }

    @Override
    public Address updateById(Integer addressId, Address addressDetails) throws ResourceNotFoundException {
        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
        address.setCity(addressDetails.getCity());
        address.setHouseNumber(addressDetails.getHouseNumber());
        address.setStreetAddress(addressDetails.getStreetAddress());
        address.setZipCode(addressDetails.getZipCode());
        final Address updatedAddress = addressDao.save(address);
        return updatedAddress;
    }

    @Override
    public List<Address> get() {
        return addressDao.findAll();
    }

    @Override
    public Address findByZipCode(Long zipCode) throws ResourceNotFoundException {
        Optional<Address> optionalAddress = addressDao.findByZipCode(zipCode);
        return optionalAddress.orElseThrow(() -> new ResourceNotFoundException("address not exist!"));

    }
}
