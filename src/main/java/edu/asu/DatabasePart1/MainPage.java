
package edu.asu.DatabasePart1;

import javafx.application.Application;
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


public class MainPage extends Application {

    // Constants for window size
    public final static double WINDOW_WIDTH = 500;
    public final static double WINDOW_HEIGHT = 430;

    public final static double ADMIN_WINDOW_WIDTH = 600;
    public final static double ADMIN_WINDOW_HEIGHT = 600;

    // Objects referencing the application's interfaces
    public StartPage StartGui;
    public AdminPage AdminGui;
    public LoginPage LoginGui;
    public RegisterPage RegisterGui;
    public CompleteProfilePage CompleteProfileGui;
    public HomePage HomeGui;

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

    public static void main(String[] args) {
        launch(args);
    }
}