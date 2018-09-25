package com.example.employeeservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.employeeservice.model.DepartmentSummary;
import com.example.employeeservice.model.Employee;
import com.example.employeeservice.model.OrganizationSummary;
import com.example.employeeservice.service.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	private Employee employee1;
	
	private Employee employee2;
	
	private Employee employee3;
	
	private DepartmentSummary department1;
	
	private DepartmentSummary department2;
	
	private OrganizationSummary organization1;
	
	private OrganizationSummary organization2;
	
	private List<Employee> employees;
	
	@Before
	public void setUp() throws ParseException {
		
		String datePattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		department1=new DepartmentSummary("1", "dep-1");
		department2=new DepartmentSummary("2", "dep-2");
		organization1=new OrganizationSummary("1", "org-1");
		organization2=new OrganizationSummary("2", "org-2");
		employee1=new Employee(department1, organization1, "Ahmet Kaya", new Date(), "Developer");
		employee1.setId("1");
		
		
		Date birthDate = simpleDateFormat.parse("1990-09-09");
		employee1.setBirthDate(birthDate);
		
		
		employee2=new Employee(department2, organization2, "Mehmet Yilmaz", new Date(), "Manager");
		employee2.setId("2");
		birthDate = simpleDateFormat.parse("1986-09-09");
		employee1.setBirthDate(birthDate);
		
		
		employee3=new Employee(department1, organization1, "Arda Can", new Date(), "Manager");
		employee3.setId("3");
		birthDate = simpleDateFormat.parse("1982-09-09");
		employee3.setBirthDate(birthDate);
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
	}
	
	@Test
	public void save() throws Exception {
		Mockito.when(
			employeeService.save(Mockito.any(Employee.class))).thenReturn(employee1);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
	    String employeeJson = gson.toJson(employee1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/employees").accept(
				MediaType.APPLICATION_JSON).content(employeeJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(employeeJson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
	}
	
	@Test
	public void findByIdFound() throws Exception {
		
		Mockito.when(
				employeeService.findById(employee1.getId())).thenReturn(employee1);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
	    String employeeJson = gson.toJson(employee1);
	    
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employee/"+employee1.getId()).accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(employeeJson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	@Test
	public void findByIdNotFound() throws Exception {
		
		Mockito.when(
				employeeService.findById(employee1.getId())).thenReturn(null);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
	    String employeeJson = gson.toJson(employee1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employee/"+employee1.getId()).accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		Assert.assertNotEquals(employeeJson, result.getResponse().getContentAsString());
		
		Assert.assertEquals("", result.getResponse().getContentAsString());
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.NOT_FOUND.value());
	
		
	}
	
	@Test
	public void findAll() throws Exception {
		
		Mockito.when(
				employeeService.findAll()).thenReturn(employees);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
	    String employeesJson = gson.toJson(employees);

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employees/").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(employeesJson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
	}
	
	@Test
	public void findByDepartment() throws Exception {
		
		List<Employee> department1Employees=new ArrayList<Employee>();
		department1Employees.add(employee1);
		department1Employees.add(employee3);
		
		Mockito.when(
				employeeService.findByDepartmentId(department1.getId())).thenReturn(department1Employees);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
	    String employeesJson = gson.toJson(department1Employees);

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employees/department/"+department1.getId()).accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		JSONAssert.assertEquals(employeesJson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
	}
	
	@Test
	public void findByOrganizatiopn() throws Exception {
		
		List<Employee> organization2Employees=new ArrayList<Employee>();
		organization2Employees.add(employee2);
		
		Mockito.when(
				employeeService.findByOrganizationId(organization2.getId())).thenReturn(organization2Employees);
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
	    String employeesJson = gson.toJson(organization2Employees);

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employees/organization/"+organization2.getId()).accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(employeesJson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
	}
}
