package com.devadas.poc.java8;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListProvider {
	public static List<Employee> getEmployeeList() {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("Devadas", 99, "Adimali"));
		empList.add(new Employee("Sasi", 50, "Chennai"));
		empList.add(new Employee("Sasi", 70, "Bangalore"));
		empList.add(new Employee("Achu", 60, "Thrissur"));
		empList.add(new Employee("Achu", 10, "Goa"));
		empList.add(new Employee("Basil", 40, "Munnar"));
		empList.add(new Employee("Bakka", 50, "Bangalore"));
		empList.add(new Employee("Rajesh", 45, "Bangalore"));
		return empList;
	}
}
