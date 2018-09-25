package com.example.organizationservice.model;

import java.util.Date;

public class Employee {

	private String id;
	private String name;
	private Date birthDate;
	private String position;

	public Employee() {

	}
	
	public Employee(String name, Date birthDate, String position) {
		this.name = name;
		this.birthDate = birthDate;
		this.position = position;
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
		return "Employee [id=" + id + ", name=" + name + ", position=" + position + "]";
	}

}