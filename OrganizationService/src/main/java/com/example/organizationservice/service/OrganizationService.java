package com.example.organizationservice.service;

import java.util.List;

import com.example.organizationservice.model.Organization;


public interface OrganizationService {
	
	public Organization save(Organization organization);

	public Organization findById(String id);
	
	public List<Organization> findAll();
	
}
