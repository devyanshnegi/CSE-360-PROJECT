package edu.asu.DatabasePart1;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ManageGeneralGroupPage {

    /** Label for the group name field */
    private Label nameLabel = new Label("Enter Special Access Group Name:");
    
    /** TextField for entering the group name */
    private TextField nameField = new TextField();

    /** Button for submitting the group */
    private Button addButton = new Button("Add");
    
    private Button removeButton = new Button("Remove");
    
    private Button backButton = new Button("Back");

    /** Label for showing error messages */
    private Label errorLabel = new Label();

    private static DatabaseHelper databaseHelper = new DatabaseHelper();

    /** Selected User's username */
    private String selectedUsername = null;

    public ManageGeneralGroupPage(Pane root, SceneController sceneController) {
    	
    	Button Load = new Button("Load");
        Load.setOnAction(e -> {
        	
        root.getChildren().remove(Load);
        
        // Set up the error label
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false); // Initially hidden

        // Set up the UI components
        setupLabelUI(nameLabel, "Arial", 14, 300, Pos.CENTER_LEFT, 50, 50);
        setupTextFieldUI(nameField, "Arial", 14, 300, Pos.CENTER_LEFT, 50, 80);
        setupButtonUI(addButton, "Arial", 14, 150, Pos.CENTER_LEFT, 50, 120);
        setupButtonUI(removeButton, "Arial", 14, 150, Pos.CENTER, 50, 120);
        setupButtonUI(backButton, "Arial", 14, 150, Pos.CENTER_RIGHT, 50, 120);

        VBox mainLayout = new VBox(20); // Main layout
        mainLayout.setStyle("-fx-padding: 20;");
        mainLayout.getChildren().addAll(nameLabel, nameField, addButton, removeButton, backButton, errorLabel);
        root.getChildren().add(mainLayout);

        Pane UserListContainer = new Pane();
        mainLayout.getChildren().add(UserListContainer);

        try {
            databaseHelper.connectToDatabase();

            // Retrieve all Student
            List<String[]> Student = databaseHelper.getAllStudents();
            
            int count =0;
            
            if (Student.isEmpty()) {
                Label noStudentLabel = new Label("No Student available.");
                UserListContainer.getChildren().add(noStudentLabel);
            } else {
                // ToggleGroup for exclusive selection of RadioButtons
                ToggleGroup toggleGroup = new ToggleGroup();

                VBox UserList = new VBox(10); // Vertical layout for Student

                // Add each User as an entry in the list
                String username = (String) sceneController.getData("username");
                for (String[] User : Student) {
                	if(User[0].equals(username))continue;
                    RadioButton radioButton = new RadioButton(User[0]); // User username
                    radioButton.setToggleGroup(toggleGroup);
                    radioButton.setOnAction(event -> selectedUsername = User[0]); // Set selected User

                    Label preferredNameLabel = new Label("Preferred Name: " + User[1]);
                    Label roleLabel = new Label("Role: " + User[2]);

                    VBox UserEntry = new VBox(radioButton, preferredNameLabel, roleLabel);
                    UserList.getChildren().add(UserEntry);
                    count++;
                }
                if(count == 0 ) {

                    Label noStudentLabel = new Label("No Students available.");
                    UserListContainer.getChildren().add(noStudentLabel);
                }
                else {
                    UserListContainer.getChildren().add(UserList);
                }
            }
        } catch (Exception err) {
            showErrorMessage("Error fetching Students: " + err.getMessage());
        } finally {
            databaseHelper.closeConnection();
        }

        addButton.setOnAction(event -> handleAdd(sceneController, nameField.getText().trim(), selectedUsername));
        removeButton.setOnAction(event -> handleRemove(sceneController, nameField.getText().trim(), selectedUsername));
        backButton.setOnAction(event -> {
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
        });
        root.getChildren().add(Load);
    }
    
    private void handleAdd(SceneController sceneController, String groupName, String selectedUsername) {
    	if (groupName.isEmpty()) {
            showErrorMessage("Please enter a valid group name.");
            return;
        }

        if (selectedUsername == null) {
            showErrorMessage("Please select a User.");
            return;
        }

        try {
            databaseHelper.connectToDatabase();

        	String username = (String) sceneController.getData("username");
        	        	        	
            if(databaseHelper.doesUserHaveAccess(username, groupName)){
            	boolean success = databaseHelper.setViewAccessGroupUser(selectedUsername, groupName);
                if (success) {
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
                	
                } else {
                    showErrorMessage("Failed to add User. Please try again.");
                }
        	}
            else {
                showErrorMessage("You do not have access to this group");
            }
        } catch (Exception e) {
            showErrorMessage("Error: " + e.getMessage());
        } finally {
            databaseHelper.closeConnection();
        }
    }
    
    private void handleRemove(SceneController sceneController, String groupName, String selectedUsername) {
    	if (groupName.isEmpty()) {
            showErrorMessage("Please enter a valid group name.");
            return;
        }

        if (selectedUsername == null) {
            showErrorMessage("Please select a User.");
            return;
        }

        try {
            databaseHelper.connectToDatabase();

        	String username = (String) sceneController.getData("username");
            if(databaseHelper.doesUserHaveAccess(username, groupName)){
            	boolean success = databaseHelper.removeViewAccessGroupUser(selectedUsername, groupName);
                if (success) {
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
                } else {
                    showErrorMessage("Failed to add User. Please try again.");
                }
        	}
            else {
                showErrorMessage("You do not have access to this group");
            }
        } catch (Exception e) {
            showErrorMessage("Error: " + e.getMessage());
        } finally {
            databaseHelper.closeConnection();
        }
    }

    /** Show error message */
    private void showErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    // Helper methods remain unchanged
    private void setupLabelUI(Label label, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        label.setFont(Font.font(fontFamily, fontSize));
        label.setMinWidth(width);
        label.setAlignment(alignment);
    }

    private void setupTextFieldUI(TextField textField, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        textField.setFont(Font.font(fontFamily, fontSize));
        textField.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setAlignment(alignment);
    }

    private void setupButtonUI(Button button, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        button.setFont(Font.font(fontFamily, fontSize));
        button.setMinWidth(width);
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
}
