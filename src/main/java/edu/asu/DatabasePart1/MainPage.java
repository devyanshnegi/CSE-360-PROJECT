
package edu.asu.DatabasePart1;

import javafx.application.Application;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***
 * <p>
 * MainPage Class
 * </p>
 * 
 * <p>
 * Description: A JavaFX application that serves as the main entry point for
 *              navigating between various user interface pages in the system.
 * </p>
 * 
 * <p>
 * Copyright: Lynn Robert Carter © 2022
 * </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 4.00 2017-10-16 The mainline of a JavaFX-based GUI application
 * @version 5.00 2022-09-22 Updated for use at ASU
 * 
 */
public class MainPage extends Application {
    /** The width of the pop-up window for the user interface */
    public final static double WINDOW_WIDTH = 500;
    /** The height of the pop-up window for the user interface */
    public final static double WINDOW_HEIGHT = 430;

    // Objects referencing the various pages of the application
    public StartPage StartGui;          // Interface for the start page
    public CompleteProfilePage CompleteProfileGui; // Interface for completing user profiles
    public AdminPage AdminGui;          // Interface for the admin page
    public LoginPage LoginGui;          // Interface for the login page
    public HomePage HomeGui;            // Interface for the home page
    public RegisterPage RegisterGui;    // Interface for the registration page

    /** The default constructor */
    public MainPage() {
    }

    /****
     * This is the start method that is called once the application has been loaded
     * into memory and is ready to get to work.
     * 
     * Here, multiple panes and scenes are created for different user interfaces,
     * and action events are set for buttons to navigate between these interfaces.
     */
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Choose or Register"); // Label the stage (a window)

        // Create the initial pane for the start page
        Pane StartPane = new Pane(); 
        StartGui = new StartPage(StartPane); // Create the Graphical User Interface
        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene

        // Create the button for registration on the start page
        Button buttonRegister = StartGui.InviteButton();		
		
        // Create the admin page's pane and scene
        Pane AdminPane = new Pane();		
        AdminGui = new AdminPage(AdminPane);
        Scene AdminScene = new Scene(AdminPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
        // Create the registration page's pane and scene
        Pane RegisterPane = new Pane();
        RegisterGui = new RegisterPage(RegisterPane);
        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
        Button switchToComplete = RegisterGui.getButton1(); // Button to switch to complete profile page
		
        // Create the login page's pane and scene
        Pane LoginPane = new Pane();
        LoginGui = new LoginPage(LoginPane);
        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
        // Create the complete profile page's pane and scene
        Pane CompleteProfilePane = new Pane();
        CompleteProfileGui = new CompleteProfilePage(CompleteProfilePane); // Create the Graphical User Interface
        Scene CompleteProfileScene = new Scene(CompleteProfilePane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
        Button buttonNavigateToHome = CompleteProfileGui.getButton(); // Button to navigate to home page
		
        // Create the home page's pane and scene
        Pane HomePane = new Pane();
        HomeGui = new HomePage(HomePane);
        Scene HomeScene = new Scene(HomePane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
        Button ExitLogin; // TODO: Define the button for exiting login

        // Set action for registration button to switch to registration scene
        buttonRegister.setOnAction(event -> {
            theStage.setScene(RegisterScene);
        });
		
        // Set action for navigating to home page from complete profile page
        buttonNavigateToHome.setOnAction(event -> {
            theStage.setScene(HomeScene);
        });
		
        // Set action for switching to complete profile page from registration page
        switchToComplete.setOnAction(event -> {
            theStage.setScene(CompleteProfileScene);
        });
		
        // Set the initial scene to the start page and show the stage
        theStage.setScene(StartScene);
        theStage.show();
    }

    /********************************
     * This is the method that launches the JavaFX application
     * 
     * @param args The standard argument list for a Java Mainline
     */
    public static void main(String[] args) { // This method may not be required
        launch(args); // Launch the JavaFX application
    }
}