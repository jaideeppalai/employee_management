package com.javaguides.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.ems.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{

}
