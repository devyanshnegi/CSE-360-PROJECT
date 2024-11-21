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

public class MessagePage{
	
	// Input field for the student username
    private TextField Message;
    
 // Input field for the student username
    private TextField usernameField;
    
    /**
     * Constructor to create and display the Manage Student pop-up window.
     *
     * @param manageStudentPane       The pane to be used to hold the scene.
     * @param sceneController  The scene controller to manage scene transitions.
     */
    public MessagePage(Pane root, SceneController sceneController) {
    	Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Help Message");
        
     // Main layout as a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        
     // Page title
        Label titleLabel = new Label("Help Message");
        titleLabel.setFont(Font.font("Arial", 18));
        grid.add(titleLabel, 0, 0, 2, 1);
        
     // Message field
        Label messageLabel = new Label("Message for Instructor: ");
        Message = new TextField();
        Message.setPromptText("Enter Help Message");
        grid.add(messageLabel, 0, 1);
        grid.add(Message, 1, 1);
        
     // Username field
        Label usernameLabel = new Label("Student Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter student username");
        grid.add(usernameLabel, 0, 3);
        grid.add(usernameField, 1, 4);
        
     // Action buttons for Add and Remove
        Button SendButton = new Button("Send");
        SendButton.setOnAction(e -> handleAction("Send", popupStage, sceneController));
        grid.add(SendButton, 1, 2);
        
     // Back button
        Button back = new Button("Back");
        back.setOnAction(e -> sceneController.switchTo("StudentHome"));
        grid.add(back, 1, 6);
        
        root.getChildren().addAll(grid);

    }

	private void handleAction(String actionType, Stage popupStage, SceneController sceneController) {
		String message = Message.getText();
		String username = usernameField.getText();
		try {
			if(actionType.equalsIgnoreCase("Send")) {
				DatabaseHelper dbhelper = new DatabaseHelper();
				dbhelper.connectToDatabase();
				if(dbhelper.sendHelpMessage(message, username)) {
					System.out.println("Message sent to Instructor");
					
				}else {
					System.out.println("Could not send message");
				}
			}
			}catch(SQLException ex){
					ex.printStackTrace();
					
				}
		popupStage.close();
		
		return;
				
			}
		
		// TODO Auto-generated method stub
}
	