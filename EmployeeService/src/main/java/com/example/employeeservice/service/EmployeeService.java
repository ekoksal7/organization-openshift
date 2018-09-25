package com.example.employeeservice.service;

import java.util.List;

import com.example.employeeservice.model.Employee;


public interface EmployeeService {
	
	public Employee save(Employee employee);

	public Employee findById(String id);
	
	public List<Employee> findAll();
	
	public List<Employee> findByDepartmentId(String departmentId);
	
	public List<Employee> findByOrganizationId(String organizationId);

}
