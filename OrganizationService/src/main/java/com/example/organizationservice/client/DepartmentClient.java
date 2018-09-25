package com.example.organizationservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.organizationservice.model.Department;

@FeignClient(name = "apiGateway")
public interface DepartmentClient {

	@GetMapping("/department-service/departments/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") String organizationId,
											   @RequestHeader("Authorization") String authHeader,
											   @RequestParam boolean isWithEmployees);
	
}
