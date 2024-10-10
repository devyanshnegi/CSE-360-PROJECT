package edu.asu.DatabasePart1;

//JavaFX imports needed to support the Graphical User Interface
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

public class ControllerPage extends Application {
	/** The width of the pop-up window for the user interface */
	public final static double WINDOW_WIDTH = 500;
	/** The height of the pop-up window for the user interface */
	public final static double WINDOW_HEIGHT = 430;

	// A object referencing the application's interface
	public chooseInterfaceController Gui;

	public ControllerPage() {

	}

	public void start(Stage theStage) throws Exception {

		theStage.setTitle("Complete Set Up Page"); // Label the stage (a window)

		Pane Root1 = new Pane(); // Create a pane within the window

		Gui = new chooseInterfaceController(Root1); // Create the Graphical User Interface

		Scene theScene = new Scene(Root1, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene

		theStage.setScene(theScene);
		// Set the scene on the stage

		theStage.show();

		// When the stage is shown to the user, the pane within the window is visible.
		// This means
		// that the labels, fields, and buttons of the Graphical User Interface (GUI)
		// are visible
		// and it is now possible for the user to select input fields and enter values
		// into them,
		// click on buttons, and read the labels, the results, and the error messages.
		// Show the stage to the user
	}

	public static void main(String[] args) { // This method may not be required
		launch(args); // for all JavaFX applications using
	} // other IDEs.
}