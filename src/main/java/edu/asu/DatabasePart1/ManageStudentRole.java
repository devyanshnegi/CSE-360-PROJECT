package edu.asu.DatabasePart1;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.text.Font;

/*******
 * <p> ManageStudentPopup Class </p>
 * 
 * <p> Description: The ManageStudentPopup class provides a graphical user interface 
 *                  for managing (adding or removing) students. It prompts the user 
 *                  to enter the username of the student to be added or removed. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-11-20 Initial implementation of the ManageStudentPopup class.
 */

public class ManageStudentRole {

    // Input field for the student username
    private TextField usernameField;

    /**
     * Constructor to create and display the Manage Student pop-up window.
     *
     * @param manageStudentPane       The pane to be used to hold the scene.
     * @param sceneController  The scene controller to manage scene transitions.
     */
    public ManageStudentRole(Pane root, SceneController sceneController) {
        // Initialize the stage for the popup window
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Manage Student");

        // Main layout as a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Page title
        Label titleLabel = new Label("Manage Student");
        titleLabel.setFont(Font.font("Arial", 18));
        grid.add(titleLabel, 0, 0, 2, 1);

        // Username field
        Label usernameLabel = new Label("Student Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter student username");
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        // Action buttons for Add and Remove
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> handleAction("Add", popupStage, sceneController));
        grid.add(addButton, 1, 2);

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> handleAction("Remove", popupStage, sceneController));
        grid.add(removeButton, 0, 2);

        // Cancel button
        Button back = new Button("Back");
        back.setOnAction(e -> sceneController.switchTo("InstructorHome"));
        grid.add(back, 0, 3);

        // Set up the scene and display the window
        //Scene popupScene = new Scene(grid, 400, 200);
//        popupStage.setScene(popupScene);
//        popupStage.showAndWait();
//        
        root.getChildren().addAll(grid);
    }

    /**
     * Handles the action (Add or Remove) for the specified student.
     *
     * @param actionType The action to perform ("Add" or "Remove").
     * @param popupStage The pop-up stage to close after the action is completed.
     * @param sceneController The scene controller to manage scene transitions.
     */
    private void handleAction(String actionType, Stage popupStage, SceneController sceneController) {
        String username = usernameField.getText();

        // Validate input
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Username cannot be empty.");
            return;
        }

        try {
            // Perform the add or remove action (dummy logic for now)
            if (actionType.equalsIgnoreCase("Add")) {
                // Add student logic
            	DatabaseHelper dbhelper = new DatabaseHelper();
            	try {
            		dbhelper.connectToDatabase();
            		if(dbhelper.addStudent(username)) {
            			System.out.println("Adding student: " + username);
            			
            		} else {
            			System.out.println("Could not add Student");
            		}
            	}catch(SQLException ex) {
            		ex.printStackTrace();
            
            	}
            	
                
            } else if (actionType.equalsIgnoreCase("Remove")) {
            	DatabaseHelper dbhelper = new DatabaseHelper();
            	dbhelper.connectToDatabase();
            	if(dbhelper.removeStudent(username)) {
            		System.out.println("Removing student: " + username);
            	}
                
            	// Remove student logic
            	else {
            		System.out.println("could not remove student");
            	}
            }

            // Show success alert
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student " + actionType.toLowerCase() + "ed successfully!");

            // Close the pop-up window
            popupStage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to " + actionType.toLowerCase() + " student.");
        }
    }

    /**
     * Displays an alert dialog with the specified type, title, and message.
     *
     * @param alertType The type of alert.
     * @param title     The alert title.
     * @param message   The alert message.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
