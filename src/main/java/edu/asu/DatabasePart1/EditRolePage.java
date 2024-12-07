package edu.asu.DatabasePart1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class EditRolePage {
    
    private static DatabaseHelper databaseHelper = new DatabaseHelper();

    public EditRolePage(Pane root, SceneController sceneController) {
        // Title Label
        Label rolesLabel = new Label("Roles");
        rolesLabel.setFont(new Font("Arial", 16));

        // CheckBoxes for Role Selection
        CheckBox studentCheckBox = new CheckBox("Student");
        CheckBox instructorCheckBox = new CheckBox("Instructor");

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String selectedUsername = (String) sceneController.getData("Modifying User");
            
            // Collect selected roles
            List<String> selectedRoles = new ArrayList<>();
            if (studentCheckBox.isSelected()) {
                selectedRoles.add("student");
            }
            if (instructorCheckBox.isSelected()) {
                selectedRoles.add("instructor");
            }

            if (selectedRoles.isEmpty()) {
                System.out.println("No role selected.");
            } else {
                // If multiple roles are selected, choose the first one
                String chosenRole = selectedRoles.get(0);
                System.out.println("Editing role for user: " + selectedUsername + " to " + chosenRole);
                try {
                    databaseHelper.connectToDatabase();
                    databaseHelper.editRole(selectedUsername, chosenRole);
                } catch (SQLException err) {
                    err.printStackTrace();
                } finally {
                    databaseHelper.closeConnection();
                }
                sceneController.switchTo("Admin");
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
        gridPane.add(studentCheckBox, 0, 1);
        gridPane.add(instructorCheckBox, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(backButton, 1, 2);

        root.getChildren().add(gridPane);
    }
}
