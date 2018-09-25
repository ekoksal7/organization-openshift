package com.example.departmentservice.service;

import java.util.List;

import com.example.departmentservice.model.Department;

public interface DepartmentService {

	public Department save(Department department);

	public Department findById(String id);
	
	public List<Department> findAll();
	
	public List<Department> findByOrganizationId(String organizationId);

}
