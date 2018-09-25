package com.example.organizationservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
    private OrganizationRepository organizationRepository;
	
	@Override
	public Organization save(Organization organization) {
		// TODO Auto-generated method stub
		return organizationRepository.save(organization);
	}

	@Override
	public Organization findById(String id) {
		if(!organizationRepository.findById(id).isPresent()) {
    		
    		return null;
    	}
    	else {

    		return organizationRepository.findById(id).get();
    	}
	}

	@Override
	public List<Organization> findAll() {
		// TODO Auto-generated method stub
		
		List<Organization> target = new ArrayList<>();
		organizationRepository.findAll().forEach(target::add);
		
		return target;
	}


}
