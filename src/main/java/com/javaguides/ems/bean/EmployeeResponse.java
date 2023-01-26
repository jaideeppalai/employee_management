package com.javaguides.ems.bean;

public class EmployeeResponse {
	//employee code, first name, last name, yearly salary, tax amount, cess amount
	private Long employeeCode;
	private String firstName;
	private String LastName;
	private double yearlySalary;
	private double taxAmount;
	private double cessAmout;
	public Long getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(Long employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public double getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getCessAmout() {
		return cessAmout;
	}
	public void setCessAmout(double cessAmout) {
		this.cessAmout = cessAmout;
	}
	
}