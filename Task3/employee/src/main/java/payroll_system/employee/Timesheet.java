package payroll_system.employee;

import java.sql.Date;

public class Timesheet {
	private int timesheetId;
	private int employeeId;
	private java.sql.Date date;
	private double hoursWorked;

	public int getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(int timesheetId) {
		this.timesheetId = timesheetId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public Timesheet(int timesheetId, int employeeId, Date date, double hoursWorked) {
		super();
		this.timesheetId = timesheetId;
		this.employeeId = employeeId;
		this.date = date;
		this.hoursWorked = hoursWorked;
	}

	public Timesheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Timesheet added to Employee";
	}

}
