package com.infosys.springbootsoapexample.service;

import com.infosys.spring_boot_example.wsdl.Employee;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface EmployeeService {
	
	public List<Employee> getEmployeeDetails();
	public Employee getEmployeeDetails(Integer id);
	public Integer saveEmployee(Employee employee);
	public String deleteEmployee(Integer id);
	public void updateEmployee(Employee employee);
}
