package com.bridgelabz.employee.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bridgelabz.employee.EmployeePayRollData;
import com.bridgelabz.employee.EmployeePayRollService;
import com.bridgelabz.employee.EmployeePayRollService.IOService;

public class EmployeeServiceJDBCTest {
	@Test
	public void givenEmpPayrollDB_WhenRetrieved_ShouldMatchEmpCount() {
		EmployeePayRollService empPayRollService = new EmployeePayRollService();
		List<EmployeePayRollData> empPayrollList = empPayRollService.readEmployeePayrollData(IOService.DB_IO);
		assertEquals(5, empPayrollList.size());
		System.out.println(empPayrollList);
		
	}

}
