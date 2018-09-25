package com.example.departmentservice.controller;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;


import com.example.departmentservice.client.EmployeeClient;
import com.example.departmentservice.model.Department;
import com.example.departmentservice.model.Employee;
import com.example.departmentservice.model.OrganizationSummary;
import com.example.departmentservice.service.DepartmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DepartmentController.class, secure = false)
public class DepartmentControllerTest {

	private MockMvc mockMvc;
	
	private StandaloneMockMvcBuilder mockMvcBuilder;

	
	@MockBean
	private DepartmentService departmentService;
	
	@MockBean
	private EmployeeClient employeeClient;
	
	private Employee employee1;
	
	private Employee employee2;
	
	private Employee employee3;
	
	private Department department1;
	
	private Department department2;
	
	private OrganizationSummary organization1;
	
	private OrganizationSummary organization2;
	
	private List<Employee> employees;
	
	private List<Department> departments;
	
	private GsonBuilder gsonBuilder;

	private Gson gson;
	
	@Before
	public void setUp() throws ParseException {
		
		gsonBuilder = new GsonBuilder();  
		gsonBuilder.serializeNulls(); 
		gson = gsonBuilder.registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
		mockMvcBuilder = MockMvcBuilders.standaloneSetup(new DepartmentController(employeeClient,departmentService));
		mockMvc=mockMvcBuilder.build();
		
		String datePattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		employee1=new Employee("Ahmet Kaya", new Date(), "Developer");
		employee1.setId("1");
		Date birthDate = simpleDateFormat.parse("1990-09-09");
		employee1.setBirthDate(birthDate);
		
		
		employee2=new Employee("Mehmet Yilmaz", new Date(), "Manager");
		employee2.setId("2");
		birthDate = simpleDateFormat.parse("1986-09-09");
		employee1.setBirthDate(birthDate);
		
		
		employee3=new Employee("Arda Can", new Date(), "Manager");
		employee3.setId("3");
		birthDate = simpleDateFormat.parse("1982-09-09");
		employee3.setBirthDate(birthDate);
		
		
		
		
		organization1=new OrganizationSummary("1", "org-1");
		organization2=new OrganizationSummary("2", "org-2");
		
		
		department1=new Department(organization1, "dep1");
		department1.setId("1");
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		department1.setEmployees(employees);
		
		
		department2=new Department(organization2, "dep2");
		department2.setId("2");
		employees=new ArrayList<Employee>();
		employees.add(employee2);
		department2.setEmployees(employees);
		
	}
	
	
	@Test
	public void save() throws Exception {
		Mockito.when(
			departmentService.save(Mockito.any(Department.class))).thenReturn(department1);

		
	    String departmentJson = gson.toJson(department1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/departments").accept(
				MediaType.APPLICATION_JSON).content(departmentJson)
				.contentType(MediaType.APPLICATION_JSON);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(departmentJson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
	}
	
	@Test
	public void findByIdFoundWithEmployees() throws Exception {
		
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		
		
		department1.setEmployees(employees);
		
		Mockito.when(
				departmentService.findById(department1.getId())).thenReturn(department1);
		
		Mockito.when(
				employeeClient.findByDepartment(department1.getId(), "AuthorizationValue")).thenReturn(employees);
		
		
	    String departmentson = gson.toJson(department1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/department/"+department1.getId()+"?isWithEmployees=true").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(departmentson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
	}
	
	@Test
	public void findByIdFoundWithoutEmployees() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		
		
		department1.setEmployees(null);
		
		Mockito.when(
				departmentService.findById(department1.getId())).thenReturn(department1);
		
		Mockito.when(
				employeeClient.findByDepartment(department1.getId(), "AuthorizationValue")).thenReturn(employees);
		
		
	    String departmentson = gson.toJson(department1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/department/"+department1.getId()+"?isWithEmployees=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		

		JSONAssert.assertEquals(departmentson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	@Test
	public void findByIdNofound() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		
		
		department1.setEmployees(null);
		
		Mockito.when(
				departmentService.findById(department1.getId())).thenReturn(null);
		
		Mockito.when(
				employeeClient.findByDepartment(department1.getId(), "AuthorizationValue")).thenReturn(employees);
		
		
	    String departmentson = gson.toJson(department1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/department/"+department1.getId()+"?isWithEmployees=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		Assert.assertNotEquals(departmentson, result.getResponse()
				.getContentAsString());
		
		Assert.assertEquals("", result.getResponse()
				.getContentAsString());
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.NOT_FOUND.value());
		
		
		
	}
	
	
	@Test
	public void findAll() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		
		
		department1.setEmployees(null);
		department2.setEmployees(null);
		
		departments=new ArrayList<Department>();
		departments.add(department1);
		departments.add(department2);
		
		Mockito.when(
				departmentService.findAll()).thenReturn(departments);
		
		
		
	    String departmentsjson = gson.toJson(departments);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/departments/").accept(
						MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(departmentsjson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	
	@Test
	public void findByOrganizationWithEmployees() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		
		
		department1.setEmployees(employees);
		
		departments=new ArrayList<Department>();
		departments.add(department1);
		
		Mockito.when(
				departmentService.findByOrganizationId(organization1.getId())).thenReturn(departments);
		
		Mockito.when(
				employeeClient.findByDepartment(department1.getId(), "AuthorizationValue")).thenReturn(employees);
		
		
	    String departmentsjson = gson.toJson(departments);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/departments/organization/"+organization1.getId()+"?isWithEmployees=true").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(departmentsjson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	
	@Test
	public void findByOrganizationWithoutEmployees() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		
		
		department1.setEmployees(null);
		
		departments=new ArrayList<Department>();
		departments.add(department1);
		
		Mockito.when(
				departmentService.findByOrganizationId(organization1.getId())).thenReturn(departments);
		
		Mockito.when(
				employeeClient.findByDepartment(department1.getId(), "AuthorizationValue")).thenReturn(employees);
		
		
	    String departmentsjson = gson.toJson(departments);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/departments/organization/"+organization1.getId()+"?isWithEmployees=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		

		JSONAssert.assertEquals(departmentsjson, result.getResponse()
				.getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}


}
