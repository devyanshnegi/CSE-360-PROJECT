package edu.asu.DatabasePart1;

import javafx.application.Application;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.sql.SQLException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;

/*******
 * <p> MainPage Class </p>
 * 
 * <p> Description: MainPage serves as the entry point for a multi-scene JavaFX-based 
 *                  user interface application, handling transitions between various 
 *                  pages like Login, Registration, Admin, and Home. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the MainPage class to manage 
 *                  application flow across multiple pages.
 */

public class MainApp extends Application {

    /** The width of the main application window in pixels */
    public final static double WINDOW_WIDTH = 500;
    
    /** The height of the main application window in pixels */
    public final static double WINDOW_HEIGHT = 430;
    
    /** The width of the admin page window in pixels */
    public final static double ADMIN_WINDOW_WIDTH = 600;
    
    /** The height of the admin page window in pixels */
    public final static double ADMIN_WINDOW_HEIGHT = 600;

    /** Reference to the application's StartPage interface */
    public StartPage StartGui;
    
    /** Reference to the application's AdminPage interface */
    public AdminPage AdminGui;
    
    /** Reference to the application's LoginPage interface */
    public LoginPage LoginGui;
    
    /** Reference to the application's RegisterPage interface */
    public RegisterPage RegisterGui;
    
    /** Reference to the application's CompleteProfilePage interface */
    public CompleteProfilePage CompleteProfileGui;
    
    /** Reference to the application's HomePage interface */
    public HomePage HomeGui;

    private static DatabaseHelper databaseHelper = new DatabaseHelper();
    
    /**********
     * start method
     * 
     * <p> Called once the application is launched. Sets up the various scenes and 
     *     transitions for different user interface components, managing the flow between 
     *     login, registration, admin, and profile completion. </p>
     * 
     * @param theStage The primary stage for this JavaFX application.
     * 
     * @throws Exception if there is an issue initializing the user interface.
     */
    @Override
    public void start(Stage theStage) throws Exception {

    	SceneController sceneController = new SceneController(theStage);
    	
        theStage.setTitle("CSE-360 Application"); // Set the window title

        // Set up the HomePage UI
        Pane HomePane = new Pane();
        new HomePage(HomePane, sceneController);
        Scene HomeScene = new Scene(HomePane, WINDOW_WIDTH, WINDOW_HEIGHT); // HomePage scene
        sceneController.addScene("Home",HomeScene);

        // Set up the CompleteProfilePage UI
        Pane CompleteProfilePane = new Pane();
        new CompleteProfilePage(CompleteProfilePane, sceneController); // Pass HomeScene to CompleteProfilePage
        Scene CompleteProfileScene = new Scene(CompleteProfilePane, WINDOW_WIDTH, WINDOW_HEIGHT); // CompleteProfilePage scene
        sceneController.addScene("CompleteProfile", CompleteProfileScene);

        // Set up the AdminPage UI with larger window size
        Pane AdminPane = new Pane();
        new AdminPage(AdminPane, sceneController);
        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // AdminPage scene
        sceneController.addScene("Admin", AdminScene);

        // Set up the LoginPage UI for normal login (not after registration)
        Pane LoginPane = new Pane();
        new LoginPage(LoginPane, sceneController, false); // Pass AdminScene for normal login
        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT); // LoginPage scene for normal login
        sceneController.addScene("Login", LoginScene);

        // Set up the LoginPage UI after registration
        Pane LoginToCompletePane = new Pane();
        new LoginPage(LoginToCompletePane, sceneController, true); // Pass AdminScene for normal login
        Scene LoginToCompleteScene = new Scene(LoginToCompletePane, WINDOW_WIDTH, WINDOW_HEIGHT); // LoginPage scene for normal login
        sceneController.addScene("LoginToComplete", LoginToCompleteScene);
        
        // Set up the RegisterPage UI
        Pane RegisterPane = new Pane();
        new RegisterPage(RegisterPane, sceneController); // After registration, redirect to LoginSceneAfterRegister
        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // RegisterPage scene
        sceneController.addScene("Register", RegisterScene);

        // Set up the StartPage UI
        Pane StartPane = new Pane();  // Initialize StartPane
        new StartPage(StartPane, sceneController); // Pass RegisterScene to StartPage
        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT); // StartPage scene
        sceneController.addScene("Start", StartScene);
        
        // Set up the InvitePage UI
        Pane invitePane = new Pane();
        new InvitePage(invitePane, sceneController);
        Scene inviteScene = new Scene(invitePane, WINDOW_WIDTH, WINDOW_HEIGHT);
        sceneController.addScene("Invite", inviteScene);
        sceneController.switchTo("Invite");
       /*
        try {
	        databaseHelper.connectToDatabase();
	        
	        databaseHelper.displayUsersByAdmin(); // REMOVE LATER
	        
	        if(databaseHelper.isDatabaseEmpty()) {
	        	int otp = 0;
	        	databaseHelper.storeOTP("admin", otp);
	        	sceneController.setData("otp", otp);
	        	databaseHelper.displayUsersByAdmin();
	        	sceneController.switchTo("Register");
	        }
	        else {
	        	sceneController.switchTo("Start");
	        }
	    } catch (SQLException e) {
	        // Handle SQL exceptions
	        System.err.println("Database error: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        // Ensure the database connection is closed when the application exits
	        databaseHelper.closeConnection();
	    }
        */
        theStage.show();
    }

    /**********
     * main method
     * 
     * <p> Launches the JavaFX application by calling the start method. </p>
     * 
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}
