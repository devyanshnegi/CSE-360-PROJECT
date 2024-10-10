package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/***
 * <p>
 * ControllerPage Class
 * </p>
 * 
 * <p>
 * Description: A JavaFX application that serves as the controller for the user interface,
 * facilitating user interaction through the CompleteProfilePage.
 * </p>
 * 
 * <p>
 * Copyright: Your Name © 2024
 * </p>
 * 
 * @author Your Name
 * 
 * @version 1.00 2024-10-09 Initial creation of the ControllerPage class to manage the 
 *         setup and display of the complete user profile interface.
 * 
 */

public class ControllerPage extends Application {
	/** The width of the pop-up window for the user interface */
	public final static double WINDOW_WIDTH = 500;
	/** The height of the pop-up window for the user interface */
	public final static double WINDOW_HEIGHT = 430;

	/** An object referencing the application's complete profile page interface */
	public CompleteProfilePage Gui;

	/** The default constructor for the ControllerPage class */
	public ControllerPage() {
		// No initialization required for the constructor at this moment
	}

	/****
	 * The start method is called once the application has been loaded into memory
	 * and is ready to be displayed to the user.
	 * 
	 * @param theStage The main stage for the JavaFX application
	 * @throws Exception If an error occurs during the loading of the application
	 */
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("Complete Set Up Page"); // Set the title of the window

		Pane Root1 = new Pane(); // Create a pane within the window for layout management

		Gui = new CompleteProfilePage(Root1); // Instantiate the CompleteProfilePage with the pane

		Scene theScene = new Scene(Root1, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene with the pane and dimensions

		theStage.setScene(theScene); // Set the scene on the stage

		theStage.show(); // Display the stage to the user

		// When the stage is shown, the user can interact with the GUI elements
		// (labels, fields, and buttons) visible within the pane. The user can enter
		// values, click buttons, and read the results or error messages displayed.
	}

	/******************************
	 * This method launches the JavaFX application.
	 * 
	 * @param args The standard argument list for a Java Mainline
	 */
	public static void main(String[] args) { // Entry point for the JavaFX application
		launch(args); // Launch the JavaFX application, calling the start method
	}
}