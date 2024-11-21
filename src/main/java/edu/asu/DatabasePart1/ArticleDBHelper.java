	package edu.asu.DatabasePart1;
	
	
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.Writer;
	import java.sql.*;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
	
	
	/*******
	 * <p> DatabaseHelper Class </p>
	 * 
	 * <p> Description: This class handles database connections, operations such as storing, retrieving,
	 * creating backups, and restoring articles using encryption techniques. </p>
	 * 
	 * <p> Copyright: Lynn Robert Carter © 2022 </p>
	 * 
	 * @author Lynn Robert Carter
	 * 
	 * @version 1.00  2022-09-22 Initial implementation of database helper class for article management
	 * 
	 * @version 2.00 2024-10-20 Repurposed and modified for an article database 
	
	 */
	
	public class ArticleDBHelper {
	
	    // JDBC driver name and database URL 
	    static final String JDBC_DRIVER = "org.h2.Driver";   
	    static final String DB_URL = "jdbc:h2:~/Database/Database";  
	
	    //  Database credentials 
	    static final String USER = "sa"; 
	    static final String PASS = ""; 
	
	    private Connection connection = null;
	    private Statement statement = null; 
	
	    /*******
	     * Default constructor for the DatabaseHelper class. It initializes the EncryptionHelper object.
	     * 
	     * @throws Exception If there is an error during EncryptionHelper initialization.
	     */
	    /*******
	     * Connects to the database by loading the JDBC driver and establishing a connection.
	     * Also creates the necessary tables if they don't already exist.
	     * 
	     * @throws SQLException If there is an error during the database connection process.
	     */
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
	    
	    /*******
	     * Creates the necessary tables in the database, specifically the "articles" table.
	     * 
	     * @throws SQLException If there is an error during the table creation process.
	     */
	    private void createTables() throws SQLException {
	        String userTable = "CREATE TABLE IF NOT EXISTS cse360articles ("
	                + "id INT AUTO_INCREMENT PRIMARY KEY, "
	        		+ "uid BIGINT, "
	        		+ "level VARCHAR(255),"
	                + "title VARCHAR(255) UNIQUE, "
	                + "author TEXT, "
	                + "abstract TEXT,"
	                + "keywords TEXT,"
	                + "body TEXT,"
	                + "references TEXT,"
	                + "groupName TEXT,"
	        		+ "specialAccessGroup TEXT DEFAULT NULL)";
	        statement.execute(userTable);
	    }
	
	    /*******
	     * Lists all articles in the database by printing their ID, title, and author.
	     * 
	     * @throws Exception If there is an error during the retrieval of articles.
	     */
	    public void list() throws Exception {
	        String sql = "SELECT * FROM cse360articles"; 
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(sql); 
	
	        while(rs.next()) { 
	            // Retrieve by column name 
	            long id  = rs.getLong("uid"); 
	            String title = rs.getString("title"); 
	            String author = rs.getString("author");   
	            String specialAccessGroup = rs.getString("specialAccessGroup");   
	            String groupName = rs.getString("groupName"); 
	            System.out.print("ID: " + id); 
	            System.out.print(", Title: " + title);
	            System.out.print(", Author(s): " + author);
	            System.out.print(", GP: " + groupName); 
	            System.out.println(", SAGP: " + specialAccessGroup);
	        } 
	    }
	    
	    public List<String[]> listArticlesByGroups(String group) throws SQLException{
	    	String query = "SELECT * FROM cse360articles WHERE groupName LIKE ?";
	    	List<String[]> articles = new ArrayList<>();
	    	try(PreparedStatement pstmt = connection.prepareStatement(query)){
	    		pstmt.setString(1, "%" + group + "%");
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
	    
	    public List<String[]> listArticlesByGroupsAndLevel(String group, String levelSearch) throws SQLException{
	    	String query = "SELECT * FROM cse360articles WHERE groupName LIKE ? AND level = ?";
	    	List<String[]> articles = new ArrayList<>();
	    	try(PreparedStatement pstmt = connection.prepareStatement(query)){
	    		pstmt.setString(1, "%" + group + "%");
	    		pstmt.setString(2, levelSearch);
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
	    
	    public List<String[]> listArticlesBySpecialAccessGroups(String group) throws SQLException{
	    	String query = "SELECT * FROM cse360articles WHERE specialAccessGroup LIKE ?";
	    	List<String[]> articles = new ArrayList<>();
	    	try(PreparedStatement pstmt = connection.prepareStatement(query)){
	    		pstmt.setString(1, "%" + group + "%");
	    		ResultSet rs = pstmt.executeQuery();
	    		while (rs.next()) {
	    			long uid = rs.getLong("uid");
	    			String level = rs.getString("level");
	                String title = rs.getString("title");
	                String authors = rs.getString("author");
	                String groups = rs.getString("specialAccessGroup");
	                
	                articles.add(new String[] {Long.toString(uid), level, title, authors, groups});
	    			
	    			
	    		}
	    		
	    	}catch(SQLException e) {
	    		System.out.println(e.getMessage());
	    	}
	    	return articles;
	    				
	    }
	    
	    public List<String[]> listArticlesBySpecialAccessGroupsAndLevel(String group, String levelSearch) throws SQLException{
	    	String query = "SELECT * FROM cse360articles WHERE specialAccessGroup LIKE ? AND level = ?";
	    	List<String[]> articles = new ArrayList<>();
	    	try(PreparedStatement pstmt = connection.prepareStatement(query)){
	    		pstmt.setString(1, "%" + group + "%");
	    		pstmt.setString(2, levelSearch);
	    		ResultSet rs = pstmt.executeQuery();
	    		while (rs.next()) {
	    			long uid = rs.getLong("uid");
	    			String level = rs.getString("level");
	                String title = rs.getString("title");
	                String authors = rs.getString("author");
	                String groups = rs.getString("specialAccessGroup");
	                
	                articles.add(new String[] {Long.toString(uid), level, title, authors, groups});
	    			
	    			
	    		}
	    		
	    	}catch(SQLException e) {
	    		System.out.println(e.getMessage());
	    	}
	    	return articles;
	    				
	    }
	    
	    public List<String[]> listAllArticles() throws SQLException{
	    	String query = "SELECT * FROM cse360articles WHERE specialAccessGroup = NULL";
	    	List<String[]> articles = new ArrayList<>();
	    	try(PreparedStatement pstmt = connection.prepareStatement(query)){
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
	    
	    public List<String[]> viewArticle(long uid) throws Exception {
	        String query = "SELECT * FROM cse360articles WHERE uid = ?"; 
	        List<String[]> articles = new ArrayList<>();
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setLong(1, uid);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if(rs.next()) {
	                	String level = rs.getString("level"); 
	                	String title = rs.getString("title"); 
	                    String author = rs.getString("author");
	                    String articleAbstract = rs.getString("abstract");
	                    String keywords = rs.getString("keywords");
	                    String body = rs.getString("body");
	                    String references = rs.getString("references");
	                    String groupName = rs.getString("groupName");
	
	                    articles.add(new String[] {Long.toString(uid), level, title, author, articleAbstract, keywords, body , references, groupName});
	                }
	            }
	        }
			return articles;        
	    }
	
	    /*******
	     * Reads and decrypts a specific article from the database based on the provided article ID.
	     * 
	     * @param id The ID of the article to be retrieved.
	     * @throws Exception If there is an error during the article retrieval or decryption process.
	     */
	    public void readArticle(int id) throws Exception {
	        String query = "SELECT * FROM cse360articles WHERE id = ?"; 
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setInt(1, id);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if(rs.next()) {
	                	String level = rs.getString("level"); 
	                	String title = rs.getString("title"); 
	                    String author = rs.getString("author");
	                    String articleAbstract = rs.getString("abstract");
	                    String keywords = rs.getString("keywords");
	                    String body = rs.getString("body");
	                    String references = rs.getString("references");
	                    String groupName = rs.getString("groupName");
	
	                    // Display content
	                    System.out.println("Level: " + level); 
	                    System.out.println("Title: " + title); 
	                    System.out.println("Author(s): " + author); 
	                    System.out.println("Abstract: " + articleAbstract); 
	                    System.out.println("Keywords: " + keywords);
	                    System.out.println("Body: " + body); 
	                    System.out.println("References: " + references);
	                    System.out.println("groupName: " + groupName);
	                }
	            }
	        }        
	    }
	
	    /*******
	     * Stores an article in the database with encryption applied to the abstract, keywords, body, and references.
	     * 
	     * @param title The title of the article.
	     * @param author The author of the article.
	     * @param abstracts The abstract of the article.
	     * @param keywords The keywords associated with the article.
	     * @param body The body of the article.
	     * @param references The references of the article.
	     * @throws Exception If there is an error during the encryption or database insertion process.
	     */
	    public void storeArticle(String level, String title, String author, String abstracts, String keywords, String body, String references, String groupName) throws Exception {
	        
	    	long uid = System.currentTimeMillis();
	    	
	        String query = "INSERT INTO cse360articles (uid, level, title, author, abstract, keywords, body, references, groupName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setLong(1, uid);
	            pstmt.setString(2, level);
	            pstmt.setString(3, title);
	            pstmt.setString(4, author);
	            pstmt.setString(5, abstracts);
	            pstmt.setString(6, keywords);
	            pstmt.setString(7, body);
	            pstmt.setString(8, references);
	            pstmt.setString(9, groupName);
	            pstmt.executeUpdate();
	        }    
	    }
	    
public void storeArticleSpecial(String level, String title, String author, String abstracts, String keywords, String body, String references, String groupName) throws Exception {
	        
	    	long uid = System.currentTimeMillis();
	    	
	        String query = "INSERT INTO cse360articles (uid, level, title, author, abstract, keywords, body, references, specialAccessGroup) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setLong(1, uid);
	            pstmt.setString(2, level);
	            pstmt.setString(3, title);
	            pstmt.setString(4, author);
	            pstmt.setString(5, abstracts);
	            pstmt.setString(6, keywords);
	            pstmt.setString(7, body);
	            pstmt.setString(8, references);
	            pstmt.setString(9, groupName);
	            pstmt.executeUpdate();
	        }    
	    }
	    
public void updateArticle(long uid, String level, String title, String author, String abstracts, String keywords, String body, String references, String groupName) throws Exception {
	            	
	        String query = "UPDATE cse360articles SET level = ?, title = ?, author = ?, abstract = ?, keywords = ?, body = ?, references = ?, groupName = ? WHERE uid = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setString(1, level);
	            pstmt.setString(2, title);
	            pstmt.setString(3, author);
	            pstmt.setString(4, abstracts);
	            pstmt.setString(5, keywords);
	            pstmt.setString(6, body);
	            pstmt.setString(7, references);
	            pstmt.setString(8, groupName);
	            pstmt.setLong(9, uid);
	            pstmt.executeUpdate();
	        }    
	    }

