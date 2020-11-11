package com.bridgelabz.employee.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.employee.EmployeePayRollData;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeePayrollRestAssuredTest {
	@Before
	public void setup() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=3000;
	}
	
	public List<EmployeePayRollData> getEmployeeList(){
		Response response =RestAssured.get("/employee_payroll");
		System.out.println("Employee Payoll Entries in JSON Server: \n" +response.toString());
		EmployeePayRollData[] empArrays = new Gson().fromJson(response.asString(),EmployeePayRollData[].class);
		return Arrays.asList(empArrays);
	}
	
	@Test
	public void givenEmployeeDataInJSONServer_WhenRetrieved_ShouldMatchTheCount() {
		List<EmployeePayRollData> employeePayrollList= getEmployeeList();
		assertEquals(6,employeePayrollList.size());
	}
}
