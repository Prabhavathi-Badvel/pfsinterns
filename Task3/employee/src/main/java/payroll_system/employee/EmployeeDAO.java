package payroll_system.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	// Employee methods
	public void addEmployee(Employee employee) throws SQLException {
		String query = "INSERT INTO Employee (name, address, contact, position, hire_date) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getAddress());
			statement.setString(3, employee.getContact());
			statement.setString(4, employee.getPosition());
			statement.setDate(5, employee.getHireDate());
			statement.executeUpdate();
		}
	}

	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		String query = "SELECT * FROM Employee";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getInt("employee_id"));
				employee.setName(resultSet.getString("name"));
				employee.setAddress(resultSet.getString("address"));
				employee.setContact(resultSet.getString("contact"));
				employee.setPosition(resultSet.getString("position"));
				employee.setHireDate(resultSet.getDate("hire_date"));
				employees.add(employee);
			}
		}
		return employees;
	}

	// Salary methods
	public void addSalary(Salary salary) throws SQLException {
		String query = "INSERT INTO Salary (employee_id, base_salary, bonus, deductions) VALUES (?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, salary.getEmployeeId());
			statement.setDouble(2, salary.getBaseSalary());
			statement.setDouble(3, salary.getBonus());
			statement.setDouble(4, salary.getDeductions());
			statement.executeUpdate();
		}
	}

	public List<Salary> getSalariesByEmployeeId(int employeeId) throws SQLException {
		List<Salary> salaries = new ArrayList<>();
		String query = "SELECT * FROM Salary WHERE employee_id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, employeeId); // Set the employeeId parameter
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Salary salary = new Salary();
					salary.setSalaryId(resultSet.getInt("salary_id"));
					salary.setEmployeeId(resultSet.getInt("employee_id"));
					salary.setBaseSalary(resultSet.getDouble("base_salary"));
					salary.setBonus(resultSet.getDouble("bonus"));
					salary.setDeductions(resultSet.getDouble("deductions"));
					salaries.add(salary);
				}
			}
		}

		return salaries;
	}

	// Time sheet methods
	public void addTimesheet(Timesheet timesheet) throws SQLException {
		String query = "INSERT INTO Timesheet (employee_id, date, hours_worked) VALUES (?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, timesheet.getEmployeeId());
			statement.setDate(2, timesheet.getDate());
			statement.setDouble(3, timesheet.getHoursWorked());
			statement.executeUpdate();
		}
	}

	public List<Timesheet> getTimesheetsByEmployeeId(int employeeId) throws SQLException {
		List<Timesheet> timesheets = new ArrayList<>();
		String query = "SELECT * FROM Timesheet WHERE employee_id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, employeeId); // Set the employeeId parameter
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Timesheet timesheet = new Timesheet();
					timesheet.setTimesheetId(resultSet.getInt("timesheet_id"));
					timesheet.setEmployeeId(resultSet.getInt("employee_id"));
					timesheet.setDate(resultSet.getDate("date"));
					timesheet.setHoursWorked(resultSet.getDouble("hours_worked"));
					timesheets.add(timesheet);
				}
			}
		}
		return timesheets;
	}

	// Payroll methods
	public void addPayroll(Payroll payroll) throws SQLException {
		String query = "INSERT INTO Payroll (employee_id, pay_date, gross_salary, net_salary) VALUES (?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, payroll.getEmployeeId());
			statement.setDate(2, payroll.getPayDate());
			statement.setDouble(3, payroll.getGrossSalary());
			statement.setDouble(4, payroll.getNetSalary());
			statement.executeUpdate();
		}
	}

	public List<Payroll> getPayrollsByEmployeeId(int employeeId) throws SQLException {
		List<Payroll> payrolls = new ArrayList<>();
		String query = "SELECT * FROM Payroll WHERE employee_id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, employeeId); // Set the employeeId parameter
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Payroll payroll = new Payroll();
					payroll.setPayrollId(resultSet.getInt("payroll_id"));
					payroll.setEmployeeId(resultSet.getInt("employee_id"));
					payroll.setPayDate(resultSet.getDate("pay_date"));
					payroll.setGrossSalary(resultSet.getDouble("gross_salary"));
					payroll.setNetSalary(resultSet.getDouble("net_salary"));
					payrolls.add(payroll);
				}
			}
		}
		return payrolls;
	}

}
