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

public class MainPage extends Application {

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

        theStage.setTitle("Choose or Register"); // Set the window title

        // Set up the HomePage UI
        Pane HomePane = new Pane();
        HomeGui = new HomePage(HomePane);
        Scene HomeScene = new Scene(HomePane, WINDOW_WIDTH, WINDOW_HEIGHT); // HomePage scene

        // Set up the CompleteProfilePage UI
        Pane CompleteProfilePane = new Pane();
        CompleteProfileGui = new CompleteProfilePage(CompleteProfilePane, theStage, HomeScene); // Pass HomeScene to CompleteProfilePage
        Scene CompleteProfileScene = new Scene(CompleteProfilePane, WINDOW_WIDTH, WINDOW_HEIGHT); // CompleteProfilePage scene

        // Set up the AdminPage UI with larger window size
        Pane AdminPane = new Pane();
        AdminGui = new AdminPage(AdminPane);
        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // AdminPage scene

        // Set up the LoginPage UI for normal login (not after registration)
        Pane LoginPaneNormal = new Pane();
        LoginGui = new LoginPage(LoginPaneNormal, theStage, AdminScene, CompleteProfileScene, false); // Pass AdminScene for normal login
        Scene LoginSceneNormal = new Scene(LoginPaneNormal, WINDOW_WIDTH, WINDOW_HEIGHT); // LoginPage scene for normal login

        // Set up the LoginPage UI after registration
        Pane LoginPaneAfterRegister = new Pane();
        LoginGui = new LoginPage(LoginPaneAfterRegister, theStage, AdminScene, CompleteProfileScene, true); // Pass CompleteProfileScene for login after registration
        Scene LoginSceneAfterRegister = new Scene(LoginPaneAfterRegister, WINDOW_WIDTH, WINDOW_HEIGHT); // LoginPage scene for login after registration

        // Set up the RegisterPage UI
        Pane RegisterPane = new Pane();
        RegisterGui = new RegisterPage(RegisterPane, theStage, LoginSceneAfterRegister); // After registration, redirect to LoginSceneAfterRegister
        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // RegisterPage scene

        // Set up the StartPage UI
        Pane StartPane = new Pane();  // Initialize StartPane
        StartGui = new StartPage(StartPane, theStage, RegisterScene); // Pass RegisterScene to StartPage
        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT); // StartPage scene

        // Handle button actions to switch between scenes
        Button buttonLogin = StartGui.LoginButton(); // Get the existing Login button from StartPage
        buttonLogin.setOnAction(event -> {
            theStage.setScene(LoginSceneNormal); // Switch to the normal login scene
        });

        theStage.setScene(StartScene); // Show the StartPage first
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
