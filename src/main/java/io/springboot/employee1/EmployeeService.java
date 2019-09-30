package io.springboot.employee1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	private List<Employee> employees= new ArrayList<>(Arrays.asList(
	           new Employee("1","TEST1"),
	           new Employee("2","TEST2"),
	           new Employee("3","TEST3")));
	
	public List<Employee> getAllEmployee(){
		return employees;
	}
	
	public Employee getEmployee(String id) {
		return employees.stream().filter(t->t.getId().equals(id)).findFirst().get();
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
		
	}

	public void updateEmployee(Employee employee,String id) {
		// TODO Auto-generated method stub
		for(int i=0;i<employees.size();i++)
		{
			Employee t=employees.get(i);
			if(t.getId().equals(id)) {
				employees.set(i, employee);
				return;
			}
		}
		
	}

	public void deleteEmployee(String id) {
		// TODO Auto-generated method stub
		employees.removeIf(t->t.getId().equals(id));
	}

	
	
}

