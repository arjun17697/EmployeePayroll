package com.bridgelabz.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
	public List<EmployeePayRollData> readData(){
		String sql="SELECT * FROM employee_payroll";
		List<EmployeePayRollData> employeePayrollList=new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				employeePayrollList.add(new EmployeePayRollData(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getDouble("basic_pay"), resultSet.getDate("start_date")));
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "arjun321";
		Connection connection;
		System.out.println("Connecting to database:" +jdbcURL);
		connection =DriverManager.getConnection(jdbcURL,userName,password);
		System.out.println("Connection is successful!" +connection);
		return connection;


	}

}
