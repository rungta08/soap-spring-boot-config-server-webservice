package com.infosys.springbootsoapexample.endpoint;



import com.infosys.spring_boot_example.wsdl.UpdateEmployeeRequest;
import com.infosys.spring_boot_example.wsdl.UpdateEmployeeResponse;
import com.infosys.spring_boot_example.wsdl.GetEmployeeByIdRequest;
import com.infosys.spring_boot_example.wsdl.GetEmployeeByIdResponse;
import com.infosys.spring_boot_example.wsdl.GetEmployeeResponse;
import com.infosys.spring_boot_example.wsdl.AddEmployeeRequest;
import com.infosys.spring_boot_example.wsdl.AddEmployeeResponse;
import com.infosys.spring_boot_example.wsdl.DeleteEmployeeRequest;
import com.infosys.spring_boot_example.wsdl.DeleteEmployeeResponse;
import com.infosys.spring_boot_example.wsdl.Employee;
import com.infosys.springbootsoapexample.service.EmployeeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {
	
	private static final String NAMESPACE_URI = "http://infosys.com/spring-boot-soap-example-employee";
	
	@Autowired
    private EmployeeServiceImpl employeeService;


    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployeeByIdRequest(@RequestPayload GetEmployeeByIdRequest request) {
    	GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        response.setEmployee(employeeService.getEmployeeDetails(request.getId()));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeRequest")
	@ResponsePayload
	public GetEmployeeResponse getAllAddress() {
    	GetEmployeeResponse response = new GetEmployeeResponse();
		List<Employee> employeeList = new ArrayList<>();
		employeeList = employeeService.getEmployeeDetails();
		response.getEmployee().addAll(employeeList);
		return response;
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addAddress(@RequestPayload AddEmployeeRequest request) {
    	AddEmployeeResponse response = new AddEmployeeResponse();			
    	Employee employee = new Employee();
    	employee.setFirstname(request.getFirstname());
    	employee.setLastname(request.getLastname());
    	employee.setGender(request.getGender());
    	employee.setEmail(request.getEmail());
    	employee.setPhonenumbers(request.getPhonenumbers());
    	employee.setSalary(request.getSalary());
    	employee.setAddress(request.getAddress());
    	Integer id = employeeService.saveEmployee(employee);
    	response.setId(id);
        return response;
	}

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateArticle(@RequestPayload UpdateEmployeeRequest request) {
    	UpdateEmployeeResponse response = new UpdateEmployeeResponse();				
    	Employee employee = new Employee();
    	employee = request.getEmployee();
    	employeeService.saveEmployee(employee);
    	response.setEmployee(employee);
        return response;
	}
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteArticle(@RequestPayload DeleteEmployeeRequest request) {
    	DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		Integer id = request.getId();
		employeeService.deleteEmployee(id);
		return response;
	}	
}
