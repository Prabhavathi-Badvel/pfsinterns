package payroll_system.employee;

public class Salary {
	private int salaryId;
	private int employeeId;
	private double baseSalary;
	private double bonus;
	private double deductions;

	// Getters and Setters for Salary class

	public int getSalaryId() {
		return salaryId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}

	// Constructors
	public Salary() {
		super();

	}

	public Salary(int salaryId, int employeeId, double baseSalary, double bonus, double deductions) {
		super();
		this.salaryId = salaryId;
		this.employeeId = employeeId;
		this.baseSalary = baseSalary;
		this.bonus = bonus;
		this.deductions = deductions;
	}

	// toString

	@Override
	public String toString() {
		return "Salary added to selected Employee";
	}

}
