package com.devadas.poc.java8;

public class Employee {
	private String name;
	private int salary;
	private String place;
	
	public Employee(String name, int salary, String place) {
		this.name = name;
		this.salary = salary;
		this.place = place;
	}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getSalary() {return salary;}
	public void setSalary(int salary) {this.salary = salary;}
	public String getPlace() {return place;}
	public void setPlace(String place) {this.place = place;}
	public String toString() {return "Employee [name=" + name + ", salary=" + salary + ", place=" + place + "]";}
}