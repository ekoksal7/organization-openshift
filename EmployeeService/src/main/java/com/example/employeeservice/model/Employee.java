package com.example.employeeservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employees")
public class Employee {

	@Id
	private String id;
	private DepartmentSummary department;
	private OrganizationSummary organization;
	private String name;
	private Date birthDate;
	private String position;

	public Employee() {

	}
	
	public Employee(DepartmentSummary department, OrganizationSummary organization, String name, Date birthDate, String position) {
		this.department = department;
		this.organization = organization;
		this.name = name;
		this.birthDate=birthDate;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DepartmentSummary getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentSummary department) {
		this.department = department;
	}

	public OrganizationSummary getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationSummary organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", organization=" + organization + ", department=" + department
				+ ", name=" + name + ", position=" + position + "]";
	}

}
