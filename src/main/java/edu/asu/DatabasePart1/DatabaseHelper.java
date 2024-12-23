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
	protected Statement statement = null; 
	//	PreparedStatement pstmt

	public void clearUserTable() {
	    String query = "DELETE FROM cse360users";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.executeUpdate();
	        System.out.println("All rows have been cleared from cse360users table.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
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
				+ "username VARCHAR(255) UNIQUE, "
				+ "email VARCHAR(255) UNIQUE, "
				+ "firstname VARCHAR(255), "
				+ "middlename VARCHAR(255), "
				+ "lastname VARCHAR(255), "
				+ "preferredname VARCHAR(255), "
				+ "password VARCHAR(255), "
				+ "role VARCHAR(20), "
				+ "specialAccessGroup TEXT DEFAULT '', "
				+ "viewAccess TEXT DEFAULT NULL,"
				+ "specialAdminAccess TEXT DEFAULT '',"
				+ "otp INT UNIQUE,"
				+ "expiry DATETIME,"
				+ "MESSAGES VARCHAR(255))";
		statement.execute(userTable);
		
	}
	
	public List<String> listUserViewAccess(String username){
		String Groups = "";
		String sql = "SELECT viewAccess FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, username);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                Groups = rs.getString("viewAccess"); // Retrieve the special access group string
	            }
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    List<String> groups = new ArrayList<>(List.of(Groups.split(", ")));
	    
	    return groups;
	}
	
	public List<String> listUserSpecialViewAccess(String username){
		String Groups = "";
		String sql = "SELECT specialAccessGroup FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, username);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                Groups = rs.getString("specialAccessGroup"); // Retrieve the special access group string
	            }
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    List<String> groups = new ArrayList<>(List.of(Groups.split(", ")));
	    
	    return groups;
	}
	
	public List<String> listAllViewAccess(String username){
		List<String> groups = listUserSpecialViewAccess(username);
		groups.addAll(listUserViewAccess(username));
		return groups;
	}
	
	public List<String> listUserSpecialAdminAccess(String username){
		String Groups = "";
		String sql = "SELECT specialAdminAccess FROM cse360users WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, username);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                Groups = rs.getString("specialAdminAccess"); // Retrieve the special access group string
	            }
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    List<String> groups = new ArrayList<>(List.of(Groups.split(", ")));
	    
	    return groups;
	}
	
	public List<String> listAllSpecialAccessGroup(){
		String currentGroup = "";
		 String sql = "SELECT specialAdminAccess FROM cse360users WHERE id = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setInt(1, 1);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                currentGroup = rs.getString("specialAdminAccess"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		List<String> groups = new ArrayList<>(List.of(currentGroup.split(", ")));
		return null;
	}
	
	public boolean setSpecialNewGroup(String Group) {
		String query = "UPDATE cse360users SET specialAdminAccess = ? WHERE id = 1";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, Group);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean setSpecialAdminAccess(String username, String Group) {
		String currentGroup = "";
		 String sql = "SELECT specialAdminAccess FROM cse360users WHERE username = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                currentGroup = rs.getString("specialAdminAccess"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(currentGroup!=null) {
			Group = currentGroup +", " + Group; 
		}
		    
		String query = "UPDATE cse360users SET specialAdminAccess = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, Group);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean removeSpecialAdminAccess(String username, String Group) {
		String currentGroup = "";
		 String sql = "SELECT specialAdminAccess FROM cse360users WHERE username = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		            	currentGroup = rs.getString("specialAdminAccess"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(currentGroup!=null) {
			List<String> groups = new ArrayList<>(List.of(currentGroup.split(", ")));
	        if (groups.remove(Group)) {
	        	if(groups.isEmpty()) {
	        		String query = "UPDATE cse360users SET specialAdminAccess = NULL WHERE username = ?";
	        		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        			pstmt.setString(1, username);
	        			pstmt.executeUpdate();
	        		} catch (SQLException e) {
	        	        e.printStackTrace();
	        	        return false;
	        	    }
	        		return true;
	        	}
	        	currentGroup = String.join(", ", groups);
	        } else {
	            System.out.println("Group not found: " + Group);
	            return false; // Group not found, nothing to remove
	        }
		}
		    
		String query = "UPDATE cse360users SET viewAccess = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, currentGroup);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean doesUserHaveSpecialAccess(String username, String specialAccessGroup) {
		String query = "SELECT COUNT(*) FROM cse360users WHERE username = ? AND specialAccessGroup LIKE ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, username);
	        pstmt.setString(2, "%"+specialAccessGroup+"%");
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
	
	public boolean doesUserHaveAccess(String username, String Group) {
		String query = "SELECT COUNT(*) FROM cse360users WHERE username = ? AND viewAccess LIKE ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        
	        pstmt.setString(1, username);
	        pstmt.setString(2, "%"+Group+"%");
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
	
	public boolean setViewAccessGroupUser(String username, String Group) {
		String currentGroup = "";
		 String sql = "SELECT viewAccess FROM cse360users WHERE username = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                currentGroup = rs.getString("viewAccess"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(currentGroup!=null) {
			Group = currentGroup +", " + Group; 
		}
		    
		String query = "UPDATE cse360users SET viewAccess = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, Group);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean removeViewAccessGroupUser(String username, String Group) {
		String currentGroup = "";
		 String sql = "SELECT viewAccess FROM cse360users WHERE username = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		            	currentGroup = rs.getString("viewAccess"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(currentGroup!=null) {
			List<String> groups = new ArrayList<>(List.of(currentGroup.split(", ")));
	        if (groups.remove(Group)) {
	        	if(groups.isEmpty()) {
	        		String query = "UPDATE cse360users SET viewAccess = NULL WHERE username = ?";
	        		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        			pstmt.setString(1, username);
	        			pstmt.executeUpdate();
	        		} catch (SQLException e) {
	        	        e.printStackTrace();
	        	        return false;
	        	    }
	        		return true;
	        	}
	        	currentGroup = String.join(", ", groups);
	        } else {
	            System.out.println("Group not found: " + Group);
	            return false; // Group not found, nothing to remove
	        }
		}
		    
		String query = "UPDATE cse360users SET viewAccess = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, currentGroup);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean setSpecialAccessGroupUser(String username, String specialAccessGroup) {
		String currentSpecialAccessGroup = "";
		 String sql = "SELECT specialAccessGroup FROM cse360users WHERE username = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                currentSpecialAccessGroup = rs.getString("specialAccessGroup"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(currentSpecialAccessGroup!=null) {
			specialAccessGroup = currentSpecialAccessGroup +", " + specialAccessGroup; 
		}
		    
		String query = "UPDATE cse360users SET specialAccessGroup = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, specialAccessGroup);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public boolean removeSpecialAccessGroupUser(String username, String specialAccessGroup) {
		String currentSpecialAccessGroup = "";
		 String sql = "SELECT specialAccessGroup FROM cse360users WHERE username = ?";
		    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		        pstmt.setString(1, username);
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                currentSpecialAccessGroup = rs.getString("specialAccessGroup"); // Retrieve the special access group string
		            }
		        }
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(currentSpecialAccessGroup!=null) {
			List<String> groups = new ArrayList<>(List.of(currentSpecialAccessGroup.split(", ")));
	        if (groups.remove(specialAccessGroup)) {
	        	if(groups.isEmpty()) {
	        		String query = "UPDATE cse360users SET specialAccessGroup = NULL WHERE username = ?";
	        		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        			pstmt.setString(1, username);
	        			pstmt.executeUpdate();
	        		} catch (SQLException e) {
	        	        e.printStackTrace();
	        	        return false;
	        	    }
	        		return true;
	        	}
	            currentSpecialAccessGroup = String.join(", ", groups);
	        } else {
	            System.out.println("Group not found: " + specialAccessGroup);
	            return false; // Group not found, nothing to remove
	        }
		}
		    
		String query = "UPDATE cse360users SET specialAccessGroup = ? WHERE username = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, currentSpecialAccessGroup);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
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
	
	public boolean isOtpResetPassword(int otp) {
	    String query = "SELECT COUNT(*) FROM cse360users WHERE otp = ? AND username IS NOT NULL";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, otp); // Set the OTP value
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            // Check if the count is greater than 0
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // Return false if no match is found or an error occurs
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
	    String query = "SELECT expiry FROM cse360users WHERE otp = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, otp);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String expiry = rs.getString("expiry"); // Ensure the column name matches the database
	            if (expiry != null) {
	                LocalDateTime currentDateTime = LocalDateTime.now();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	                LocalDateTime dateTime = LocalDateTime.parse(expiry, formatter);

	                // Check if the OTP has expired
	                if (dateTime.isBefore(currentDateTime)) {
	                    // OTP has expired, clear it
	                    clearOtp(otp);
	                    return false;
	                }
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	// Helper method to clear the OTP after validation
	private void clearOtp(int otp) {
	    String updateQuery = "UPDATE cse360users SET otp = NULL, expiry = NULL WHERE otp = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
	        pstmt.setInt(1, otp);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void storeResetPasswordOtp(int otp, String username) {
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    LocalDateTime oneHourFromNow = currentDateTime.plusHours(1);

	    // Format expiry timestamp for compatibility with the database
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String expiry = oneHourFromNow.format(formatter);

	    // Use UPDATE instead of INSERT for existing rows
	    String updateUser = "UPDATE cse360users SET otp = ?, expiry = ? WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(updateUser)) {
	        pstmt.setInt(1, otp);          // Set the OTP
	        pstmt.setString(2, expiry);   // Set the formatted expiry timestamp
	        pstmt.setString(3, username); // Set the username for the WHERE clause

	        int rowsUpdated = pstmt.executeUpdate();

	        if (rowsUpdated == 0) {
	            System.out.println("No user found with the username: " + username);
	        } else {
	            System.out.println("OTP and expiry updated successfully for user: " + username);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error while updating OTP and expiry for user: " + username);
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
			int rowsUpdated = pstmt.executeUpdate(); // Get the number of rows updated
	        return rowsUpdated > 0; // Return true if at least one row was updated
		} catch (SQLException e) {
	        e.printStackTrace();
	        return false;
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
	
	public boolean sendHelpMessage(String message, String username) {
	    String updateQuery = "UPDATE cse360users SET MESSAGES = ? WHERE username = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(updateQuery)) {
	        pstmt.setString(1, message);
	        pstmt.setString(2, username);
	        int rowsUpdated = pstmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            return true; // Successfully updated the message
	        } else {
	            System.out.println("No user found with the given username.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return false; // Update failed
	}

	
	public List<String> getAllMessages() {
	    List<String> messages = new ArrayList<>();
	    String retrieveQuery = "SELECT username, MESSAGES FROM cse360users";
	    try (PreparedStatement pstmt = connection.prepareStatement(retrieveQuery)) {
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String username = rs.getString("username");
	            String message = rs.getString("MESSAGES");
	            messages.add(username + ": " + message);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return messages;
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

	public List<String[]> getAllStudents() throws SQLException {
	    String sql = "SELECT * FROM cse360users WHERE role = 'student'"; // Skips the first row
	    List<String[]> users = new ArrayList<>();
	    
	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            users.add(new String[] {rs.getString("username"),rs.getString("preferredname"),rs.getString("role")}); // Adjust to retrieve specific columns
	        }
	    }
	    return users;
	}
	
	public List<String[]> listArticlesByKeyword(String keyword) throws SQLException{
    	String query = "SELECT * FROM cse360articles WHERE title LIKE ? OR abstract LIKE ? OR author LIKE ?";
    	List<String[]> articles = new ArrayList<>();
    	try(PreparedStatement pstmt = connection.prepareStatement(query)){
    		pstmt.setString(1, "%" + keyword + "%");
    		pstmt.setString(2, "%" + keyword + "%");
    		pstmt.setString(3, "%" + keyword + "%");
    		ResultSet rs = pstmt.executeQuery();
    		while (rs.next()) {
    			long uid = rs.getLong("uid");
    			String level = rs.getString("level");
                String title = rs.getString("title");
                String authors = rs.getString("author");
                String groups = rs.getString("groupName");
                
                articles.add(new String[] {Long.toString(uid), level, title, authors, groups});
    			
    			
    		}
    		
    	}catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	return articles;
    				
    }
	
	public List<String[]> getAllInstructors() throws SQLException {
	    String sql = "SELECT * FROM cse360users WHERE role = ?"; // Skips the first row
	    List<String[]> users = new ArrayList<>();
	    
	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        
	        pstmt.setString(1, "instructor");
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            users.add(new String[] {rs.getString("username"),rs.getString("preferredname"),rs.getString("role")}); // Adjust to retrieve specific columns
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return users;
	}
	
	public List<String[]> getAllAdmins() throws SQLException {
	    String sql = "SELECT * FROM cse360users WHERE role = 'admin' ORDER BY id ASC OFFSET 1"; 
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
