package com.webapi.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.webapi.exception.ResourseNotFoundException;
import com.webapi.model.Employees;
import com.webapi.repository.EmployeesRepo;
import com.webapi.service.ExcelService;

import org.springframework.data.domain.Sort;

//http://localhost:6060/api/employees/fetch

@RestController
@RequestMapping("/api/")
public class EmployeesController {
	@Autowired
	private EmployeesRepo empRepo;

	@Autowired
	private ExcelService service;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	@GetMapping("employees/download")
	public ResponseEntity<Resource> downloadAllData() throws IOException {
		String filename = now + "_Employees.xlsx";
		ByteArrayInputStream byteStream = service.loadAllData();
		InputStreamResource file = new InputStreamResource(byteStream);

		ResponseEntity<Resource> body = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
		System.out.println("Data downloaded successfully in excel file: " + filename);
		return body;
	}

	@GetMapping("employees/fetch")
	public List<Employees> getAllEmployees() {
		System.out.println("List of All Emp:" + this.empRepo.findAll());
		return this.empRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@GetMapping("employees/{id}")
	public ResponseEntity<Employees> getAllEmployees(@PathVariable("id") int id) throws ResourseNotFoundException {
		Employees employees = empRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Employee not found for this id: " + id));

		System.out.println(empRepo.findById(id));
		return ResponseEntity.ok().body(employees);
	}

	@GetMapping("employees/maxid")
	public Employees getMaxEmployees() {
		return this.empRepo.findTopByOrderByIdDesc().orElse(null);
	}

	@PostMapping("employees/add")
	public Employees addEmployees(@RequestBody Employees employees) {
		empRepo.save(employees);
		return employees;
	}

	@PutMapping("employees/update/{id}")
	public ResponseEntity<Employees> updateOrAddEmployees(@PathVariable(value = "id") int id,
			@Validated @RequestBody Employees employeesDetail) throws ResourseNotFoundException {

		Employees employee = empRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Employee not found for this id: " + id));

		employee.setName(employeesDetail.getName());
		employee.setSalary(employeesDetail.getSalary());
		employee.setAge(employeesDetail.getAge());
		employee.setMobile(employeesDetail.getMobile());
		employee.setEmail(employeesDetail.getEmail());
		employee.setAddress(employeesDetail.getAddress());
		employee.setGender(employeesDetail.getGender());

		return ResponseEntity.ok(this.empRepo.save(employee));
	}

	@DeleteMapping("employees/delete/{id}")
	public Map<String, Boolean> deleteEmployees(@PathVariable(value = "id") int id) throws ResourseNotFoundException {
		Employees employee = empRepo.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("Employee not found for this id: " + id));

		this.empRepo.delete(employee);
		Map<String, Boolean> responce = new HashMap<>();
		responce.put("deleted", Boolean.TRUE);

		return responce;
	}
}
