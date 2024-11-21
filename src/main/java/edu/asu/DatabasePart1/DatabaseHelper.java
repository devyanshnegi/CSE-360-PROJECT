package edu.asu.DatabasePart1;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
				+ "otp INT UNIQUE,"
				+ "expiry DATETIME)";
		statement.execute(userTable);
		
//		String otpTable = "CREATE TABLE IF NOT EXISTS otp ("
//				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
//				+ "code VARCHAR(255) UNIQUE, "
//				+ "created DATETIME, "
//				+ "flag BIT)";
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
	
	public void storeOTP(String role, int otp) {
		String insertUser = "INSERT INTO cse360users (role, otp) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, role);
			pstmt.setInt(2, otp);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isOtpValid(int otp) {
	    String query = "SELECT COUNT(*) FROM cse360users WHERE otp = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setInt(1, otp);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, the user exists
	            if (rs.getInt(1) > 0) {
	            	
	        		return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // If an error occurs, assume user doesn't exist
	}
	
		public boolean isOtpPresentAndValid(int otp) {
		String query = "SELECT COUNT(*) FROM cse360users WHERE otp = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setInt(1, otp);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // If the count is greater than 0, the user exists
	            if(rs.getInt(1) > 0) {
	            	LocalDateTime currentDateTime = LocalDateTime.now();

	            	String expiry = rs.getString("expiry"); 
	            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            	LocalDateTime dateTime = LocalDateTime.parse(expiry, formatter);
	            	
	            	String insertUser = "UPDATE cse360users SET otp = ?, expiry = ? WHERE otp = ?";
	        		try (PreparedStatement pstmt1 = connection.prepareStatement(insertUser)) {
	        			pstmt1.setNull(1, java.sql.Types.INTEGER); // Set otp to null
	        		    pstmt1.setNull(2, java.sql.Types.TIMESTAMP); // Set the DATETIME field to NULL
	        			pstmt1.setInt(3, otp); 
	        			pstmt1.executeUpdate();
	        		} catch (SQLException e) {
	        	        e.printStackTrace();
	        	    }
	            	
	            	if(dateTime.isBefore(currentDateTime)) {
	            		return false;
	            	}
	            	else {
	            		return true;
	            	}
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return true; // If an error occurs, assume user doesn't exist
	}

	public void storeResetPasswordOtp(int otp) {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
    	String expiry = currentDateTime.toString(); 
    	
    	String insertUser = "INSERT INTO cse360users (otp, expiry) VALUES (?, ?)";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setInt(1, otp);
			pstmt.setString(2, expiry);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	public boolean register(String username, String password, int otp) throws SQLException {
		if(doesUserExist(username)){
			return false;
		}
		String insertUser = "UPDATE cse360users SET username = ?, password = ?, otp = ? WHERE otp = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setNull(3, java.sql.Types.INTEGER); // Set otp to null
			pstmt.setInt(4, otp);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
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
	
	public String getRole(String username) throws SQLException {
		String query = "SELECT * FROM cse360users WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return rs.getString("role");  
				}
			}
		}
		return "";
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
	
	public boolean deleteUser(String username) {
		if(!doesUserExist(username)){
			return false;
		}
		String query = "DELETE FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setString(1, username);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	public boolean resetPassword(int otp, String password) {
		String insertUser = "UPDATE cse360users SET password = ? WHERE otp = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, password);
			pstmt.setInt(2, otp);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean editRole(String username, String role) {
		String insertUser = "UPDATE cse360users SET role = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(insertUser)) {
			pstmt.setString(1, role);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
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
	
	public int numberOfUsers() throws SQLException {
		String sql = "SELECT COUNT(*) FROM cse360users WHERE username IS NOT NULL"; 
	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        if (rs.next()) {
	            return rs.getInt(1); // Retrieve the count from the result set
	        }
	    }
	    return 0; // Return 0 if no result
	}
	
	public List<String[]> getAllUsers() throws SQLException {
	    String sql = "SELECT * FROM cse360users ORDER BY id ASC OFFSET 1"; // Skips the first row
	    List<String[]> users = new ArrayList<>();
	    
	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            users.add(new String[] {rs.getString("username"),rs.getString("preferredname"),rs.getString("role")}); // Adjust to retrieve specific columns
	        }
	    }
	    return users;
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
			System.out.println(", Otp: " + otp); 
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
	
	public boolean addStudent(String username) {
		String roleQuery = "INSERT INTO cse360users (role, username) VALUES (?, ?)";
		try(PreparedStatement pstmt = connection.prepareStatement(roleQuery)){
			pstmt.setString(1, "student");
	        pstmt.setString(2, username);
	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeStudent(String username) {
	    String roleQuery = "DELETE FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(roleQuery)) {
	        pstmt.setString(1, username);
	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
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
