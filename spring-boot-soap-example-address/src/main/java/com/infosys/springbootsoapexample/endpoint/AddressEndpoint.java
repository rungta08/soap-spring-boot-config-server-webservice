package com.infosys.springbootsoapexample.endpoint;


import com.infosys.spring_boot_soap_example_address.GetAddressByIdRequest;
import com.infosys.spring_boot_soap_example_address.GetAddressByIdResponse;
import com.infosys.spring_boot_soap_example_address.GetAddressResponse;
import com.infosys.spring_boot_soap_example_address.ServiceStatus;
import com.infosys.spring_boot_soap_example_address.UpdateAddressResponse;
import com.infosys.spring_boot_soap_example_address.UpdateAddressRequest;
import com.infosys.spring_boot_soap_example_address.AddAddressRequest;
import com.infosys.spring_boot_soap_example_address.AddAddressResponse;
import com.infosys.spring_boot_soap_example_address.Address;
import com.infosys.spring_boot_soap_example_address.DeleteAddressRequest;
import com.infosys.spring_boot_soap_example_address.DeleteAddressResponse;
import com.infosys.springbootsoapexample.service.AddressServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AddressEndpoint {
	
	private static final String NAMESPACE_URI = "http://infosys.com/spring-boot-soap-example-address";
	
	@Autowired
    private AddressServiceImpl addressService;


    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getAddressByIdRequest")
    @ResponsePayload
    public GetAddressByIdResponse getAddressByIdRequest(@RequestPayload GetAddressByIdRequest request) {
    	GetAddressByIdResponse response = new GetAddressByIdResponse();
        response.setAddress(addressService.getAddressDetails(request.getId()));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAddressRequest")
	@ResponsePayload
	public GetAddressResponse getAllAddress() {
    	GetAddressResponse response = new GetAddressResponse();
		List<Address> addressList = new ArrayList<>();
		addressList = addressService.getAddressDetails();
		response.getAddress().addAll(addressList);
		return response;
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAddressRequest")
	@ResponsePayload
	public AddAddressResponse addAddress(@RequestPayload AddAddressRequest request) {
    	AddAddressResponse response = new AddAddressResponse();		
    	ServiceStatus serviceStatus = new ServiceStatus();		
    	Address address = new Address();
    	address.setCity(request.getCity());
    	address.setCountry(request.getCountry());
    	address.setZipcode(request.getZipcode());
    	Integer id = addressService.saveAddress(address);
    	response.setId(id);
    	serviceStatus.setStatusCode("SUCCESS");
 	   	serviceStatus.setMessage("Content Added Successfully");
    	response.setServiceStatus(serviceStatus);
        return response;
	}

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAddressRequest")
	@ResponsePayload
	public UpdateAddressResponse updateArticle(@RequestPayload UpdateAddressRequest request) {
    	UpdateAddressResponse response = new UpdateAddressResponse();		
    	ServiceStatus serviceStatus = new ServiceStatus();		
    	Address address = new Address();
    	address = request.getAddress();
    	addressService.saveAddress(address);
    	response.setAddress(address);
    	serviceStatus.setStatusCode("SUCCESS");
 	   	serviceStatus.setMessage("Content Updated Successfully");
    	response.setServiceStatus(serviceStatus);
        return response;
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAddressRequest")
	@ResponsePayload
	public DeleteAddressResponse deleteArticle(@RequestPayload DeleteAddressRequest request) {
		DeleteAddressResponse response = new DeleteAddressResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Integer id = request.getId();
		addressService.deleteAddress(id);
		serviceStatus.setStatusCode("SUCCESS");
 	   	serviceStatus.setMessage("Content Deleted Successfully");
    	response.setServiceStatus(serviceStatus);
		return response;
	}	
}
