package edu.asu.DatabasePart1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InvitePage {
	public InvitePage(Pane root, SceneController sceneController) {
        // Title Label
        Label rolesLabel = new Label("Roles");
        rolesLabel.setFont(new Font("Arial", 16));

        // Radio Buttons for Role Selection
        ToggleGroup roleGroup = new ToggleGroup();
        RadioButton studentRadio = new RadioButton("Student");
        studentRadio.setToggleGroup(roleGroup);
        RadioButton instructorRadio = new RadioButton("Instructor");
        instructorRadio.setToggleGroup(roleGroup);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
        	RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();
            if (selectedRole != null) {
                System.out.println("Selected role: " + selectedRole.getText());
                // Navigate to Admin Page on submit
                sceneController.switchTo("Admin");
            } else {
                System.out.println("No role selected.");
            }
        });

        // Back Button to navigate back
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            System.out.println("Back button clicked.");
            // Navigate back to Admin Page on back
            sceneController.switchTo("Admin");
        });

        // Layout using GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.add(rolesLabel, 0, 0);
        gridPane.add(studentRadio, 0, 1);
        gridPane.add(instructorRadio, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(backButton, 1, 2);

        root.getChildren().add(gridPane);
    }
	
}
