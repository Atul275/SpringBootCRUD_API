package com.webapi.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webapi.exception.ResourseNotFoundException;
import com.webapi.helper.ExcelHelper;
import com.webapi.model.Employees;
import com.webapi.repository.EmployeesRepo;

@Service
public class ExcelService {
	@Autowired
	private EmployeesRepo empRepo;

	public ByteArrayInputStream loadAllData() throws IOException {
		List<Employees> emp = empRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));

		ByteArrayInputStream in = ExcelHelper.databaseToExcel(emp);
		return in;
	}
}
