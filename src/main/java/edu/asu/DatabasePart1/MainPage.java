//package edu.asu.DatabasePart1;
//
////JavaFX imports needed to support the Graphical User Interface
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.scene.control.Button;
//
//public class MainPage extends Application {
//
//    // Constants for window size
//    public final static double WINDOW_WIDTH = 500;
//    public final static double WINDOW_HEIGHT = 430;
//
//    // Increased size for Admin Page window
//    public final static double ADMIN_WINDOW_WIDTH = 500;
//    public final static double ADMIN_WINDOW_HEIGHT = 500;
//
//    // Objects referencing the application's interfaces
//    public StartPage StartGui;
//    public CompleteProfilePage CompleteProfileGui;
//    public AdminPage AdminGui;
//    public LoginPage LoginGui;
//    public HomePage HomeGui;
//    public RegisterPage RegisterGui;
//
//    @Override
//    public void start(Stage theStage) throws Exception {
//
//        theStage.setTitle("Choose or Register"); // Set the window title
//
//        // Set up the StartPage UI
//        Pane StartPane = new Pane();
//        StartGui = new StartPage(StartPane);
//        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//        Button buttonRegister = StartGui.InviteButton(); // Get the existing Register button
//        Button buttonLogin = StartGui.LoginButton(); // Get the existing Login button from StartPage
//
//        // Set up the RegisterPage UI
//        Pane RegisterPane = new Pane();
//        RegisterGui = new RegisterPage(RegisterPane);
//        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//        Button switchToComplete = RegisterGui.getButton1();
//
//        // Set up the AdminPage UI with larger window size
//        Pane AdminPane = new Pane();
//        AdminGui = new AdminPage(AdminPane);
//        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // Larger Admin window
//
//        // Set up the LoginPage UI
//        Pane LoginPane = new Pane();
//        LoginGui = new LoginPage(LoginPane, theStage, AdminScene); // Pass AdminScene to LoginPage
//        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//        // Set up the CompleteProfilePage UI
//        Pane CompleteProfilePane = new Pane();
//        CompleteProfileGui = new CompleteProfilePage(CompleteProfilePane);
//        Scene CompleteProfileScene = new Scene(CompleteProfilePane, WINDOW_WIDTH, WINDOW_HEIGHT);
//        Button buttonNavigateToHome = CompleteProfileGui.getButton();
//
//        // Set up the HomePage UI
//        Pane HomePane = new Pane();
//        HomeGui = new HomePage(HomePane);
//        Scene HomeScene = new Scene(HomePane, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//        // Handle button actions to switch between scenes
//        buttonRegister.setOnAction(event -> {
//            theStage.setScene(RegisterScene);
//        });
//
//        // Switch to the LoginPage when login button is clicked on StartPage
//        buttonLogin.setOnAction(event -> {
//            theStage.setScene(LoginScene); // Switch to the login scene
//        });
//
//        buttonNavigateToHome.setOnAction(event -> {
//            theStage.setScene(HomeScene);
//        });
//
//        switchToComplete.setOnAction(event -> {
//            theStage.setScene(CompleteProfileScene);
//        });
//
//        theStage.setScene(StartScene); // Show the StartPage first
//        theStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}


