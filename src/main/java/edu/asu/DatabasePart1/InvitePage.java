package edu.asu.DatabasePart1;
import java.util.Random;


	import java.sql.SQLException;

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
		
	    private static DatabaseHelper databaseHelper = new DatabaseHelper();

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
	        Label OTP = new Label();
	        // Submit Button
	        Button submitButton = new Button("Submit");
	        submitButton.setOnAction(e -> {
	        	RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();
	            if (selectedRole != null) {
	                System.out.println("Selected role: " + selectedRole.getText());
	                // Navigate to Admin Page on submit
	                try {
	            		Random random = new Random();
	                    int randomNumber = 100000 + random.nextInt(900000);

	                    databaseHelper.connectToDatabase();
	            		databaseHelper.storeOTP(selectedRole.getText().toLowerCase(), randomNumber);
	            		databaseHelper.closeConnection();
	            		
	        	        OTP.setText(Integer.toString(randomNumber)); 
	            	} catch(SQLException err) {
	            		err.printStackTrace();
	            	}
	            	finally {
	            		databaseHelper.closeConnection();
	            	}
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
	        gridPane.add(OTP, 0, 2);
	        gridPane.add(submitButton, 0, 3);
	        gridPane.add(backButton, 1, 3);

	        root.getChildren().add(gridPane);
	    }
		
	}

