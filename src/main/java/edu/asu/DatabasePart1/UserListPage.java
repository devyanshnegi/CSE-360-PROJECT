package edu.asu.DatabasePart1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class UserListPage {

    private static DatabaseHelper databaseHelper = new DatabaseHelper();
    private String selectedUsername = null;
    Label OTPLabel  = new Label("");

    public UserListPage(Pane rootPane, SceneController sceneController) {

        VBox mainLayout = new VBox(10); // Main layout to hold user list and buttons
        mainLayout.setStyle("-fx-padding: 20;");

        Pane userListContainer = new Pane();
        mainLayout.getChildren().add(userListContainer);

        try {
            databaseHelper.connectToDatabase();
            
            // Retrieve users excluding the first one
            List<String[]> users = databaseHelper.getAllUsers();

            if(users.size()==0) {
            	Label usernameLabel = new Label("No Users");
            }
            
            
            // ToggleGroup for exclusive selection of RadioButtons
            ToggleGroup toggleGroup = new ToggleGroup();

            double yOffset = 10; // Initial vertical offset for positioning

            // Add each user as an entry in the Pane
            for (String[] user : users) {
                RadioButton radioButton = new RadioButton();
                radioButton.setToggleGroup(toggleGroup);

                // Set action to track the selected username
                radioButton.setOnAction(e -> selectedUsername = user[0]);

                // Display user details in labels
                Label usernameLabel = new Label("Username: " + user[0]);
                Label preferredNameLabel = new Label("Preferred Name: " + user[1]);
                Label roleLabel = new Label("Role: " + user[2]);

                // Arrange radio button and labels in an HBox
                HBox userEntry = new HBox(10, radioButton, usernameLabel, preferredNameLabel, roleLabel);
                userEntry.setLayoutY(yOffset); // Position the entry vertically
                userEntry.setLayoutX(10); // Horizontal padding

                userListContainer.getChildren().add(userEntry);
                yOffset += 30; // Increment yOffset for the next user entry
            }

            // Add action buttons
            Button resetPasswordButton = new Button("Reset Password");
            resetPasswordButton.setOnAction(e -> handleResetPassword(sceneController));

            Button editRoleButton = new Button("Edit Role");
            editRoleButton.setOnAction(e -> handleEditRole(sceneController));

            Button deleteUserButton = new Button("Delete User");
            deleteUserButton.setOnAction(e -> handleDeleteUser(sceneController));
            
            Button backButton = new Button("Back");
	        backButton.setOnAction(e -> {
	            System.out.println("Back button clicked.");
	            
	            String role = (String) sceneController.getData("role");
            	if(role.equals("admin")) {
        			sceneController.switchTo("Admin");
        		}
        		else if(role.equals("student")) {            			
        			sceneController.switchTo("StudentHome");
        		}
        		else if(role.equals("instructor")) {
        			sceneController.switchTo("InstructorHome");
        		}
	        });

            // Arrange buttons in an HBox
            HBox buttonBox = new HBox(10, resetPasswordButton, editRoleButton, deleteUserButton, backButton);
            mainLayout.getChildren().add(buttonBox);
            
            mainLayout.getChildren().add(OTPLabel);
             

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseHelper.closeConnection();
        }

        // Add the main layout to the root pane
        rootPane.getChildren().add(mainLayout);
    }

    // Action handler methods
    private void handleResetPassword(SceneController sceneController) {
        if (selectedUsername != null) {
            // Logic for resetting the password for the selected user
            System.out.println("Resetting password for user: " + selectedUsername);// TODO
            try {
        		Random random = new Random();
                int randomNumber = 100000 + random.nextInt(900000);

                databaseHelper.connectToDatabase();
                
        		databaseHelper.storeResetPasswordOtp(randomNumber, selectedUsername);
        		databaseHelper.closeConnection();
        		System.out.println("OTP:" + randomNumber);
        		OTPLabel.setText("OTP:" + randomNumber);
        	} catch(SQLException err) {
        		err.printStackTrace();
        	} finally {
        		databaseHelper.closeConnection();
        	}
        } else {
            System.out.println("No user selected to reset password.");
        }
    }

    private void handleEditRole(SceneController sceneController) {
        if (selectedUsername != null) {
        	sceneController.setData("Modifying User", selectedUsername);
            sceneController.switchTo("EditRole");
        } else {
            System.out.println("No user selected to edit role.");
        }
    }

    private void handleDeleteUser(SceneController sceneController) {
        if (selectedUsername != null) {
        	sceneController.setData("Modifying User", selectedUsername);
        	sceneController.switchTo("Confirmation");
        } else {
            System.out.println("No user selected to delete.");
        }
    }
}
