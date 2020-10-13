package com.bridgelabz.employee;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import static com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO;

public class EmployeePayrollServiceTest {

	@Test
	public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries(){
		EmployeePayRollData[] arrayOfEmps= {
				new EmployeePayRollData(1,"Jeff Bezos",10000),
				new EmployeePayRollData(2,"Bill Gates",20000),
				new EmployeePayRollData(3,"Mark Bezos",30000)
		};
		EmployeePayRollService employeePayrollService;
		employeePayrollService = new EmployeePayRollService(Arrays.asList(arrayOfEmps));
		employeePayrollService.writeData(FILE_IO);
		employeePayrollService.printData(FILE_IO);
		long entries = employeePayrollService.countEntries(FILE_IO);
		Assert.assertEquals(3,entries);
	}

	
	  @Test public void givenFileOnReadingFromFileShouldMatchEmployeeCount() {
	  EmployeePayRollService employeePayrollService = new EmployeePayRollService();
	  long entries = employeePayrollService.readData(FILE_IO);
	  Assert.assertEquals(3, entries);
	 }
	 
}
