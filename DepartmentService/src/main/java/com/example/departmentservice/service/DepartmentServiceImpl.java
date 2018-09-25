package com.example.departmentservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.departmentservice.model.Department;
import com.example.departmentservice.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
    private DepartmentRepository departmentRepository;
	
	@Override
	public Department save(Department department) {
		// TODO Auto-generated method stub
		return departmentRepository.save(department);
	}

	@Override
	public Department findById(String id) {
		if(!departmentRepository.findById(id).isPresent()) {
    		
    		return null;
    	}
    	else {

    		return departmentRepository.findById(id).get();
    	}
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		
		List<Department> target = new ArrayList<>();
		departmentRepository.findAll().forEach(target::add);
		
		return target;
	}

	@Override
	public List<Department> findByOrganizationId(String organizationId) {
		// TODO Auto-generated method stub
		return departmentRepository.findByOrganizationId(organizationId);
	}

}
