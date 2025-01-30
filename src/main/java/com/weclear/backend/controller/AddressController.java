package com.weclear.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weclear.backend.model.Address;
import com.weclear.backend.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
     @Autowired
    private AddressService addressService;

    @GetMapping("/{userId}")
    public List<Address> getUserAddresses(@PathVariable Long userId) {
        return addressService.getUserAddresses(userId);
    }

    @PostMapping("/{userId}")
    public Address addAddress(@PathVariable Long userId, @RequestBody Address address) {
        return addressService.addAddress(userId, address);
    }

    @PutMapping("/{addressId}")
    public Address updateAddress(@PathVariable Long addressId, @RequestBody Address newAddressDetails) {
        return addressService.updateAddress(addressId, newAddressDetails);
    }

    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
    }
}