//package edu.asu.DatabasePart1;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import java.util.ArrayList;
//import javafx.geometry.Pos;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import java.util.ArrayList;
//
//public class MainPage extends Application {
//
//    // Constants for window size
//    public final static double WINDOW_WIDTH = 500;
//    public final static double WINDOW_HEIGHT = 430;
//
//    // Increased size for Admin Page window
//    public final static double ADMIN_WINDOW_WIDTH = 600;
//    public final static double ADMIN_WINDOW_HEIGHT = 600;
//
//    // Objects referencing the application's interfaces
//    public StartPage StartGui;
//    public AdminPage AdminGui;
//    public LoginPage LoginGui;
//
//    // ArrayList to store usernames and passwords
//    private ArrayList<String[]> credentials = new ArrayList<>();
//
//    @Override
//    public void start(Stage theStage) throws Exception {
//
//        theStage.setTitle("Choose or Register"); // Set the window title
//
//        // Set up the StartPage UI
//        Pane StartPane = new Pane();
//        StartGui = new StartPage(StartPane);
//        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//        Button buttonLogin = StartGui.LoginButton(); // Get the existing Login button from StartPage
//
//        // Set up the AdminPage UI with larger window size
//        Pane AdminPane = new Pane();
//        AdminGui = new AdminPage(AdminPane);
//        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // Larger Admin window
//
//        // Set up the LoginPage UI and pass the credentials array
//        Pane LoginPane = new Pane();
//        LoginGui = new LoginPage(LoginPane, theStage, AdminScene, credentials); // Pass AdminScene and credentials
//        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//        // Handle button actions to switch between scenes
//        buttonLogin.setOnAction(event -> {
//            theStage.setScene(LoginScene); // Switch to the login scene
//        });
//
//        theStage.setScene(StartScene); // Show the StartPage first
//        theStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//
//package edu.asu.DatabasePart1;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.control.Button;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class MainPage extends Application {
//
//    // Constants for window size
//    public final static double WINDOW_WIDTH = 500;
//    public final static double WINDOW_HEIGHT = 430;
//
//    // Increased size for Admin Page window
//    public final static double ADMIN_WINDOW_WIDTH = 600;
//    public final static double ADMIN_WINDOW_HEIGHT = 600;
//
//    // Objects referencing the application's interfaces
//    public StartPage StartGui;
//    public AdminPage AdminGui;
//    public LoginPage LoginGui;
//    public RegisterPage RegisterGui;
//
//    @Override
//    public void start(Stage theStage) throws Exception {
//
//        theStage.setTitle("Choose or Register"); // Set the window title
//
//        // Set up the StartPage UI
//        Pane StartPane = new Pane();
//
//        // Set up the RegisterPage UI
//        Pane RegisterPane = new Pane();
//        RegisterGui = new RegisterPage(RegisterPane);
//        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // RegisterPage scene
//
//        // Set up the AdminPage UI with larger window size
//        Pane AdminPane = new Pane();
//        AdminGui = new AdminPage(AdminPane);
//        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // Larger Admin window
//
//        // Set up the LoginPage UI
//        Pane LoginPane = new Pane();
//        LoginGui = new LoginPage(LoginPane, theStage, AdminScene); // Pass AdminScene to LoginPage
//        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//
//        // Update the StartPage constructor to pass theStage and RegisterScene
//        StartGui = new StartPage(StartPane, theStage, RegisterScene);  // Pass the RegisterScene and the Stage
//
//        // Handle button actions to switch between scenes
//        Button buttonLogin = StartGui.LoginButton();
//        buttonLogin.setOnAction(event -> {
//            theStage.setScene(LoginScene); // Switch to the login scene
//        });
//
//        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT);
//        theStage.setScene(StartScene); // Show the StartPage first
//        theStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

