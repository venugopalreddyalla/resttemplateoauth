package com.example.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.mapper.StoredProcedureDemo;
import com.example.demo.model.Employee;

@SpringBootApplication
public class StoredproceduresdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(StoredproceduresdemoApplication.class, args);
		StoredProcedureDemo procedureDemo = applicationContext.getBean(StoredProcedureDemo.class);
		String data = procedureDemo.getData(100,200);
		System.out.println(data);
		
		List<Employee> employees = procedureDemo.getEmployees();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
