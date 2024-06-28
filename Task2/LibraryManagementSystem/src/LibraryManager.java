import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {

	private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private Connection connection;

	public LibraryManager() throws SQLException {
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	// Methods for adding borrower, issuing, returning books, and generating reports

	public void addBorrower(Borrower borrower) {
		String query = "INSERT INTO Borrowers (name, email, phone) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, borrower.getName());
			stmt.setString(2, borrower.getEmail());
			stmt.setString(3, borrower.getPhone());
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				borrower.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBook(Book book) {
		String query = "INSERT INTO Books (title, author, publisher, year) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getPublisher());
			stmt.setInt(4, book.getYear());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void issueBook(int bookId, int borrowerId) {
		String query = "INSERT INTO Transactions (book_id, borrower_id, issue_date) VALUES (?, ?, CURDATE())";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, bookId);
			stmt.setInt(2, borrowerId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void returnBook(int transactionId) {
		String query = "UPDATE Transactions SET return_date = CURDATE() WHERE transaction_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, transactionId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Transaction> generateReport() {
		List<Transaction> transactions = new ArrayList<>();
		String query = "SELECT * FROM Transactions";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				int transactionId = rs.getInt("transaction_id");
				int bookId = rs.getInt("book_id");
				int borrowerId = rs.getInt("borrower_id");
				Date issueDate = rs.getDate("issue_date");
				Date returnDate = rs.getDate("return_date");

				Transaction transaction = new Transaction(transactionId, bookId, borrowerId, issueDate, returnDate);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

}
