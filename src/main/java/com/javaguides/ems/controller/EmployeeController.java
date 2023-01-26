package com.javaguides.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.ems.bean.EmployeeResponse;
import com.javaguides.ems.bean.Employeerequest;
import com.javaguides.ems.service.EmployeeService;


import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeController {
	
  @Autowired
	private EmployeeService employeeService;
	
	@PostMapping(value="/saveEmployee")
	public ResponseEntity saveEmpployee(@RequestBody Employeerequest employeeRequest) {
		return employeeService.saveEmployee(employeeRequest);
	}
	
	@GetMapping(value="getEmployee")
	public List<EmployeeResponse> getEmployee(){
	
		return employeeService.getEmployee();
		
		
	}

}
