
public class Borrower {

	private int id;
	private String name;
	private String email;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Borrower(String name, String email, String phone) {
		super();

		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Borrower() {
		super();

	}

}
