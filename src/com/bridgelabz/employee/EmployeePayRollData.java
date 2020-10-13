package com.bridgelabz.employee;

public class EmployeePayRollData {
	public int id;
	public String name;
	public double salary;
	
	public EmployeePayRollData(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
public String toString() {return "id:" + id +" name: " +name + " Salary: " +salary;}
}
