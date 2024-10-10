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
    public final static double WINDOW_HEIGHT = 430;

    // A reference to the CompleteProfilePage and HomePage
    public CompleteProfilePage Gui;
    public HomePage HomeGui;

    public ControllerPage() {
    }

    @Override
    public void start(Stage theStage) throws Exception {

        theStage.setTitle("Complete Set Up Page"); // Label the stage (a window)

        // Set up the HomePage
        Pane homePane = new Pane();
        HomeGui = new HomePage(homePane);  // Create the HomePage UI
        Scene homeScene = new Scene(homePane, WINDOW_WIDTH, WINDOW_HEIGHT);  // Create the HomePage scene

        // Set up the CompleteProfilePage and pass the homeScene to allow redirection
        Pane profilePane = new Pane(); // Create a pane within the window for CompleteProfilePage
        Gui = new CompleteProfilePage(profilePane, theStage, homeScene); // Create the CompleteProfilePage and pass the HomePage scene
        Scene profileScene = new Scene(profilePane, WINDOW_WIDTH, WINDOW_HEIGHT); // Create the CompleteProfilePage scene

        theStage.setScene(profileScene); // Set the CompleteProfilePage scene on the stage

        theStage.show(); // Show the stage to the user
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}
