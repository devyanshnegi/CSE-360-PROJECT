package edu.asu.DatabasePart1;

import java.sql.SQLException;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class InvitePage {
    
    private static DatabaseHelper databaseHelper = new DatabaseHelper();

    public InvitePage(Pane root, SceneController sceneController) {
        // Title Label
        Label rolesLabel = new Label("Roles");
        rolesLabel.setFont(new Font("Arial", 16));

        // Checkboxes for Role Selection
        CheckBox studentCheckBox = new CheckBox("Student");
        CheckBox instructorCheckBox = new CheckBox("Instructor");
        
        Label OTP = new Label();

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            boolean studentSelected = studentCheckBox.isSelected();
            boolean instructorSelected = instructorCheckBox.isSelected();
            
            
            if (studentSelected || instructorSelected) {
                try {
                    databaseHelper.connectToDatabase();
                    Random random = new Random();
                    int randomNumber = 100000 + random.nextInt(900000);
                    
                    if(studentSelected && instructorSelected) {
                        System.out.println("Selected role: instructor & student");
                        databaseHelper.storeOTP("instructor_student", randomNumber);
                    }
                    else if (studentSelected) {
                        System.out.println("Selected role: student");
                        databaseHelper.storeOTP("student", randomNumber);
                    }
                    else if (instructorSelected) {
                        System.out.println("Selected role: instructor");
                        databaseHelper.storeOTP("instructor", randomNumber);
                    }

                    // Display the OTP
                    OTP.setText("OTP: " + randomNumber);
                    
                } catch(SQLException err) {
                    err.printStackTrace();
                } finally {
                    databaseHelper.closeConnection();
                }
            } else {
                System.out.println("No roles selected.");
            }
        });

        // Back Button to navigate back
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            System.out.println("Back button clicked.");
            // Clear the OTP label
            OTP.setText("");
            
            // Navigate back to Admin Page
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
        gridPane.add(OTP, 0, 2);
        gridPane.add(submitButton, 0, 3);
        gridPane.add(backButton, 1, 3);

        root.getChildren().add(gridPane);
    }
}
