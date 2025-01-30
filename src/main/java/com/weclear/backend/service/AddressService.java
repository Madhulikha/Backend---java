package com.weclear.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weclear.backend.model.Address;
import com.weclear.backend.model.User;
import com.weclear.backend.repository.AddressRepository;
import com.weclear.backend.repository.UserRepository;

@Service
public class AddressService {

     @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository; 

    public List<Address> getUserAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address addAddress(Long userId, Address address) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            address.setUser(user);
            return addressRepository.save(address);
        }
        throw new IllegalArgumentException("User not found");
    }

    public Address updateAddress(Long addressId, Address newAddressDetails) {
        return addressRepository.findById(addressId).map(address -> {
            address.setStreet(newAddressDetails.getStreet());
            address.setCity(newAddressDetails.getCity());
            address.setState(newAddressDetails.getState());
            address.setZipCode(newAddressDetails.getZipCode());
            address.setCountry(newAddressDetails.getCountry());
            return addressRepository.save(address);
        }).orElseThrow(() -> new IllegalArgumentException("Address not found"));
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
    
}
