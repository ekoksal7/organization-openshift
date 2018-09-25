package com.example.departmentservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.departmentservice.model.Employee;

@FeignClient(name = "apiGateway")
public interface EmployeeClient {

	@GetMapping("/employee-service/employees/department/{departmentId}")
	List<Employee> findByDepartment(@PathVariable("departmentId") String departmentId,
									@RequestHeader("Authorization") String authHeader );
	
}
