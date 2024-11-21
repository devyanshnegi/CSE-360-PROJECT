package edu.asu.DatabasePart1;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

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

public class ViewMessages{
	
	private Button showButton;
	

	public ViewMessages(Pane root, SceneController sceneController) {
    	Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Help Messages from Students");
        
     // Main layout as a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
     // Page title
        Label titleLabel = new Label("Help Messages from Students");
        titleLabel.setFont(Font.font("Arial", 18));
        grid.add(titleLabel, 0, 0, 2, 1);
        
     //Setup Button
        Button showButton = new Button("Show Messages");
        showButton.setOnAction(e -> handleAction("Show Messages", popupStage, sceneController));
        grid.add(showButton, 1, 6);
        
     // Back button
        Button back = new Button("Back");
        back.setOnAction(e -> sceneController.switchTo("InstructorHome"));
        grid.add(back, 1, 9);
        
        
        root.getChildren().addAll(grid);
        
	}

	private Object handleAction(String actionType, Stage popupStage, SceneController sceneController) {
		// TODO Auto-generated method stub
		
		try {
			if(actionType.equalsIgnoreCase("Show Messages")) {
				DatabaseHelper dbhelper = new DatabaseHelper();
				dbhelper.connectToDatabase();
				
				List<String> messages = dbhelper.getAllMessages();

	            // Display messages in the popup
	            StringBuilder messageContent = new StringBuilder();
	            for (String message : messages) {
	                messageContent.append(message).append("\n\n");
	                
	             
	                
			}
	            
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Messages");
	            alert.setHeaderText("Messages from Students");
	            alert.setContentText(messageContent.toString().isEmpty() ? "No messages found." : messageContent.toString());
	            alert.showAndWait();
	            
	            
		}
			}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
    
}