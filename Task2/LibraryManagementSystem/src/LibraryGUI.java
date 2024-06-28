import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class LibraryGUI {
	private JFrame frame;
	private JTextField titleField, authorField, publisherField, yearField;
	private JTextField borrowerNameField, borrowerEmailField, borrowerPhoneField;
	private JTextField bookIdField, borrowerIdField, transactionIdField;
	private JTextArea reportArea;
	private LibraryManager libraryManager;

	public LibraryGUI() {
		try {
			libraryManager = new LibraryManager();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		frame = new JFrame("Library Management System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());

		// Book fields
		JLabel titleLabel = new JLabel("Title:");
		titleField = new JTextField(20);
		JLabel authorLabel = new JLabel("Author:");
		authorField = new JTextField(20);
		JLabel publisherLabel = new JLabel("Publisher:");
		publisherField = new JTextField(20);
		JLabel yearLabel = new JLabel("Year:");
		yearField = new JTextField(5);

		JButton addBookButton = new JButton("Add Book");
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Code to handle adding a book
				String title = titleField.getText();
				String author = authorField.getText();
				String publisher = publisherField.getText();
				int year;
				// Assuming year is an integer
				try {
					year = Integer.parseInt(yearField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Year must be an integer.", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Call LibraryManager method to add book to database
				Book book = new Book(title, author, publisher, year);
				libraryManager.addBook(book);
				JOptionPane.showMessageDialog(frame, "Book added successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

//                // Refresh table or display confirmation

				System.out.println("Adding book: " + title + ", " + author + ", " + publisher + ", " + year);
				// Clear input fields
				titleField.setText("");
				authorField.setText("");
				publisherField.setText("");
				yearField.setText("");

			}
		});

		topPanel.add(titleLabel);
		topPanel.add(titleField);
		topPanel.add(authorLabel);
		topPanel.add(authorField);
		topPanel.add(publisherLabel);
		topPanel.add(publisherField);
		topPanel.add(yearLabel);
		topPanel.add(yearField);
		topPanel.add(addBookButton);

		// Borrower fields
		JPanel borrowerPanel = new JPanel();
		borrowerPanel.setLayout(new FlowLayout());

		JLabel borrowerNameLabel = new JLabel("Name:");
		borrowerNameField = new JTextField(20);
		JLabel borrowerEmailLabel = new JLabel("Email:");
		borrowerEmailField = new JTextField(20);
		JLabel borrowerPhoneLabel = new JLabel("Phone:");
		borrowerPhoneField = new JTextField(15);

		JButton addBorrowerButton = new JButton("Add Borrower");
		System.out.println(" ");
		addBorrowerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Code to handle adding a borrower
				String name = borrowerNameField.getText();
				String email = borrowerEmailField.getText();
				String phone = borrowerPhoneField.getText();
				// Call LibraryManager method to add borrower to database
				Borrower borrower = new Borrower(name, email, phone);
				libraryManager.addBorrower(borrower);
				JOptionPane.showMessageDialog(frame, "Borrower added successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				// Clear input fields
				borrowerNameField.setText("");
				borrowerEmailField.setText("");
				borrowerPhoneField.setText("");
				// Refresh table or display confirmation
				System.out.println("Adding borrower: " + name + ", " + email + ", " + phone);
			}
		});

		borrowerPanel.add(borrowerNameLabel);
		borrowerPanel.add(borrowerNameField);
		borrowerPanel.add(borrowerEmailLabel);
		borrowerPanel.add(borrowerEmailField);
		borrowerPanel.add(borrowerPhoneLabel);
		borrowerPanel.add(borrowerPhoneField);
		borrowerPanel.add(addBorrowerButton);

		// Transaction Panel for issuing and returning books
		JPanel transactionPanel = new JPanel();
		transactionPanel.setLayout(new FlowLayout());
		System.out.println("/n");
		
		JLabel bookIdLabel = new JLabel("Book ID:");
		bookIdField = new JTextField(5);
		JLabel borrowerIdLabel = new JLabel("Borrower ID:");
		borrowerIdField = new JTextField(5);

		JButton issueBookButton = new JButton("Issue Book");
		issueBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int bookId;
				int borrowerId;
				try {
					bookId = Integer.parseInt(bookIdField.getText());
					borrowerId = Integer.parseInt(borrowerIdField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Book ID and Borrower ID must be integers.", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				libraryManager.issueBook(bookId, borrowerId);
				JOptionPane.showMessageDialog(frame, "Book issued successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				// Refresh table or display confirmation
				System.out.println("Issuing book ID " + bookId + " to borrower ID " + borrowerId);
				// Clear input fields
				bookIdField.setText("");
				borrowerIdField.setText("");
			}

		});

		JLabel transactionIdLabel = new JLabel("Transaction ID:");
		transactionIdField = new JTextField(5);

		JButton returnBookButton = new JButton("Return Book");
		returnBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int transactionId;
				try {
					transactionId = Integer.parseInt(transactionIdField.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Transaction ID must be an integer.", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				libraryManager.returnBook(transactionId);
				JOptionPane.showMessageDialog(frame, "Book returned successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				// Refresh table or display confirmation
				// Clear input fields
				transactionIdField.setText("");
			}
		});

		transactionPanel.add(bookIdLabel);
		transactionPanel.add(bookIdField);
		transactionPanel.add(borrowerIdLabel);
		transactionPanel.add(borrowerIdField);
		transactionPanel.add(issueBookButton);
		transactionPanel.add(transactionIdLabel);
		transactionPanel.add(transactionIdField);
		transactionPanel.add(returnBookButton);

		// report Panel
		JPanel reportPanel = new JPanel();
		reportPanel.setLayout(new BorderLayout());

		JLabel reportLabel = new JLabel("Transactions Report:");
		reportArea = new JTextArea(10, 50);
		JScrollPane scrollPane = new JScrollPane(reportArea);

		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Transaction> transactions = libraryManager.generateReport();
				StringBuilder report = new StringBuilder();
				for (Transaction transaction : transactions) {
					report.append("Transaction ID: ").append(transaction.getId()).append(", Book ID: ")
							.append(transaction.getBookId()).append(", Borrower ID: ")
							.append(transaction.getBorrowerId()).append(", Issue Date: ")
							.append(transaction.getIssueDate()).append(", Return Date: ")
							.append(transaction.getReturnDate()).append("\n");
				}
				reportArea.setText(report.toString());
			}
		});

		reportPanel.add(reportLabel, BorderLayout.NORTH);
		reportPanel.add(scrollPane, BorderLayout.CENTER);
		reportPanel.add(generateReportButton, BorderLayout.SOUTH);

		// Add panels to the main frame
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(borrowerPanel, BorderLayout.WEST);
		frame.add(transactionPanel, BorderLayout.CENTER);
		frame.add(reportPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LibraryGUI();
			}
		});

	}
}
