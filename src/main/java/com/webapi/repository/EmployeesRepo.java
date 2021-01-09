package com.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapi.model.Employees;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Integer>{

}
