import java.util.Date;

public class Transaction {

	private int id;
	private int bookId;
	private int borrowerId;
	private Date issueDate;
	private Date returnDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Transaction(int id, int bookId, int borrowerId, Date issueDate, Date returnDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.borrowerId = borrowerId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public Transaction() {
		super();

	}

}
