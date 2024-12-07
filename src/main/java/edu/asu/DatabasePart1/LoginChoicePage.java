package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class LoginChoicePage {

    public LoginChoicePage(Pane root, SceneController sceneController) {
        // Create a VBox to hold the buttons
        VBox vbox = new VBox(20); // 20 pixels of vertical spacing
        vbox.setAlignment(Pos.CENTER);
        
        // Create Instructor Login button
        Button instructorButton = new Button("Login as Instructor");
        instructorButton.setFont(Font.font("Arial", 16));
        instructorButton.setOnAction(e -> {
            // Switch to your Instructor login scene
        	String role = "instructor";
    		sceneController.setData("role", role);
        	
    		boolean after_register = (boolean)sceneController.getData("after_register");
    		if(after_register) {
        		sceneController.switchTo("CompleteProfile");
    		}
    		else {		
    			sceneController.switchTo("InstructorHome");
    		}
        });

        // Create Student Login button
        Button studentButton = new Button("Login as Student");
        studentButton.setFont(Font.font("Arial", 16));
        studentButton.setOnAction(e -> {
            // Switch to your Student login scene
        	String role = "student";
    		sceneController.setData("role", role);
    		

    		boolean after_register = (boolean)sceneController.getData("after_register");
    		if(after_register) {
        		sceneController.switchTo("CompleteProfile");
    		}
    		else {		
    			sceneController.switchTo("StudentHome");
    		}
        });

        // Add both buttons to the VBox
        vbox.getChildren().addAll(instructorButton, studentButton);

        // Add the VBox to the root Pane
        root.getChildren().add(vbox);
    }
}
