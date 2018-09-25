package com.example.organizationservice.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.organizationservice.client.DepartmentClient;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.service.OrganizationService;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private DepartmentClient departmentClient;
	
	
	public OrganizationController(DepartmentClient departmentClient,OrganizationService organizationService) {
		this.departmentClient=departmentClient;
		this.organizationService=organizationService;
	}
	
	@PostMapping("/organizations")
	public Organization save(@RequestBody Organization organization) {
		LOGGER.info("Organization add: {}", organization);
		return organizationService.save(organization);
	}
	
	@GetMapping("/organizations")
	public List<Organization> findAll(@RequestParam boolean isWithDepartments,
										@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("Organization find");
		
		List<Organization> organizations = organizationService.findAll();
		if(isWithDepartments)
			organizations.forEach(o -> o.setDepartments(departmentClient.findByOrganization(o.getId(),authHeader,false)));
		
		return organizations;
	}
	
	@GetMapping("/organization/{id}")
	public @ResponseBody ResponseEntity findById(@PathVariable("id") String id, 
												 @RequestParam boolean isWithDepartments,
												 @RequestParam boolean isWithEmployees,
												 @RequestHeader("Authorization") String authHeader) {
		
		LOGGER.info("Organization find: id={}", id);

		Organization organization=organizationService.findById(id);
		
    	
    	if(organization==null) {
    		return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND);
    	}
    	else {
    		
    		if(isWithDepartments) {
    			organization.setDepartments(departmentClient.findByOrganization(id,authHeader,isWithEmployees));
    		}
    		return new ResponseEntity<Organization>(organization,HttpStatus.OK);
    	}	
		
		
	}
}
