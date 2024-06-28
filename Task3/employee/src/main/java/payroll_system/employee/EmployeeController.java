package payroll_system.employee;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

	// Employee Fields
	@FXML
	private TextField nameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField contactField;
	@FXML
	private TextField positionField;
	@FXML
	private DatePicker hireDateField;
	@FXML
	private ListView<Employee> employeeListView;
	@FXML
	private Button addEmployeeButton;

	// Salary Fields
	@FXML
	private TextField baseSalaryField;
	@FXML
	private TextField bonusField;
	@FXML
	private TextField deductionsField;
	@FXML
	private ListView<Salary> salaryListView;
	@FXML
	private Button addSalaryButton;

	// Time sheet Fields
	@FXML
	private DatePicker timesheetDateField;
	@FXML
	private TextField hoursWorkedField;
	@FXML
	private ListView<Timesheet> timesheetListView;
	@FXML
	private Button addTimesheetButton;

	// Payroll Fields
	@FXML
	private DatePicker payrollDateField;
	@FXML
	private TextField grossSalaryField;
	@FXML
	private TextField netSalaryField;
	@FXML
	private ListView<Payroll> payrollListView;
	@FXML
	private Button addPayrollButton;

	private EmployeeDAO employeeDAO = new EmployeeDAO();

	@FXML
	public void initialize() {
		employeeListView.setCellFactory(param -> new ListCell<Employee>() {
			@Override
			protected void updateItem(Employee employee, boolean empty) {
				super.updateItem(employee, empty);

				if (empty || employee == null || employee.getName() == null) {
					setText(null);
				} else {
					setText(employee.getName() + " - " + employee.getPosition());
				}
			}
		});

		loadEmployees();
	}

	// Employee Methods
	@FXML
	public void addEmployee() {
		try {
			Employee employee = new Employee();
			employee.setName(nameField.getText());
			employee.setAddress(addressField.getText());
			employee.setContact(contactField.getText());
			employee.setPosition(positionField.getText());
			employee.setHireDate(java.sql.Date.valueOf(hireDateField.getValue()));
			employeeDAO.addEmployee(employee);
			loadEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadEmployees() {
		try {
			employeeListView.getItems().clear();
			employeeListView.getItems().addAll(employeeDAO.getAllEmployees());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Salary Methods
	@FXML
	public void addSalary() {
		try {
			int employeeId = getSelectedEmployeeId();
			if (employeeId == -1) {
				showError("No Employee Selected", "Please select an employee before adding a salary.");
				return;
			}

			Salary salary = new Salary();
			salary.setEmployeeId(employeeId);
			salary.setBaseSalary(Double.parseDouble(baseSalaryField.getText()));
			salary.setBonus(Double.parseDouble(bonusField.getText()));
			salary.setDeductions(Double.parseDouble(deductionsField.getText()));

			if (employeeExists(employeeId)) {
				employeeDAO.addSalary(salary);
				loadSalaries(employeeId);
			} else {
				showError("Employee Not Found", "The selected employee does not exist in the database.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadSalaries(int employeeId) {
		try {
			salaryListView.getItems().clear();
			salaryListView.getItems().addAll(employeeDAO.getSalariesByEmployeeId(employeeId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean employeeExists(int employeeId) {
		try {
			List<Employee> employees = employeeDAO.getAllEmployees();
			for (Employee employee : employees) {
				if (employee.getEmployeeId() == employeeId) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void showError(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private int getSelectedEmployeeId() {
		Employee selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
		return selectedEmployee != null ? selectedEmployee.getEmployeeId() : -1;
	}

	// Time sheet Methods
	@FXML
	public void addTimesheet() {
		try {
			Timesheet timesheet = new Timesheet();
			timesheet.setEmployeeId(getSelectedEmployeeId());
			timesheet.setDate(java.sql.Date.valueOf(timesheetDateField.getValue()));
			timesheet.setHoursWorked(Double.parseDouble(hoursWorkedField.getText()));
			employeeDAO.addTimesheet(timesheet);
			loadTimesheets(getSelectedEmployeeId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadTimesheets(int employeeId) {
		try {
			timesheetListView.getItems().clear();
			timesheetListView.getItems().addAll(employeeDAO.getTimesheetsByEmployeeId(employeeId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Payroll Methods
	@FXML
	public void addPayroll() {
		try {
			Payroll payroll = new Payroll();
			payroll.setEmployeeId(getSelectedEmployeeId());
			payroll.setPayDate(java.sql.Date.valueOf(payrollDateField.getValue()));
			payroll.setGrossSalary(Double.parseDouble(grossSalaryField.getText()));
			payroll.setNetSalary(Double.parseDouble(netSalaryField.getText()));
			employeeDAO.addPayroll(payroll);
			loadPayrolls(getSelectedEmployeeId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadPayrolls(int employeeId) {
		try {
			payrollListView.getItems().clear();
			payrollListView.getItems().addAll(employeeDAO.getPayrollsByEmployeeId(employeeId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
