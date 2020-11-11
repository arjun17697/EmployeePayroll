package com.bridgelabz.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bridgelabz.employee.EmployeePayRollService.IOService;
import com.bridgelabz.employee.EmployeePayrollException.ExceptionType;

public class EmployeePayRollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private List<EmployeePayRollData> employeePayrollList;
	private EmployeePayrollDBService employeePayrollDBService;

	public EmployeePayRollService() {
		employeePayrollDBService = EmployeePayrollDBService.getInstance();
	}

	public EmployeePayRollService(List<EmployeePayRollData> employeePayrollList) {
		this();
		this.employeePayrollList = new ArrayList<>(employeePayrollList);

	}

	public static void main(String[] args) {
		ArrayList<EmployeePayRollData> employeePayrollList = new ArrayList<>();
		EmployeePayRollService employeePayRollService = new EmployeePayRollService(employeePayrollList);
		Scanner sc = new Scanner(System.in);
		employeePayRollService.readData(sc);
		// employeePayRollService.writeData();
	}

	public void readData(Scanner sc) {
		System.out.println("Enter ID:");
		int id = sc.nextInt();
		System.out.println("Enter Name:");
		String name = sc.next();
		System.out.println("Enter Salary:");
		double salary = sc.nextDouble();
		employeePayrollList.add(new EmployeePayRollData(id, name, salary));
	}

	public void writeData(IOService ioService) {
		if (ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.CONSOLE_IO))
			System.out.println(employeePayrollList);
		else if (ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData2(employeePayrollList);
	}

	public void printData(IOService ioService) {
		if (ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO))
			new com.bridgelabz.employee.EmployeePayrollFileIOService().printData();
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(com.bridgelabz.employee.EmployeePayRollService.IOService.FILE_IO))
			return new com.bridgelabz.employee.EmployeePayrollFileIOService().countEntries();
		return 0;

	}

	public static boolean readFile() {
		Path path = Paths.get("C:/Users/HP LAP/Desktop/BridgeLabz/FileIO/EmployeePayRoll/PayRollDoc.txt");
		try {
			String fileContent = Files.readString(path);
			String[] employees = fileContent.split(",");
			for (String employee : employees)
				System.out.println(employee);
			return true;
		} catch (IOException e) {
			System.out.println(" directory not found");
		}
		return false;
	}

	public List<EmployeePayRollData> readEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.DB_IO))
			this.employeePayrollList = new EmployeePayrollDBService().readData();
		return this.employeePayrollList;
	}

	public void updateEmployeeSalary(String name, double salary) throws SQLException {
		int result = employeePayrollDBService.updateSalaryUsingSQL(name, salary);
		EmployeePayRollData employeePayrollData = getEmployeePayrollData(name);
		if (result != 0 && employeePayrollData != null)
			employeePayrollData.basic_pay = salary;
	}

	public List<EmployeePayRollData> getEmpPayrollDataForDataRange(LocalDate startDate, LocalDate endDate)
			throws EmployeePayrollException {
		return employeePayrollDBService.getEmployeesForDateRange(startDate, endDate);
	}

	public boolean isEmpPayrollSyncedWithDB(String name) {
		try {
			EmployeePayRollData emp = getEmployeePayrollData(name);
			return employeePayrollDBService.getEmployeePayrollDatas(name).get(0).getId() == emp.getId()
					&& employeePayrollDBService.getEmployeePayrollDatas(name).get(0).getName().equals(emp.getName());
		} catch (IndexOutOfBoundsException e) {
		}
		return false;
	}

	private EmployeePayRollData getEmployeePayrollData(String name) {
		return employeePayrollList.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
	}

	public Map<String, Double> getSumOfDataGroupedByGender(IOService ioService, String column)
			throws EmployeePayrollException {
		if (ioService == IOService.DB_IO)
			return employeePayrollDBService.getEmpDataGroupByGender(column, "SUM");
		else
			throw new EmployeePayrollException("Wrong IO type", ExceptionType.WRONG_IO_TYPE);
	}

	public Map<String, Double> getAvgOfDataGroupedByGender(IOService ioService, String column)
			throws EmployeePayrollException {
		if (ioService == IOService.DB_IO)
			return employeePayrollDBService.getEmpDataGroupByGender(column, "AVG");
		else
			throw new EmployeePayrollException("Wrong IO type", ExceptionType.WRONG_IO_TYPE);
	}

	public Map<String, Double> getMINOfDataGroupedByGender(IOService ioService, String column)
			throws EmployeePayrollException {
		if (ioService == IOService.DB_IO)
			return employeePayrollDBService.getEmpDataGroupByGender(column, "MIN");
		else
			throw new EmployeePayrollException("Wrong IO type", ExceptionType.WRONG_IO_TYPE);
	}

	public Map<String, Double> getMAXOfDataGroupedByGender(IOService ioService, String column)
			throws EmployeePayrollException {
		if (ioService == IOService.DB_IO)
			return employeePayrollDBService.getEmpDataGroupByGender(column, "MAX");
		else
			throw new EmployeePayrollException("Wrong IO type", ExceptionType.WRONG_IO_TYPE);
	}

	public Map<String, Double> getCountOfDataGroupedByGender(IOService ioService, String column)
			throws EmployeePayrollException {
		if (ioService == IOService.DB_IO)
			return employeePayrollDBService.getEmpDataGroupByGender(column, "COUNT");
		else
			throw new EmployeePayrollException("Wrong IO type", ExceptionType.WRONG_IO_TYPE);
	}

	public synchronized void addEmployeePayrollData(String name, Double salary, String startDate, String gender)
			throws EmployeePayrollException {
		int result = employeePayrollDBService.insertNewEmployeeToDB(name, salary, startDate, gender);
		readEmployeePayrollData(IOService.DB_IO);
		EmployeePayRollData employeePayrollData = getEmployeePayrollData(name);
		if (result != 0 && employeePayrollData != null) {
			employeePayrollData.setName(name);
			employeePayrollData.setStartDate(LocalDate.parse(startDate));
			employeePayrollData.setBasic_pay(salary);
		}
		if (result == 0)
			throw new EmployeePayrollException("Wrong name given", ExceptionType.WRONG_NAME);
		if (employeePayrollData == null)
			throw new EmployeePayrollException("No data found", ExceptionType.NO_DATA_FOUND);
	}

	public void addEmployeePayrollData(List<EmployeePayRollData> employeePayrollDataList)
			throws EmployeePayrollException {
		employeePayrollDataList.forEach(emp -> {
			try {
				addEmployeePayrollData(emp.getName(), emp.getSalary(), emp.getStartDate().toString(), emp.getGender());
			} catch (EmployeePayrollException e) {
				e.printStackTrace();
			}
		});
	}

	public void removeEmployee(int empId) throws SQLException, EmployeePayrollException {
		try {
			employeePayrollDBService.removeEmployeeFromDB(empId);
		} catch (EmployeePayrollException e) {
			e.printStackTrace();
		}
	}

	public void addEmployeeAndPayrollData(String name, Double salary, String startDate, String gender, int companyId,
			ArrayList<String> department) throws EmployeePayrollException, SQLException {
		employeePayrollList.add(
				employeePayrollDBService.addNewEmployeeToDB(name, salary, startDate, gender, companyId, department));
	}

	public void addEmployeePayrollDataWithThreads(List<EmployeePayRollData> employeePayrollDataList)
			throws EmployeePayrollException {
		Map<Integer, Boolean> status = new HashMap<>();

		employeePayrollDataList.forEach(emp -> {
			status.put(emp.hashCode(), false);
			{
				Runnable task = () -> {
					System.out.println(Thread.currentThread().getName() + " is being added to DB");
					try {
						addEmployeeAndPayrollData(emp.getName(), emp.getSalary(), emp.getStartDate().toString(),
								emp.getGender(), emp.getcompany_id(), emp.getDepartmentName());
						System.out.println("Employee added: " + Thread.currentThread().getName());
						status.put(emp.hashCode(), true);
					} catch (EmployeePayrollException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Employee added: " + emp.getName());
				};
				Thread thread = new Thread(task, emp.getName());
				thread.start();
			}
		});

		while (status.containsValue(false))
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
