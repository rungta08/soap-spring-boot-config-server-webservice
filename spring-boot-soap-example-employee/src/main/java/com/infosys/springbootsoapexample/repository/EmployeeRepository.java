package com.infosys.springbootsoapexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.springbootsoapexample.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
