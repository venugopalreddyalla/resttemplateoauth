package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.Employee;

@Mapper
public interface StoredProcedureDemo {	
	
	
	@Select("select demo(#{a},#{b})")
	public String getData(@Param("a") int a,@Param("b")int b);
	
	@Select("select * from employee_fun()")
	public List<Employee> getEmployees();

}
