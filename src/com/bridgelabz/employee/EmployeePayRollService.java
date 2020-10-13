package com.bridgelabz.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	private List<EmployeePayRollData> employeePayrollList;
	//public EmployeePayRollService() {};
	public EmployeePayRollService(List<EmployeePayRollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public static void main(String[] args) {
		ArrayList<EmployeePayRollData> employeePayrollList = new ArrayList<>();
		EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayrollList);
		Scanner sc = new Scanner(System.in);
		employeePayRollService.readData(sc);
		employeePayRollService.writeData();
	}

	private void readData(Scanner sc) {
		System.out.println("Enter ID:");
		int id = sc.nextInt();
		System.out.println("Enter Name:");
		String name = sc.next();
		System.out.println("Enter Salary:");
		double salary = sc.nextDouble();
		employeePayrollList.add(new EmployeePayRollData(id, name, salary));
	}

	private void writeData() {
		System.out.println(employeePayrollList);
	}
}
