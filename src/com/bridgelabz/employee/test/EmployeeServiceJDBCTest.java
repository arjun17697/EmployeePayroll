package com.bridgelabz.employee.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
	
	@Test
	public void givenNewSalary_WhenUpdated_ShouldSyncWithDB() {
		EmployeePayRollService empPayRollService = new EmployeePayRollService();
		List<EmployeePayRollData> empPayrollList = empPayRollService.readEmployeePayrollData(IOService.DB_IO);
		empPayRollService.updateEmployeeSalary("Arjun", 3000001.0);
		boolean isSynced = empPayRollService.isEmpPayrollSyncedWithDB("Arjun");
		assertTrue(isSynced);
	}
}