package com.example.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.departmentservice.client.EmployeeClient;
import com.example.departmentservice.model.Department;
import com.example.departmentservice.service.DepartmentService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class DepartmentController {
private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeClient employeeClient;
	
	@PostMapping("/departments")
	public Department save(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return departmentService.save(department);
	}
	
	public DepartmentController(EmployeeClient employeeClient,DepartmentService departmentservice) {
		this.employeeClient=employeeClient;
		this.departmentService=departmentservice;
	}
	
	@GetMapping("/department/{id}")
	public @ResponseBody ResponseEntity findById(@PathVariable("id") String id,
			                                     @RequestParam boolean isWithEmployees,
			                                     @RequestHeader("Authorization") String authHeader) {
		LOGGER.info("Department find: id={}", id);
		
		Department department=departmentService.findById(id);
		
    	
    	if(department==null) {
    		return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
    	}
    	else {
    		
    		if(isWithEmployees)
    			department.setEmployees(employeeClient.findByDepartment(id,authHeader));
    		
    		return new ResponseEntity<Department>(department,HttpStatus.OK);
    	}	
		
	}
	
	@GetMapping("/departments")
	public List<Department> findAll() {
		LOGGER.info("Department find");
		
		
		return departmentService.findAll();
	}
	
	
	@GetMapping("/departments/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") String organizationId,
												@RequestHeader("Authorization") String authHeader,
												@RequestParam boolean isWithEmployees) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		
		List<Department> departments = departmentService.findByOrganizationId(organizationId);
		if(isWithEmployees)
			departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId(),authHeader)));
		
		return departments;
	}

}
