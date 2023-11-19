package com.webapi.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.webapi.exception.ResourseNotFoundException;
import com.webapi.model.Employees;
import com.webapi.repository.EmployeesRepo;

import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/")
public class EmployeesController {
	@Autowired
	private EmployeesRepo empRepo;
	
	@GetMapping("employees/fetch")
	public List<Employees> getAllEmployees() {
		System.out.println("List of All Emp:" + this.empRepo.findAll());
		return this.empRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employees> getAllEmployees(@PathVariable("id") int id) throws ResourseNotFoundException {
		Employees employees = empRepo.findById(id).orElseThrow(() ->
			new ResourseNotFoundException("Employee not found for this id: " +id));
		
		System.out.println(empRepo.findById(id));
		return ResponseEntity.ok().body(employees);
	}
	
	@PostMapping("employees/add")
	public Employees addEmployees(@RequestBody Employees employees) {
		empRepo.save(employees);
		return employees;
	}
	
	@PutMapping("employees/update/{id}")
	public ResponseEntity<Employees> updateOrAddEmployees(@PathVariable(value = "id") int id, 
			@Validated @RequestBody Employees employeesDetail) throws ResourseNotFoundException {
		
		Employees employee = empRepo.findById(id).orElseThrow(() ->
			new ResourseNotFoundException("Employee not found for this id: " +id));
		
		employee.setName(employeesDetail.getName());
		employee.setSalary(employeesDetail.getSalary());
		employee.setAge(employeesDetail.getAge());
		employee.setMobile(employeesDetail.getMobile());
		employee.setEmail(employeesDetail.getEmail());
		employee.setAddress(employeesDetail.getAddress());
		
		return ResponseEntity.ok(this.empRepo.save(employee));
	}
	
	@DeleteMapping("employees/delete/{id}")
	public Map<String, Boolean> deleteEmployees(@PathVariable(value = "id") int id) throws ResourseNotFoundException {
		Employees employee = empRepo.findById(id).orElseThrow(() ->
			new ResourseNotFoundException("Employee not found for this id: " +id));
		
		this.empRepo.delete(employee);
		Map<String, Boolean> responce = new HashMap<>();
		responce.put("deleted", Boolean.TRUE);
		
		return responce;
	}
}
