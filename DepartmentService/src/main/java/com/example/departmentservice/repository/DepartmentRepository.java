package com.example.departmentservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.departmentservice.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, String> {

	public List<Department> findByOrganizationId(String organizationId);
}
