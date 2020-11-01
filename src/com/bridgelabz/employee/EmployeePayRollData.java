package com.bridgelabz.employee;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeePayRollData {
	public int id;
	public String name;
	public double salary;
	public LocalDate startDate;
	public double basic_pay;
	public String gender;
	private int company_id;
	private List<String> departmentName;

	public EmployeePayRollData(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	
	public EmployeePayRollData(int id, String name, double salary, Date startDate, String gender, int company_id) {
		this(company_id, name, salary, startDate);
		this.gender = gender;
		this.company_id = company_id;
	}
	
	public EmployeePayRollData(int id, String name, double basic_pay, Date date) {
		this.id = id;
		this.name = name;
		this.basic_pay = basic_pay;
		this.startDate = date.toLocalDate();
	}

	public EmployeePayRollData(int id, String name, double basic_pay, Date date, String gender, int company_id,
			List<String> departmentName) {
		this.id = id;
		this.name = name;
		this.basic_pay = basic_pay;
		this.startDate = date.toLocalDate();
		this.company_id = company_id;
		this.departmentName = departmentName;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "EmployeePayRollData [id=" + id + ", name=" + name + ", salary=" + salary + ", startDate=" + startDate
				+ ", basic_pay=" + basic_pay + ", gender=" + gender + ", company_id=" + company_id + ", departmentName="
				+ departmentName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(basic_pay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + company_id;
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (Double.doubleToLongBits(basic_pay) != Double.doubleToLongBits(other.basic_pay))
			return false;
		if (company_id != other.company_id)
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
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

	public int getcompany_id() {
		return company_id;
	}

	public void setcompany_id(int company_id) {
		this.company_id = company_id;
	}

	public List<String> getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(List<String> departmentName) {
		this.departmentName = departmentName;
	}

	public int getId() {
		return id;
	}

	public double getSalary() {
		return salary;
	}

	public double getBasic_pay() {
		return basic_pay;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setBasic_pay(double basic_pay) {
		this.basic_pay = basic_pay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
