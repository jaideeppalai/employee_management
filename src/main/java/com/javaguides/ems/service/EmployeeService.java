package com.javaguides.ems.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javaguides.ems.bean.EmployeeResponse;
import com.javaguides.ems.bean.Employeerequest;
import com.javaguides.ems.entity.Employee;
import com.javaguides.ems.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
@Autowired
private EmployeeRepo employeeRepo;
	
	public ResponseEntity saveEmployee(Employeerequest employeeRequest) {
		ResponseEntity response;
	 Employee employee=new Employee();
	 try {
	 employee.setEmployeeId(employeeRequest.getEmployeeid());
	 employee.setFirstName(employeeRequest.getFirstName());
	 employee.setLastName(employeeRequest.getLastName());
	 employee.setEmail(employeeRequest.getEmail());
	 employee.setPhoneNumber(employeeRequest.getPhoneNumber());
	 employee.setAlternatePhoneNumber(employeeRequest.getAlternatePhoneNumber());
	 employee.setDoj(employeeRequest.getDoj());
	 employee.setSalary(employeeRequest.getSalary());
	 employeeRepo.save(employee);
	 response = new ResponseEntity<>("Employee data saved",HttpStatus.CREATED);
	 }catch(Exception e){
		response = new ResponseEntity<>("Error occured",HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 return response;
	}
	 
	 public List<EmployeeResponse> getEmployee() {
		 List<Employee> employeeList=employeeRepo.findAll();
		 List<EmployeeResponse> responseList= new ArrayList<EmployeeResponse>();
		 for(Employee e: employeeList) {
			 EmployeeResponse response=new EmployeeResponse();
			 response.setEmployeeCode(e.getEmployeeId());
			 response.setFirstName(e.getFirstName());
			 response.setLastName(e.getLastName());
		
			 EmployeeResponse newresponse=this.calculateTax(e);
			 response.setTaxAmount(newresponse.getTaxAmount());
			 response.setYearlySalary(newresponse.getYearlySalary());
			 response.setCessAmout(newresponse.getCessAmout());
	responseList.add(response);		 
		 }
	 return responseList;
	
		 
	}
	 
	 public EmployeeResponse calculateTax(Employee employee) {
		 EmployeeResponse employeeRespone=new EmployeeResponse();
		 Double totalSalary=0.0;
		 int currentyear= LocalDate.now().getYear();
		 LocalDate mar= LocalDate.now().withMonth(3).withDayOfMonth(31);
		 DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDate joiningDate= LocalDate.parse(employee.getDoj(),formatter);
		 if(joiningDate.isBefore(mar)&& joiningDate.isAfter(mar.minusYears(1))) {
	     Long days= ChronoUnit.DAYS.between(joiningDate, mar);
	     Double perDaySalary= employee.getSalary()/30;
	     totalSalary= days*perDaySalary;
			 
		 }else {
			 totalSalary= employee.getSalary()*12;
			 
		 }
		 Double taxAmount =0.0 ;
		 Double cessAmount=0.0;
		 if (totalSalary > 1000000) {
             taxAmount = (totalSalary - 1000000) * 0.2 + (500000 - 250000) * 0.1 + (250000) * 0.05;
         } else if (totalSalary > 500000) {
             taxAmount = (totalSalary - 500000) * 0.1 + (250000) * 0.05;
   
         } else if (totalSalary > 250000) {
             taxAmount = (totalSalary - 250000) * 0.05;
         }
		 
		 if(totalSalary>2500000)
			 cessAmount=(totalSalary-2500000)* 0.02;
		 employeeRespone.setYearlySalary(totalSalary);
		 employeeRespone.setCessAmout(cessAmount);
		 employeeRespone.setTaxAmount(taxAmount);
		 return employeeRespone;
		 
	 }
	 
	 
	 
	 
}
