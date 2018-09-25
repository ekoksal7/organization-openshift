package com.example.organizationservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.organizationservice.model.Organization;


public interface OrganizationRepository extends CrudRepository<Organization, String> {

}
