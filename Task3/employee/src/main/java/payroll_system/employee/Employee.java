package payroll_system.employee;

import java.sql.Date;

public class Employee {
	private int employeeId;
	private String name;
	private String address;
	private String contact;
	private String position;
	private java.sql.Date hireDate;

	// Getters and Setters for Employee class
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}
	// Constructors

	public Employee(int employeeId, String name, String address, String contact, String position, Date hireDate) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.position = position;
		this.hireDate = hireDate;
	}

	public Employee() {
		super();

	}
	// toString

	@Override
	public String toString() {
		return name + " - " + position;
	}

}
