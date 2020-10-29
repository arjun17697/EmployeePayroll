package com.bridgelabz.employee.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.bridgelabz.employee.EmployeePayRollData;
import com.bridgelabz.employee.EmployeePayRollService;
import com.bridgelabz.employee.EmployeePayRollService.IOService;
import com.bridgelabz.employee.EmployeePayrollException;

public class EmployeeServiceJDBCTest {
	EmployeePayRollService empPayrollService;
	List<EmployeePayRollData> empPayrollList;
	Map<String, Double> empPayrollDataByGenderMap;

	@Before
	public void Initializer() {
		empPayrollService = new EmployeePayRollService();
		empPayrollList = empPayrollService.readEmployeePayrollData(IOService.DB_IO);
	}

	@Ignore
	@Test
	public void givenEmpPayrollDB_WhenRetrieved_ShouldMatchEmpCount() {
		assertEquals(5, empPayrollList.size());
		System.out.println(empPayrollList);
	}
	
	@Ignore
	@Test
	public void givenNewSalary_WhenUpdated_ShouldSyncWithDB() throws SQLException {
		EmployeePayRollService empPayRollService = new EmployeePayRollService();
		List<EmployeePayRollData> empPayrollList = empPayRollService.readEmployeePayrollData(IOService.DB_IO);
		empPayRollService.updateEmployeeSalary("Arjun", 2500000);
		boolean isSynced = empPayRollService.isEmpPayrollSyncedWithDB("Arjun");
		assertTrue(isSynced);
	}

	@Ignore
	@Test
	public void givenDateRange_WhenRetrievedEmployee_ShouldReturnEmpCount() throws EmployeePayrollException {
		EmployeePayRollService empPayRollService = new EmployeePayRollService();
		List<EmployeePayRollData> empPayrollList = empPayRollService.readEmployeePayrollData(IOService.DB_IO);
		LocalDate startDate = LocalDate.of(2018, 01, 01);
		LocalDate enDate = LocalDate.now();
		empPayrollList = empPayRollService.getEmpPayrollDataForDataRange(startDate, enDate);
		assertEquals(4, empPayrollList.size());
	}

	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedSum_ShouldReturnSumByGender() throws EmployeePayrollException {
		empPayrollDataByGenderMap = empPayrollService.getSumOfDataGroupedByGender(IOService.DB_IO, "basic_pay");
		assertEquals(2500000, empPayrollDataByGenderMap.get("M"), 0.0);
		assertEquals(6000000, empPayrollDataByGenderMap.get("F"), 0.0);
	}

	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedAvg_ShouldReturnAvgByGender() throws EmployeePayrollException {
		empPayrollDataByGenderMap = empPayrollService.getAvgOfDataGroupedByGender(IOService.DB_IO, "basic_pay");
		assertEquals(2500000, empPayrollDataByGenderMap.get("M"), 0.0);
		assertEquals(3000000, empPayrollDataByGenderMap.get("F"), 0.0);
	}

	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedMaxMin_ShouldReturnMaxByGender() throws EmployeePayrollException {
		empPayrollDataByGenderMap = empPayrollService.getMAXOfDataGroupedByGender(IOService.DB_IO, "basic_pay");
		assertEquals(2500000, empPayrollDataByGenderMap.get("M"), 0.0);
		assertEquals(3000000, empPayrollDataByGenderMap.get("F"), 0.0);
	}

	@Ignore
	@Test
	public void givenEmployeeDB_WhenRetrievedCount_ShouldReturnCountByGender() throws EmployeePayrollException {
		empPayrollDataByGenderMap = empPayrollService.getCountOfDataGroupedByGender(IOService.DB_IO, "id");
		assertEquals(2, empPayrollDataByGenderMap.get("M"), 0.0);
		assertEquals(3, empPayrollDataByGenderMap.get("F"), 0.0);
	}
	
	
	@Test
	public void givenNewEmployee_WhenAdded_ShouldSyncWithDB() throws EmployeePayrollException{
		empPayrollService.readEmployeePayrollData(IOService.DB_IO);
		empPayrollService.addEmployeePayrollData("Mark", 200000.00, "Finance", "2016-02-01", "M");
		boolean isSynced= empPayrollService.isEmpPayrollSyncedWithDB("Mark");
		assertTrue(isSynced);
	}
}