package edu.asu.DatabasePart1;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Random;

/*******
 * <p> StartCSE360 Class </p>
 * 
 * <p> Description: StartCSE360 serves as the entry point for a command-line-based 
 *                  application that interacts with a database. It supports setting up 
 *                  an administrator, user registration, and login processes for both 
 *                  users and administrators. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the StartCSE360 class for managing 
 *                  database interactions and user workflows.
 */

public class StartCSE360 {

    /** DatabaseHelper instance to handle database operations */
    private static final DatabaseHelper databaseHelper = new DatabaseHelper();
    
    /** Scanner instance for reading user input */
    private static final Scanner scanner = new Scanner(System.in);

    /**********
     * main method
     * 
     * <p> The main entry point for the StartCSE360 application. It connects to the 
     *     database, checks if the database is empty, and directs the user to the 
     *     appropriate workflow (administrator or user). </p>
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Random rand = new Random();

        try {
            databaseHelper.connectToDatabase(); // Connect to the database
            String email = "dnegi4@asu.edu";
            String name = "Devyansh";

            // Check if the database is empty (no users registered)
            if (databaseHelper.isDatabaseEmpty()) {
                System.out.println("In-Memory Database is empty");
                // Set up administrator access
                setupAdministrator();
            } else {
                databaseHelper.displayUsersByAdmin();
                if (databaseHelper.isOTPValid(114897)) {
                    databaseHelper.register("user1", "user1", 114897);
                }
                databaseHelper.displayUsersByAdmin();
                System.exit(0);

                System.out.println("If you are an administrator, then select A\nIf you are a user, then select U\nEnter your choice: ");
                String role = scanner.nextLine();
                switch (role) {
                    case "U":
                        userFlow();
                        break;
                    case "A":
                        adminFlow();
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 'A' for admin or 'U' for user.");
                        databaseHelper.closeConnection();
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Goodbye!");
            databaseHelper.closeConnection();
        }
    }

    /**********
     * setupAdministrator method
     * 
     * <p> Sets up the initial administrator account if the database is empty. Prompts 
     *     the user to enter an email and password for the admin account. </p>
     * 
     * @throws SQLException if a database access error occurs.
     */
    private static void setupAdministrator() throws SQLException {
        System.out.println("Setting up the Administrator access.");
        System.out.print("Enter Admin Email: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        databaseHelper.register(username, password, 000000);
        System.out.println("Administrator setup completed.");
    }

    /**********
     * userFlow method
     * 
     * <p> Manages the user workflow, including registration and login processes. 
     *     Prompts the user to choose between registration and login, and performs 
     *     the respective action. </p>
     * 
     * @throws SQLException if a database access error occurs.
     */
    private static void userFlow() throws SQLException {
        String username = null;
        String password = null;
        System.out.println("User flow");
        System.out.print("What would you like to do? 1. Register 2. Login ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.print("Enter User Email: ");
                username = scanner.nextLine();
                System.out.print("Enter User Password: ");
                password = scanner.nextLine();
                // Check if the user already exists in the database
                if (!databaseHelper.doesUserExist(username)) {
                    System.out.println("User setup completed.");
                } else {
                    System.out.println("User already exists.");
                }
                break;
            case "2":
                System.out.print("Enter User Email: ");
                username = scanner.nextLine();
                System.out.print("Enter User Password: ");
                password = scanner.nextLine();
                if (databaseHelper.login(username, password)) {
                    System.out.println("User login successful.");
                    databaseHelper.displayUsersByAdmin();
                } else {
                    System.out.println("Invalid user credentials. Try again!");
                }
                break;
        }
    }

    /**********
     * adminFlow method
     * 
     * <p> Manages the administrator workflow, including the login process. Prompts 
     *     the administrator to enter their credentials and grants access if the login 
     *     is successful. </p>
     * 
     * @throws SQLException if a database access error occurs.
     */
    private static void adminFlow() throws SQLException {
        System.out.println("Admin flow");
        System.out.print("Enter Admin Email: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        if (databaseHelper.login(username, password)) {
            System.out.println("Admin login successful.");
            databaseHelper.displayUsersByAdmin();
        } else {
            System.out.println("Invalid admin credentials. Try again!");
        }
    }
}
