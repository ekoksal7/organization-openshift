package com.example.organizationservice.controller;

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

import com.example.organizationservice.client.DepartmentClient;
import com.example.organizationservice.model.Department;
import com.example.organizationservice.model.Employee;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.service.OrganizationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@RunWith(SpringRunner.class)
@WebMvcTest(value = OrganizationController.class, secure = false)
public class OrganizationControllerTest {
	
private MockMvc mockMvc;
	
	private StandaloneMockMvcBuilder mockMvcBuilder;

	
	@MockBean
	private OrganizationService organizationService;
	
	@MockBean
	private DepartmentClient departmentClient;
	
	private Employee employee1;
	
	private Employee employee2;
	
	private Employee employee3;
	
	private Department department1;
	
	private Department department2;
	
	private Organization organization1;
	
	private Organization organization2;
	
	private List<Employee> employees;
	
	private List<Department> departments;
	
	private List<Organization> organizations;
	
	private GsonBuilder gsonBuilder;

	private Gson gson;
	
	@Before
	public void setUp() throws ParseException {
		
		gsonBuilder = new GsonBuilder();  
		gsonBuilder.serializeNulls(); 
		gson = gsonBuilder.registerTypeAdapter(Date.class, new GmtDateTypeAdapter()).create();
		
		mockMvcBuilder = MockMvcBuilders.standaloneSetup(new OrganizationController(departmentClient,organizationService));
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
		
		
		
		
		organization1=new Organization("org-1");
		organization1.setId("1");
		organization2=new Organization("org-2");
		organization2.setId("2");
		
		
		department1=new Department("dep1");
		department1.setId("1");
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		department1.setEmployees(employees);
		
		
		department2=new Department("dep2");
		department2.setId("2");
		employees=new ArrayList<Employee>();
		employees.add(employee2);
		department2.setEmployees(employees);
		
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(departments);
		
		departments=new ArrayList<Department>();
		departments.add(department2);
		organization2.setDepartments(departments);
		
		
		organizations=new ArrayList<Organization>();
		organizations.add(organization1);
		organizations.add(organization2);
		
	}
	
	@Test
	public void save() throws Exception {
		
		organization1.setDepartments(null);
		Mockito.when(
			organizationService.save(Mockito.any(Organization.class))).thenReturn(organization1);

		
	    String organizationJson = gson.toJson(organization1);
	    
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/organizations").accept(
				MediaType.APPLICATION_JSON).content(organizationJson)
				.contentType(MediaType.APPLICATION_JSON);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(organizationJson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
	}
	
	@Test
	public void findAllWithDepartments() throws Exception {
		
		department1.setEmployees(null);
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(departments);
		
		organizations=new ArrayList<Organization>();
		organizations.add(organization1);
		
		Mockito.when(
				organizationService.findAll()).thenReturn(organizations);
		
		Mockito.when(
				departmentClient.findByOrganization(organization1.getId(), "AuthorizationValue",false)).thenReturn(departments);
		
		
	    String organizationsjson = gson.toJson(organizations);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/organizations?isWithDepartments=true").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(organizationsjson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	@Test
	public void findAllWithoutDepartments() throws Exception {
		
		department1.setEmployees(null);
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(null);
		
		organizations=new ArrayList<Organization>();
		organizations.add(organization1);
		
		Mockito.when(
				organizationService.findAll()).thenReturn(organizations);
		
		Mockito.when(
				departmentClient.findByOrganization(organization1.getId(), "AuthorizationValue",false)).thenReturn(departments);
		
		
	    String organizationsjson = gson.toJson(organizations);
	    
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/organizations?isWithDepartments=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(organizationsjson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	@Test
	public void findByIdWithDepartmentsAndWithEmployees() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		department1.setEmployees(employees);
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(departments);
		
		
		Mockito.when(
				organizationService.findById(organization1.getId())).thenReturn(organization1);
		
		Mockito.when(
				departmentClient.findByOrganization(organization1.getId(), "AuthorizationValue",true)).thenReturn(departments);
		
		
	    String organizationjson = gson.toJson(organization1);
	    
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/organization/"+organization1.getId()+"?isWithDepartments=true&isWithEmployees=true").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(organizationjson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	
	@Test
	public void findByIdWithDepartmentsAndWithoutEmployees() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		department1.setEmployees(null);
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(departments);
		
		
		Mockito.when(
				organizationService.findById(organization1.getId())).thenReturn(organization1);
		
		Mockito.when(
				departmentClient.findByOrganization(organization1.getId(), "AuthorizationValue",false)).thenReturn(departments);
		
		
	    String organizationjson = gson.toJson(organization1);
	    		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/organization/"+organization1.getId()+"?isWithDepartments=true&isWithEmployees=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();


		
		JSONAssert.assertEquals(organizationjson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	
	@Test
	public void findByIdWithoutDepartments() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		department1.setEmployees(null);
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(null);
		
		
		Mockito.when(
				organizationService.findById(organization1.getId())).thenReturn(organization1);
		
		Mockito.when(
				departmentClient.findByOrganization(organization1.getId(), "AuthorizationValue",false)).thenReturn(departments);
		
		
	    String organizationjson = gson.toJson(organization1);
	    
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/organization/"+organization1.getId()+"?isWithDepartments=false&isWithEmployees=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		JSONAssert.assertEquals(organizationjson, result.getResponse().getContentAsString(), true);
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.OK.value());
		
		
		
	}
	
	@Test
	public void findByIdNotFound() throws Exception {
		
		employees=new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee3);
		department1.setEmployees(null);
		departments=new ArrayList<Department>();
		departments.add(department1);
		organization1.setDepartments(null);
		
		
		Mockito.when(
				organizationService.findById(organization1.getId())).thenReturn(null);
		
		Mockito.when(
				departmentClient.findByOrganization(organization1.getId(), "AuthorizationValue",false)).thenReturn(departments);
		
		
	    String organizationjson = gson.toJson(organization1);
	    
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/organization/"+organization1.getId()+"?isWithDepartments=false&isWithEmployees=false").accept(
						MediaType.APPLICATION_JSON).header("Authorization", "AuthorizationValue");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		
		Assert.assertNotEquals(organizationjson, result.getResponse()
				.getContentAsString());
		
		Assert.assertEquals("", result.getResponse()
				.getContentAsString());
		
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.NOT_FOUND.value());
		
		
		
	}

}
