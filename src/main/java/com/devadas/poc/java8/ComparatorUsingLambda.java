package com.devadas.poc.java8;

import java.util.Comparator;
import java.util.List;

public class ComparatorUsingLambda {

	public static void main(String[] args) {
		List<Employee> empList = EmployeeListProvider.getEmployeeList();
		
		System.out.println("\nSorting by salary:");
		// Collections.sort also can be used. Here List.sort is used.
		//Collections.sort(empList, Comparator.comparingInt(Employee::getSalary));
		empList.sort(Comparator.comparingInt(Employee::getSalary));
		printList(empList);
		
		System.out.println("\nSorting by name - REVERSED:");
		empList.sort(Comparator.comparing(Employee::getName).reversed());
		printList(empList);
		
		// Using two comparators - thenComparing.
		empList.sort(Comparator.comparing(Employee::getName).thenComparing(Employee::getPlace));
		System.out.println("\nList after adding sorting name and then by place:");
		printList(empList);
		
		// Comparing null
		empList.add(2, null);
		empList.add(4, null);
		System.out.println("\nList after adding null:");
		printList(empList);
		
		empList.sort(Comparator.nullsLast(Comparator.comparing(Employee::getSalary)));
		System.out.println("\nSorted List after adding null:");
		printList(empList);
	}

	private static void printList(List<Employee> empList) {
		empList.forEach(System.out::println);
	}
	
}
