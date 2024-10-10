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
	public chooseInterface Gui;
	
	public chooseInterfaceController Gui2;
	
	public AdminPage Gui3;
	
	public LoginPage Gui4;
	
	
	public MainPage() {

	}

	public void start(Stage theStage) throws Exception {

		theStage.setTitle("Choose or Register"); // Label the stage (a window)

		Pane Root = new Pane(); // Create a pane within the window
		
		

		Gui = new chooseInterface(Root); // Create the Graphical User Interface

		Button buttonSwitch = Gui.InviteButton();

		
		Scene theScene = new Scene(Root, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene

		
		theStage.setScene(theScene);
		// Set the scene on the stage

		theStage.show();
		
		Thread.sleep(3000);
		
		Pane Root1 = new Pane();
		
		Gui2 = new chooseInterfaceController(Root1); // Create the Graphical User Interface
		
		Scene theScene1 = new Scene(Root1, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
		buttonSwitch.setOnAction(event ->{
			theStage.setScene(theScene1);
		});
		
		Pane Root2 = new Pane();
		
		
		Gui3 = new AdminPage(Root2);
		
		Scene theScene2 = new Scene(Root2, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
		Pane Root3 = new Pane();
		
		Gui4 = new LoginPage(Root3);
		
		Scene theScene3 = new Scene(Root3, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the scene
		
		//theStage.setScene(theScene3);
		 //Set the scene on the stage

    	//theStage.show();
	
    	
		
		
		
			
 
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
