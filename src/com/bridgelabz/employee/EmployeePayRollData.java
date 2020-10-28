package com.bridgelabz.employee;

import java.sql.Date;
import java.time.LocalDate;

public class EmployeePayRollData {
	public int id;
	public String name;
	public double salary;
	public Date startDate;
	
	public EmployeePayRollData(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	public EmployeePayRollData(int id, String name, double salary,Date date) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startDate=date;
	}
	
public String toString() {return "id:" + id +" name: " +name + " Salary: " +salary;}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	long temp;
	temp = Double.doubleToLongBits(salary);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	EmployeePayRollData other = (EmployeePayRollData) obj;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
		return false;
	if (startDate == null) {
		if (other.startDate != null)
			return false;
	} else if (!startDate.equals(other.startDate))
		return false;
	return true;
}

}
