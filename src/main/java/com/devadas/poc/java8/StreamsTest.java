package com.devadas.poc.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamsTest {

	public static void main(String[] args) {
		new Thread(() -> System.out.println("Hi")).start();
		
		// Int stream
		System.out.println("97 is Prime ? " + isPrime(97));
		System.out.println("37 is Prime ? " + isPrime(37));
		System.out.println("98 is Prime ? " + isPrime(98));
		System.out.println("33 is Prime ? " + isPrime(33));

		long count = IntStream.rangeClosed(1, 15).filter(i -> i%5==0).count();
		System.out.println("Count of numbers divisible by 5 between 1 and 15 is " + count);
		IntStream.rangeClosed(1, 15).filter(i -> i%5==0).forEach(System.out::println);
		
		// Collections.
		List<Employee> empList = EmployeeListProvider.getEmployeeList();
		
		System.out.println("Distinct salary >= 40");
		empList.stream().filter(e -> e.getSalary() >= 40).mapToInt(e -> e.getSalary()).distinct().sorted().forEach(System.out::println);;
		
		IntStream intStream = Arrays.stream(new int[]{1, 2, 3, 4, 5});
		// Same as IntStream.rangeClosed(1,  5); - but shows how to create stream from array.
		
	}

	private static boolean isPrime(int i) {
		return (i > 1) && IntStream.range(2, i).noneMatch(j -> i%j == 0);
	}

	private static void printList(List<Employee> empList) {
		empList.forEach(System.out::println);
	}
}
