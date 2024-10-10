package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/***
 * <p> LoginPage Class </p>
 * 
 * <p> Description: A JavaFX class representing the login interface for users to 
 * enter their credentials (username and password) to access the system. </p>
 * 
 * <p> Copyright: Your Name © 2024 </p>
 * 
 * @author Your Name
 * 
 * @version 1.00	2024-10-09 Initial creation of the LoginPage class for user authentication
 * 
 */

public class LoginPage {

    /** Label for the username input */
    private Label usernameLabel = new Label("Username:");
    /** TextField for user to input their username */
    private TextField usernameField = new TextField();

    /** Label for the password input */
    private Label passwordLabel = new Label("Password:");
    /** PasswordField for user to input their password securely */
    private PasswordField passwordField = new PasswordField();

    /** Button for the user to submit their login request */
    private Button loginButton = new Button("Login");
    /** Label for displaying messages to the user */
    private Label messageLabel = new Label();

    /** 
     * The constructor for the LoginPage class.
     * Initializes the user interface elements and adds them to the given pane.
     * 
     * @param Root3 The pane where all the UI elements will be added
     */
    public LoginPage(Pane Root3) {
        // Set up the UI components
        setupLabelUI(usernameLabel, "Arial", 14, 100, Pos.CENTER_LEFT, 50, 50); // Username label UI setup
        setupTextFieldUI(usernameField, "Arial", 14, 200, Pos.CENTER_LEFT, 160, 50); // Username field UI setup

        setupLabelUI(passwordLabel, "Arial", 14, 100, Pos.CENTER_LEFT, 50, 100); // Password label UI setup
        setupTextFieldUI(passwordField, "Arial", 14, 200, Pos.CENTER_LEFT, 160, 100); // Password field UI setup

        setupButtonUI(loginButton, "Arial", 14, 100, Pos.CENTER, 160, 150); // Login button UI setup
        setupLabelUI(messageLabel, "Arial", 14, 300, Pos.CENTER, 160, 200); // Message label UI setup
        
        // Set the login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText(); // Get the entered username
            String password = passwordField.getText(); // Get the entered password

            if (!username.isEmpty() && !password.isEmpty()) { // Check if both fields are filled
                // If login is successful, show success message
                System.out.println("Login successful!"); // Print success to console
                messageLabel.setText("Login successful!"); // Update message label
                messageLabel.setStyle("-fx-text-fill: green;"); // Set message label color to green
            } else {
                // Show an error message if fields are empty
                messageLabel.setText("Please enter both username and password."); // Update message label
                messageLabel.setStyle("-fx-text-fill: red;"); // Set message label color to red
            }
        });

        // Add all components to the provided Pane
        Root3.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, messageLabel); // Add UI components to the root pane
    }

    /****
     * This utility method initializes the standard fields for a Label UI.
     * 
     * @param label The label to be set up
     * @param fontFamily The font family for the label
     * @param fontSize The font size for the label
     * @param width The width of the label
     * @param alignment The alignment position of the label
     * @param x The X coordinate for the label's position
     * @param y The Y coordinate for the label's position
     */
    private void setupLabelUI(Label label, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        label.setFont(Font.font(fontFamily, fontSize)); // Set the font for the label
        label.setMinWidth(width); // Set the minimum width for the label
        label.setAlignment(alignment); // Set the alignment of the label
        label.setLayoutX(x); // Set the X position of the label
        label.setLayoutY(y); // Set the Y position of the label
    }

    /****
     * This utility method initializes the standard fields for a TextField UI.
     * 
     * @param textField The text field to be set up
     * @param fontFamily The font family for the text field
     * @param fontSize The font size for the text field
     * @param width The width of the text field
     * @param alignment The alignment position of the text field
     * @param x The X coordinate for the text field's position
     * @param y The Y coordinate for the text field's position
     */
    private void setupTextFieldUI(TextField textField, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        textField.setFont(Font.font(fontFamily, fontSize)); // Set the font for the text field
        textField.setMinWidth(width); // Set the minimum width for the text field
        textField.setMaxWidth(width); // Set the maximum width for the text field
        textField.setAlignment(alignment); // Set the alignment of the text field
        textField.setLayoutX(x); // Set the X position of the text field
        textField.setLayoutY(y); // Set the Y position of the text field
    }

    /****
     * This utility method initializes the standard fields for a Button UI.
     * 
     * @param button The button to be set up
     * @param fontFamily The font family for the button
     * @param fontSize The font size for the button
     * @param width The width of the button
     * @param alignment The alignment position of the button
     * @param x The X coordinate for the button's position
     * @param y The Y coordinate for the button's position
     */
    private void setupButtonUI(Button button, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        button.setFont(Font.font(fontFamily, fontSize)); // Set the font for the button
        button.setMinWidth(width); // Set the minimum width for the button
        button.setLayoutX(x); // Set the X position of the button
        button.setLayoutY(y); // Set the Y position of the button
    }
}