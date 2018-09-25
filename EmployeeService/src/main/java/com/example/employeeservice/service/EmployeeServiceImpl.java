package com.example.employeeservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;
	
	@Override
	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public Employee findById(String id) {
		if(!employeeRepository.findById(id).isPresent()) {
    		
    		return null;
    	}
    	else {

    		return employeeRepository.findById(id).get();
    	}
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		
		List<Employee> target = new ArrayList<>();
		employeeRepository.findAll().forEach(target::add);
		
		return target;
	}

	@Override
	public List<Employee> findByDepartmentId(String departmentId) {
		// TODO Auto-generated method stub
		return employeeRepository.findByDepartmentId(departmentId);
	}

	@Override
	public List<Employee> findByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return employeeRepository.findByDepartmentId(organizationId);
	}

}
