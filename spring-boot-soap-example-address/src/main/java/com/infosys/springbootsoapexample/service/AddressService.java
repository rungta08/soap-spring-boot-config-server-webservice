package com.infosys.springbootsoapexample.service;

import com.infosys.spring_boot_soap_example_address.Address;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface AddressService {
	
	public List<Address> getAddressDetails();
	public Address getAddressDetails(Integer id);
	public Integer saveAddress(Address address);
	public String deleteAddress(Integer id);
	public void updateAddress(Address address);
}
