package payroll_system.employee;

import java.sql.Date;

public class Payroll {
	private int payrollId;
	private int employeeId;
	private java.sql.Date payDate;
	private double grossSalary;
	private double netSalary;

//Getters and Setters for Payroll class

	public int getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public java.sql.Date getPayDate() {
		return payDate;
	}

	public void setPayDate(java.sql.Date payDate) {
		this.payDate = payDate;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}

//constructors
	public Payroll(int payrollId, int employeeId, Date payDate, double grossSalary, double netSalary) {
		super();
		this.payrollId = payrollId;
		this.employeeId = employeeId;
		this.payDate = payDate;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
	}

	public Payroll() {
		super();

	}

//toString
	@Override
	public String toString() {
		return "Payroll added to Employee";
	}

}
