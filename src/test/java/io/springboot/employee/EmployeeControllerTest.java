package io.springboot.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.hamcrest.Matchers;
import org.springframework.http.MediaType;
import io.springboot.employee1.Employee;
import io.springboot.employee1.EmployeeController;
import io.springboot.employee1.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeControllerTest {
	
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;
	
	private MockMvc mockMvc;
	
			
	 @Before
	    public void setUp(){
	        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
	                .build();
	    }
	
	@Test
	public void testGetAllEmployees() throws Exception
	{
		 List<Employee> employeeList = Arrays.asList(new Employee("1","ABC"),
				 new Employee("3","PQR"));
	        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeList);
	        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetEmployees() throws Exception
	{
		Employee employeeList = new Employee("1","ABC");
        Mockito.when(employeeService.getEmployee("1")).thenReturn(employeeList);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("ABC")));
        Mockito.verify(employeeService).getEmployee("1");
	}
	
	@Test
	public void testAddEmployees() throws Exception
	{
		Employee e1=new Employee();
		e1.setId("1");
		e1.setName("ABC");
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(e1);
	    
	    mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateEmployees() throws Exception
	{
		Employee e1=new Employee();
		e1.setId("1");
		e1.setName("ABC");
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(e1);
	    
	    mockMvc.perform(put("/employees/1").contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteEmployees() throws Exception
	{
				mockMvc.perform(delete("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}

}