//package edu.asu.DatabasePart1;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.control.Button;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class MainPage extends Application {
//
//    public final static double WINDOW_WIDTH = 500;
//    public final static double WINDOW_HEIGHT = 430;
//
//    public final static double ADMIN_WINDOW_WIDTH = 600;
//    public final static double ADMIN_WINDOW_HEIGHT = 600;
//
//    public StartPage StartGui;
//    public AdminPage AdminGui;
//    public LoginPage LoginGui;
//    public RegisterPage RegisterGui;
//
//    @Override
//    public void start(Stage theStage) throws Exception {
//
//        theStage.setTitle("Choose or Register");
//
//        // Set up the StartPage UI
//        Pane StartPane = new Pane();
//
//        // Set up the AdminPage UI with larger window size
//        Pane AdminPane = new Pane();
//        AdminGui = new AdminPage(AdminPane);
//        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // AdminPage scene
//
//        // Set up the LoginPage UI
//        Pane LoginPane = new Pane();
//        LoginGui = new LoginPage(LoginPane, theStage, AdminScene); // Pass AdminScene to LoginPage
//        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT); // LoginPage scene
//
//        // Set up the RegisterPage UI
//        Pane RegisterPane = new Pane();
//        RegisterGui = new RegisterPage(RegisterPane, theStage, LoginScene); // Pass LoginScene to RegisterPage
//        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // RegisterPage scene
//
//        // Initialize StartPage and pass RegisterScene
//        StartGui = new StartPage(StartPane, theStage, RegisterScene);
//        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT); // StartPage scene
//
//        // Handle button actions to switch between scenes
//        Button buttonLogin = StartGui.LoginButton();
//        buttonLogin.setOnAction(event -> {
//            theStage.setScene(LoginScene); // Switch to the login scene
//        });
//
//        theStage.setScene(StartScene); // Show the StartPage first
//        theStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}



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

//public class MainPage extends Application {
//
//    // Constants for window size
//    public final static double WINDOW_WIDTH = 500;
//    public final static double WINDOW_HEIGHT = 430;
//
//    public final static double ADMIN_WINDOW_WIDTH = 600;
//    public final static double ADMIN_WINDOW_HEIGHT = 600;
//
//    // Objects referencing the application's interfaces
//    public StartPage StartGui;
//    public AdminPage AdminGui;
//    public LoginPage LoginGui;
//    public RegisterPage RegisterGui;
//    public CompleteProfilePage CompleteProfileGui;
//    public HomePage HomeGui;
//
//    @Override
//    public void start(Stage theStage) throws Exception {
//
//        theStage.setTitle("Choose or Register"); // Set the window title
//
//        // Set up the HomePage UI
//        Pane HomePane = new Pane();
//        HomeGui = new HomePage(HomePane);
//        Scene HomeScene = new Scene(HomePane, WINDOW_WIDTH, WINDOW_HEIGHT); // HomePage scene
//
//        // Set up the CompleteProfilePage UI
//        Pane CompleteProfilePane = new Pane();
//        CompleteProfileGui = new CompleteProfilePage(CompleteProfilePane, theStage, HomeScene); // Pass HomeScene to CompleteProfilePage
//        Scene CompleteProfileScene = new Scene(CompleteProfilePane, WINDOW_WIDTH, WINDOW_HEIGHT); // CompleteProfilePage scene
//
//        // Set up the AdminPage UI with larger window size
//        Pane AdminPane = new Pane();
//        AdminGui = new AdminPage(AdminPane);
//        Scene AdminScene = new Scene(AdminPane, ADMIN_WINDOW_WIDTH, ADMIN_WINDOW_HEIGHT); // AdminPage scene
//
//        // Set up the LoginPage UI
//        Pane LoginPane = new Pane();
//        LoginGui = new LoginPage(LoginPane, theStage, AdminScene, CompleteProfileScene, false); // Pass AdminScene to LoginPage
//        Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT); // LoginPage scene
//
//        // Set up the RegisterPage UI
//        Pane RegisterPane = new Pane();
//        RegisterGui = new RegisterPage(RegisterPane, theStage, LoginScene); // Pass LoginScene to RegisterPage
//        Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // RegisterPage scene
//
//        // Set up the StartPage UI
//        Pane StartPane = new Pane();  // Initialize StartPane
//        StartGui = new StartPage(StartPane, theStage, RegisterScene); // Pass RegisterScene to StartPage
//        Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT); // StartPage scene
//
//        // Handle button actions to switch between scenes
//        Button buttonLogin = StartGui.LoginButton(); // Get the existing Login button from StartPage
//        buttonLogin.setOnAction(event -> {
//            theStage.setScene(LoginScene); // Switch to the login scene
//        });
//
//        theStage.setScene(StartScene); // Show the StartPage first
//        theStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

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