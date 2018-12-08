package com.infosys.springbootsoapexample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	private Integer Id;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String gender;
	
	@Column
	private Long phonenumbers;
	
	@Column
	private String email;
	
	@Column
	private Long Salary;
	
	@Column
	private Integer AddressId;

	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getPhonenumbers() {
		return phonenumbers;
	}
	public void setPhonenumbers(Long phonenumbers) {
		this.phonenumbers = phonenumbers;
	}
	public Long getSalary() {
		return Salary;
	}
	public void setSalary(Long salary) {
		Salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAddressId() {
		return AddressId;
	}
	public void setAddressId(Integer addressId) {
		AddressId = addressId;
	}
	
	
}
