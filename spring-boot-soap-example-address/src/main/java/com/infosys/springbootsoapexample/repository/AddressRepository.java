package com.infosys.springbootsoapexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.springbootsoapexample.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}
