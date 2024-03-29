package com.webapi.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.webapi.model.Employees;

public class ExcelHelper {
	  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Id","Name","Age","Gender","Salary","Mobile","Email","Address" };
	  static String SHEET = "employees";

	  public static ByteArrayInputStream databaseToExcel(List<Employees> employees) throws IOException {
	    
		  Workbook workbook = new XSSFWorkbook(); 
		  ByteArrayOutputStream out = new ByteArrayOutputStream();
		  try {
	    	
	    	Sheet sheet = workbook.createSheet(SHEET);

	    	// Header
	    	Row headerRow = sheet.createRow(0);

	    	for (int col = 0; col < HEADERs.length; col++) {
	    		Cell cell = headerRow.createCell(col);
	    		cell.setCellValue(HEADERs[col]);
	    	}

	    	int rowIdx = 1;
	    	for (Employees emp: employees) {
	    		Row row = sheet.createRow(rowIdx);
	    		rowIdx++;

	    		row.createCell(0).setCellValue(emp.getId());
	    		row.createCell(1).setCellValue(emp.getName());
	    		row.createCell(2).setCellValue(emp.getAge());
	    		row.createCell(3).setCellValue(emp.getGender());
	    		row.createCell(4).setCellValue(emp.getSalary());
	    		row.createCell(5).setCellValue(emp.getMobile());
	    		row.createCell(6).setCellValue(emp.getEmail());
	    		row.createCell(7).setCellValue(emp.getAddress());
	    	}
	    	workbook.write(out);
	    	return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    } finally {
	    	workbook.close();
	    	out.close();
	    	System.out.println("Finally block executed.");
	    	
		}
	  }
	}