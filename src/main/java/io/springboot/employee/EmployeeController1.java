package io.springboot.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({ "/employees" })
public class EmployeeController1 {
	
	/*
	 * @Autowired private EmployeeService1 employeeService;
	 * 
	 * @DeleteMapping(path = { "/{id}" }) public Employee1
	 * delete(@PathVariable("id") int id) { return employeeService.delete(id); }
	 * 
	 * @PostMapping public Employee1 create(@RequestBody Employee1 user) { return
	 * employeeService.add(user);
	 * 
	 * }
	 */
}