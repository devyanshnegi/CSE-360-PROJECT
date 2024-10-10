package edu.asu.DatabasePart1;

//JavaFX imports needed to support the Graphical User Interface
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
/*******
 * <p>
 * PasswordAddressTestbed Class
 * </p>
 * 
 * <p>
 * Description: A JavaFX demonstration application and baseline for a sequence
 * of projects
 * </p>
 * 
 * <p>
 * Copyright: Lynn Robert Carter © 2022
 * </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 4.00 2017-10-16 The mainline of a JavaFX-based GUI implementation of
 *          a User Interface testbed
 * @version 5.00 2022-09-22 Updated for use at ASU
 * 
 */

public class MainPage extends Application {
	/** The width of the pop-up window for the user interface */
	public final static double WINDOW_WIDTH = 500;
	/** The height of the pop-up window for the user interface */
	public final static double WINDOW_HEIGHT = 430;

	// A object referencing the application's interface
	public StartPage StartGui;
	public CompleteProfilePage CompleteProfileGui;
	public AdminPage AdminGui;
	public LoginPage LoginGui;
	public HomePage HomeGui;
	public RegisterPage RegisterGui;
	
	
	
	public MainPage() {

	}

	public void start(Stage theStage) throws Exception {

		theStage.setTitle("Choose or Register"); // Label the stage (a window)

		Pane StartPane = new Pane(); // Create a pane within the window
		StartGui = new StartPage(StartPane); // Create the Graphical User Interface
		Scene StartScene = new Scene(StartPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		Button buttonRegister = StartGui.InviteButton();		
		
		Pane AdminPane = new Pane();		
		AdminGui = new AdminPage(AdminPane);
		Scene AdminScene = new Scene(AdminPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
		Pane RegisterPane = new Pane();
		RegisterGui = new RegisterPage(RegisterPane);
		Scene RegisterScene = new Scene(RegisterPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		Button switchToComplete = RegisterGui.getButton1();
		
		Pane LoginPane = new Pane();
		LoginGui = new LoginPage(LoginPane);
		Scene LoginScene = new Scene(LoginPane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
		Pane CompleteProfilePane = new Pane();
		CompleteProfileGui = new CompleteProfilePage(CompleteProfilePane); // Create the Graphical User Interface
		Scene CompleteProfileScene = new Scene(CompleteProfilePane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		Button buttonNavigateToHome = CompleteProfileGui.getButton();
		
		Pane HomePane = new Pane();
		HomeGui = new HomePage(HomePane);
		Scene HomeScene = new Scene(HomePane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		Button ExitLogin; // TODO

		buttonRegister.setOnAction(event ->{
			theStage.setScene(RegisterScene);
		});
		
		buttonNavigateToHome.setOnAction(event ->{
			theStage.setScene(HomeScene);
		});
		
		switchToComplete.setOnAction(event ->{
			theStage.setScene(CompleteProfileScene);
		});
		
		theStage.setScene(StartScene);
		theStage.show();

	}

	public static void main(String[] args) { // This method may not be required
		launch(args); // for all JavaFX applications using
	} 
}
