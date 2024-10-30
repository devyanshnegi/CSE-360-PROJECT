package edu.asu.DatabasePart1;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


class DatabaseHelper {

	// JDBC driver name and database URL 
	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:~/Database/firstDatabase";  

	//  Database credentials 
	static final String USER = "sa"; 
	static final String PASS = ""; 

	private Connection connection = null;
	private Statement statement = null; 
	//	PreparedStatement pstmt

	public void connectToDatabase() throws SQLException {
		try {
			Class.forName(JDBC_DRIVER); // Load the JDBC driver
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement(); 
			createTables();  // Create the necessary tables if they don't exist
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
		}
	}
	
	private void createTables() throws SQLException {
		String userTable = "CREATE TABLE IF NOT EXISTS cse360users ("
				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "username VARCHAR(16) UNIQUE, "
				+ "email VARCHAR(255) UNIQUE, "
				+ "firstname VARCHAR(255), "
				+ "middlename VARCHAR(255), "
				+ "lastname VARCHAR(255), "
				+ "preferredname VARCHAR(255), "
				+ "password VARCHAR(255), "
				+ "role VARCHAR(20),"
				+ "otp INT UNIQUE)";
//		String otpTable = "CREATE TABLE IF NOT EXISTS otp ("
//				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
//				+ "code VARCHAR(255) UNIQUE, "
//				+ "created DATETIME, "
//				+ "flag BIT)";
		statement.execute(userTable);
//		statement.execute(otpTable);
	}
	
//	public void storeOTP(String otp, String Date) throws SQLException {
//		String insertUser = "INSERT INTO otp (password, created, flag) VALUES (?, ?, True)";
//		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
//			pstmt.setString(1, otp);
//			pstmt.setString(2, Date);
//			pstmt.executeUpdate();
//		}
//	}
	
	public void storeOTP(String role, int otp) throws SQLException {
		String insertUser = "INSERT INTO cse360users (role, otp) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, role);
			pstmt.setInt(2, otp);
			pstmt.executeUpdate();
		}
	}
	
	public boolean isOTPValid(int otp) {
	    String query = "SELECT COUNT(*) FROM cse360users WHERE otp = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setInt(1, otp);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, the user exists
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // If an error occurs, assume user doesn't exist
	}


	// Check if the database is empty
	public boolean isDatabaseEmpty() throws SQLException {
		String query = "SELECT COUNT(*) AS count FROM cse360users";
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getInt("count") == 0;
		}
		return true;
	}

	public void register(String username, String password, int otp) throws SQLException {
		String insertUser = "UPDATE cse360users SET username = ?, password = ?, otp = ? WHERE otp = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, null);
			pstmt.setInt(4, otp);
			pstmt.executeUpdate();
		}
	}

	public boolean login(String username, String password) throws SQLException {
		String query = "SELECT * FROM cse360users WHERE username = ? AND password = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		}
	}
	
	public void completeProfile(String username, String firstname, String middlename, String lastname, String preferredname,  String email) throws SQLException {
		String insertUser = "UPDATE cse360users SET firstname = ?, middlename = ?, lastname = ?, email = ?, preferredname = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, firstname);
			pstmt.setString(2, middlename);
			pstmt.setString(3, lastname);
			pstmt.setString(4, preferredname);
			pstmt.setString(5, email);
			pstmt.setString(6, username);
			pstmt.executeUpdate();
		}
	}
	
	public boolean doesUserExist(String username) {
	    String query = "SELECT COUNT(*) FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, the user exists
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // If an error occurs, assume user doesn't exist
	}

	public void displayUsersByAdmin() throws SQLException{
		String sql = "SELECT * FROM cse360users"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql); 

		while(rs.next()) { 
			// Retrieve by column name 
			int id  = rs.getInt("id"); 
			String  username = rs.getString("username"); 
			String  email = rs.getString("email"); 
			String password = rs.getString("password"); 
			String role = rs.getString("role");  
			String otp = rs.getString("otp");  

			// Display values 
			System.out.print("ID: " + id); 
			System.out.print(", Username: " + username); 
			System.out.print(", Email: " + email); 
			System.out.print(", Password: " + password); 
			System.out.println(", Role: " + role); 
			System.out.println(", OTP: " + otp); 
		} 
	}
	
	public void displayUsersByUser() throws SQLException{
		String sql = "SELECT * FROM cse360users"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql); 

		while(rs.next()) { 
			// Retrieve by column name 
			int id  = rs.getInt("id"); 
			String  email = rs.getString("email"); 
			String password = rs.getString("password"); 
			String role = rs.getString("role");  
			String otp = rs.getString("otp");  

			// Display values 
			System.out.print("ID: " + id); 
			System.out.print(", Email: " + email); 
			System.out.print(", Password: " + password); 
			System.out.println(", Role: " + role); 
			System.out.println(", OTP: " + otp); 
		} 
	}


	public void closeConnection() {
		try{ 
			if(statement!=null) statement.close(); 
		} catch(SQLException se2) { 
			se2.printStackTrace();
		} 
		try { 
			if(connection!=null) connection.close(); 
		} catch(SQLException se){ 
			se.printStackTrace(); 
		} 
		System.out.println("Closing connection to database...");
	}

}
