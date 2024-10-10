package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class LoginPage {

    private Label usernameLabel = new Label("Username:");
    private TextField usernameField = new TextField();

    private Label passwordLabel = new Label("Password:");
    private PasswordField passwordField = new PasswordField();

    private Button loginButton = new Button("Login");
    private Label messageLabel = new Label();

    public LoginPage(Pane Root3) {
        // Set up the UI components
        setupLabelUI(usernameLabel, "Arial", 14, 100, Pos.CENTER_LEFT, 50, 50);
        setupTextFieldUI(usernameField, "Arial", 14, 200, Pos.CENTER_LEFT, 160, 50);

        setupLabelUI(passwordLabel, "Arial", 14, 100, Pos.CENTER_LEFT, 50, 100);
        setupTextFieldUI(passwordField, "Arial", 14, 200, Pos.CENTER_LEFT, 160, 100);

        setupButtonUI(loginButton, "Arial", 14, 100, Pos.CENTER, 160, 150);
        setupLabelUI(messageLabel, "Arial", 14, 300, Pos.CENTER, 160, 200);
        
        // Set the login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!username.isEmpty() && !password.isEmpty()) {
                // If login is successful, show success message
                System.out.println("Login successful!");
                messageLabel.setText("Login successful!");
                messageLabel.setStyle("-fx-text-fill: green;");
            } else {
                // Show an error message
                messageLabel.setText("Please enter both username and password.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Add all components to the provided Pane
        Root3.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, messageLabel);
    }

    // Utility method to set up Label UI properties
    private void setupLabelUI(Label label, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        label.setFont(Font.font(fontFamily, fontSize));
        label.setMinWidth(width);
        label.setAlignment(alignment);
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    // Utility method to set up TextField UI properties
    private void setupTextFieldUI(TextField textField, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        textField.setFont(Font.font(fontFamily, fontSize));
        textField.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setAlignment(alignment);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
    }

    // Utility method to set up Button UI properties
    private void setupButtonUI(Button button, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        button.setFont(Font.font(fontFamily, fontSize));
        button.setMinWidth(width);
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
}
