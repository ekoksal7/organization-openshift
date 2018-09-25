package com.example.employeeservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.service.EmployeeService;

@RestController
public class EmployeeController {
private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return employeeService.save(employee);
	}
	
	@GetMapping("/employee/{id}")
	public @ResponseBody ResponseEntity findById(@PathVariable("id") String id) {
		LOGGER.info("Employee find: id={}", id);		
		
		Employee employee=employeeService.findById(id);
    	
    	if(employee==null) {
    		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    	}
    	else {
    		return new ResponseEntity<Employee>(employeeService.findById(id),HttpStatus.OK);
    	}	
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		LOGGER.info("Employee find");
		return employeeService.findAll();
		
	}
	
	
	@GetMapping("/employees/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return employeeService.findByDepartmentId(departmentId);
	}
	
	@GetMapping("/employees/organization/{organizationId}")
	public List<Employee> findByOrganization(@PathVariable("organizationId") String organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return employeeService.findByOrganizationId(organizationId);
	}

}
