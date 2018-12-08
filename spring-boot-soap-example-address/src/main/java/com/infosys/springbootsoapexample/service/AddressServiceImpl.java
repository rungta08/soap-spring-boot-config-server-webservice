package com.infosys.springbootsoapexample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.spring_boot_soap_example_address.Address;
import com.infosys.springbootsoapexample.entity.AddressEntity;
import com.infosys.springbootsoapexample.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	@Override
	public List<Address> getAddressDetails(){
		List<AddressEntity> list = new ArrayList<>();
		List<Address> addressList = new ArrayList<>();
		list = addressRepository.findAll();
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			 Address address = new Address();
		     BeanUtils.copyProperties(list.get(i), address);
		     addressList.add(address);    
		}
		return addressList;
	}
	
	@Override
	public Address getAddressDetails(Integer id) {
		Address address = new Address();
		AddressEntity addressEntity = addressRepository.findById(id).get();
		BeanUtils.copyProperties(addressEntity, address);
		return address;
	}
	
	@Override
	public Integer saveAddress(Address address) {
		AddressEntity addressEntity = new AddressEntity();
		BeanUtils.copyProperties(address, addressEntity);
		Integer id = addressRepository.saveAndFlush(addressEntity).getId();
		return id;
	}
	
	@Override
	public String deleteAddress(Integer id) {
		addressRepository.deleteById(id);
		return "Deleted";
	}
	
	@Override
	public void updateAddress(Address address) {
		AddressEntity addressEntity = new AddressEntity();
		BeanUtils.copyProperties(address, addressEntity);
		addressRepository.save(addressEntity);
	}
}
