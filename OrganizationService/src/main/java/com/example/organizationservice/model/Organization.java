package com.example.organizationservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Organizations")
public class Organization {
	
	@Id
	private String id;
	private String name;
	
	@Transient
	private List<Department> departments;


	public Organization() {

	}
	public Organization(String name) {
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

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + "]";
}
}
