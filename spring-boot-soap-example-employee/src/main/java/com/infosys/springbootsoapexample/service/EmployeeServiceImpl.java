package com.infosys.springbootsoapexample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.spring_boot_example.wsdl.AddAddressRequest;
import com.infosys.spring_boot_example.wsdl.AddAddressResponse;
import com.infosys.spring_boot_example.wsdl.DeleteAddressRequest;
import com.infosys.spring_boot_example.wsdl.GetAddressByIdRequest;
import com.infosys.spring_boot_example.wsdl.GetAddressByIdResponse;
import com.infosys.spring_boot_example.wsdl.UpdateAddressRequest;
import com.infosys.spring_boot_example.wsdl.Address1;
import com.infosys.spring_boot_example.wsdl.Employee;
import com.infosys.springbootsoapexample.entity.EmployeeEntity;
import com.infosys.springbootsoapexample.repository.EmployeeRepository;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
public class EmployeeServiceImpl extends WebServiceGatewaySupport implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public List<Employee> getEmployeeDetails(){
		List<EmployeeEntity> list = new ArrayList<>();
		List<Employee> employeeList = new ArrayList<>();
		list = employeeRepository.findAll();
		for (int i = 0; i < list.size(); i++) {
			 Employee employee = new Employee();
			 Address1 address = new Address1();
			 employee.setFirstname(list.get(i).getFirstname());
			 employee.setLastname(list.get(i).getLastname());
			 employee.setGender(list.get(i).getGender());
			 employee.setEmail(list.get(i).getEmail());
			 employee.setPhonenumbers(list.get(i).getPhonenumbers());
			 employee.setSalary(list.get(i).getSalary());
			 employee.setId(list.get(i).getId());
			 GetAddressByIdRequest request = new GetAddressByIdRequest();
			 request.setId(list.get(i).getAddressId());
			 GetAddressByIdResponse response = (GetAddressByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
						request, new SoapActionCallback("http://localhost:8001/soapWS/getAddressByIdRequest"));
			 BeanUtils.copyProperties(response,address);
			 employee.setAddress(address);
			 employeeList.add(employee);    
		}
		return employeeList;
	}
	
	@Override
	public Employee getEmployeeDetails(Integer id) {
		Employee employee = new Employee();
		Address1 address = new Address1();
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		employee.setFirstname(employeeEntity.getFirstname());
		employee.setLastname(employeeEntity.getLastname());
		employee.setGender(employeeEntity.getGender());
		employee.setEmail(employeeEntity.getEmail());
		employee.setPhonenumbers(employeeEntity.getPhonenumbers());
		employee.setSalary(employeeEntity.getSalary());
		employee.setId(employeeEntity.getId());
		GetAddressByIdRequest request = new GetAddressByIdRequest();
		request.setId(employeeEntity.getAddressId());
		GetAddressByIdResponse response = (GetAddressByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8001/soapWS/getAddressByIdRequest"));
		BeanUtils.copyProperties(response,address);
		employee.setAddress(address);;
		return employee;
	}
	
	@Override
	public Integer saveEmployee(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		AddAddressRequest request = new AddAddressRequest();
		request.setCity(employee.getAddress().getCity());
		request.setCountry(employee.getAddress().getCountry());
		request.setZipcode(employee.getAddress().getZipcode());
		AddAddressResponse response = (AddAddressResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8001/soapWS/addAddressRequest"));
		employeeEntity.setAddressId(response.getId());
		Integer id = employeeRepository.saveAndFlush(employeeEntity).getId();
		return id;
	}
	
	@Override
	public String deleteEmployee(Integer id) {
		DeleteAddressRequest request = new DeleteAddressRequest();
		request.setId(employeeRepository.findById(id).get().getAddressId());
		getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8001/soapWS/deleteAddressRequest"));
		employeeRepository.deleteById(id);
		return "Deleted";
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		employeeEntity.setAddressId(employee.getAddress().getId());
		UpdateAddressRequest request = new UpdateAddressRequest();
		com.infosys.spring_boot_example.wsdl.Address address  = new com.infosys.spring_boot_example.wsdl.Address();
		address.setCity(employee.getAddress().getCity());
		address.setCountry(employee.getAddress().getCountry());
		address.setId(employee.getAddress().getId());
		address.setZipcode(employee.getAddress().getZipcode());
		request.setAddress(address);
		getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8001/soapWS/updateAddressRequest"));
		employeeRepository.saveAndFlush(employeeEntity).getId();
	}
}