public void updateArticleSpecial(long uid, String level, String title, String author, String abstracts, String keywords, String body, String references, String groupName) throws Exception {
	String sql = "UPDATE cse360articles SET groupName = NULL, specialAccessGroup = NULL WHERE uid = ?";
	try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setLong(1, uid);
        pstmt.executeUpdate();
    } 
    String query = "UPDATE cse360articles SET level = ?, title = ?, author = ?, abstract = ?, keywords = ?, body = ?, references = ?, specialAccessGroup = ?) WHERE uid = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setString(1, level);
        pstmt.setString(2, title);
        pstmt.setString(3, author);
        pstmt.setString(4, abstracts);
        pstmt.setString(5, keywords);
        pstmt.setString(6, body);
        pstmt.setString(7, references);
        pstmt.setString(8, groupName);
        pstmt.setLong(9, uid);
        pstmt.executeUpdate();
    }    
}
	
	    /*******
	     * Deletes an article from the database based on the provided article ID.
	     * 
	     * @param id The ID of the article to be deleted.
	     * @throws Exception If there is an error during the article deletion process.
	     */
	    public void deleteArticle(long uid) throws Exception {
	        String query = "DELETE FROM cse360articles WHERE uid = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setLong(1, uid);
	            pstmt.executeUpdate();
	        }    
	    }
	    
	    public int countArticles() throws SQLException{
	    	String query = "SELECT COUNT(*) FROM cse360articles";
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            if (rs.next()) {
	                return rs.getInt(1); // Retrieve the count from the first column
	            }
	        }
	        return 0; // Return 0 if no articles are found or query fails
	    }
	
	    /*******
	     * Creates a backup of all articles in the database by writing them to a specified file.
	     * 
	     * @param filename The name of the file where the backup will be saved.
	     * @throws Exception If there is an error during the backup process.
	     */
	    public void createBackup(String filename) throws Exception {
	        String query = "SELECT * FROM cse360articles"; 
	        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	            try (ResultSet rs = pstmt.executeQuery()){
	                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
	                while(rs.next()) {
	                	long uid = rs.getLong("uid"); 
	                	String level = rs.getString("level"); 
	                	 String title = rs.getString("title"); 
	                     String author = rs.getString("author");
	                     String articleAbstract = rs.getString("abstract");
	                     String keywords = rs.getString("keywords");
	                     String body = rs.getString("body");
	                     String references = rs.getString("references");
	                     String groupName = rs.getString("groupName");
	
	                     writer.write(uid +" | "+level+" | "+title + " | " + author + " | " + articleAbstract + " | " + keywords + " | " + body + " | " + references+" | "+groupName);
	                     writer.newLine();
	                }
	                writer.close();
	            }
	            catch (IOException e) {
	                System.out.print(e.getMessage());
	            }
	        }    
	    }
	
	    /*******
	     * Restores a backup by reading data from a file and re-inserting the articles into the database.
	     * 
	     * @param filename The name of the backup file to be restored.
	     * @throws Exception If there is an error during the restore process.
	     */
	    public void restoreBackup(String filename) throws Exception {
	        String query = "DROP TABLE IF EXISTS cse360articles";
	        try (Statement stmt = connection.createStatement()) {
	            stmt.execute(query);
	        }
	        createTables();
	        query = "INSERT INTO cse360articles (uid, level, title, author, abstract, keywords, body, references, groupName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filename)); 
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] split = line.split("\\|");
	                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	                    pstmt.setString(1, split[0]);
	                    pstmt.setString(2, split[1]);
	                    pstmt.setString(3, split[2]);
	                    pstmt.setString(4, split[3]);
	                    pstmt.setString(5, split[4]);
	                    pstmt.setString(6, split[5]);
	                    pstmt.setString(7, split[6]);
	                    pstmt.setString(8, split[7]);
	                    pstmt.setString(9, split[8]);
	                    pstmt.executeUpdate();
	                }    
	            }
	            reader.close();
	        }
	        catch (IOException e) {
	            System.out.print(e.getMessage());
	        }
	    }
	
	    /*******
	     * Closes the database connection and statement, if they are open.
	     */
	    public void closeConnection() {
	        try { 
	            if(statement != null) statement.close(); 
	        } catch(SQLException se2) { 
	            se2.printStackTrace();
	        } 
	        try { 
	            if(connection != null) connection.close(); 
	        } catch(SQLException se) { 
	            se.printStackTrace(); 
	        } 
	    }
	
	}
