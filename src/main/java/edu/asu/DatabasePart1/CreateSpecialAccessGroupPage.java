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

public class CreateSpecialAccessGroupPage {

    /** Label for the group name field */
    private Label nameLabel = new Label("Enter Special Access Group Name:");
    
    /** TextField for entering the group name */
    private TextField nameField = new TextField();

    /** Button for submitting the group */
    private Button submitButton = new Button("Create Group");

    /** Label for showing error messages */
    private Label errorLabel = new Label();

    private static DatabaseHelper databaseHelper = new DatabaseHelper();

    /** Selected instructor's username */
    private String selectedUsername = null;

    public CreateSpecialAccessGroupPage(Pane root, SceneController sceneController) {
        // Set up the error label
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false); // Initially hidden

        // Set up the UI components
        setupLabelUI(nameLabel, "Arial", 14, 300, Pos.CENTER_LEFT, 50, 50);
        setupTextFieldUI(nameField, "Arial", 14, 300, Pos.CENTER_LEFT, 50, 80);
        setupButtonUI(submitButton, "Arial", 14, 150, Pos.CENTER, 50, 120);

        VBox mainLayout = new VBox(20); // Main layout
        mainLayout.setStyle("-fx-padding: 20;");
        mainLayout.getChildren().addAll(nameLabel, nameField, submitButton, errorLabel);
        root.getChildren().add(mainLayout);

        Pane instructorListContainer = new Pane();
        mainLayout.getChildren().add(instructorListContainer);

        try {
            databaseHelper.connectToDatabase();

            // Retrieve all instructors
            List<String[]> instructors = databaseHelper.getAllInstructors();

            if (instructors.isEmpty()) {
                Label noInstructorsLabel = new Label("No instructors available.");
                instructorListContainer.getChildren().add(noInstructorsLabel);
            } else {
                // ToggleGroup for exclusive selection of RadioButtons
                ToggleGroup toggleGroup = new ToggleGroup();

                VBox instructorList = new VBox(10); // Vertical layout for instructors

                // Add each instructor as an entry in the list
                for (String[] instructor : instructors) {
                    RadioButton radioButton = new RadioButton(instructor[0]); // Instructor username
                    radioButton.setToggleGroup(toggleGroup);
                    radioButton.setOnAction(e -> selectedUsername = instructor[0]); // Set selected instructor

                    Label preferredNameLabel = new Label("Preferred Name: " + instructor[1]);
                    Label roleLabel = new Label("Role: " + instructor[2]);

                    VBox instructorEntry = new VBox(radioButton, preferredNameLabel, roleLabel);
                    instructorList.getChildren().add(instructorEntry);
                }

                instructorListContainer.getChildren().add(instructorList);
            }
        } catch (Exception e) {
            showErrorMessage("Error fetching instructors: " + e.getMessage());
        } finally {
            databaseHelper.closeConnection();
        }

        // Set the submit button action
        submitButton.setOnAction(event -> {
            String groupName = nameField.getText().trim();

            if (groupName.isEmpty()) {
                showErrorMessage("Please enter a valid group name.");
                return;
            }

            if (selectedUsername == null) {
                showErrorMessage("Please select an instructor.");
                return;
            }

            try {
                databaseHelper.connectToDatabase();
                boolean success = databaseHelper.setSpecialAccessGroupUser(selectedUsername, nameField.getText());
                if (success) {
                    sceneController.switchTo("Admin");
                } else {
                    showErrorMessage("Failed to create the group. Please try again.");
                }
            } catch (Exception e) {
                showErrorMessage("Error: " + e.getMessage());
            } finally {
                databaseHelper.closeConnection();
            }
        });
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
    }
}
