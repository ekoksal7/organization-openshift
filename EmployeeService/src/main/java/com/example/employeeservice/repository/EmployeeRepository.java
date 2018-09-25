package com.example.employeeservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.employeeservice.model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, String> {

    public List<Employee> findByDepartmentId(String departmentId);
}
