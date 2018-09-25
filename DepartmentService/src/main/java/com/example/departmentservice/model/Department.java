package com.example.departmentservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Departments")
public class Department {

	@Id
	private String id;
	private OrganizationSummary organization;
	private String name;
	
	@Transient
	private List<Employee> employees;

	public Department() {
		
	}

	public Department(OrganizationSummary organization, String name) {
		super();
		this.organization = organization;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	

	public OrganizationSummary getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationSummary organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", organization=" + organization + "]";
	}

}
