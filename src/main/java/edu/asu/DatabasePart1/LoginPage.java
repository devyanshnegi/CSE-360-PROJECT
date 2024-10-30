package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;

/*******
 * <p> LoginPage Class </p>
 * 
 * <p> Description: LoginPage provides the user interface for the login screen. It includes 
 *                  fields for entering a username and password, and a login button that 
 *                  validates user input and redirects to the appropriate page based on whether 
 *                  the login follows a registration. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the LoginPage class for handling 
 *                  user authentication and navigation in the multi-page application.
 */

public class LoginPage {

    /** Label for the username field */
    private Label usernameLabel = new Label("Username:");
    
    /** TextField for entering the username */
    private TextField usernameField = new TextField();

    /** Label for the password field */
    private Label passwordLabel = new Label("Password:");
    
    /** PasswordField for entering the password */
    private PasswordField passwordField = new PasswordField();

    /** Button for logging in */
    private Button loginButton = new Button("Login");
    
    /** Label for displaying messages (e.g., error messages) */
    private Label messageLabel = new Label();
    
    private static DatabaseHelper databaseHelper = new DatabaseHelper();


    /**********
     * Constructor for LoginPage
     * 
     * <p> Sets up the user interface for the login page, including input fields for username 
     *     and password, a login button, and a message label for feedback. Depending on the 
     *     isAfterRegister flag, it redirects the user to either the CompleteProfilePage or 
     *     AdminPage upon successful login. </p>
     * 
     * @param root The root Pane where the UI components will be added.
     * @param theStage The primary Stage for switching scenes.
     * @param adminScene The scene to redirect to for the AdminPage.
     * @param completeProfileScene The scene to redirect to for the CompleteProfilePage.
     * @param isAfterRegister Flag indicating if the login occurs after registration.
     */
    public LoginPage(Pane root, SceneController sceneController, boolean isAfterRegister) {
        // Set up the UI components
        setupLabelUI(usernameLabel, "Arial", 14, 100, Pos.CENTER_LEFT, 50, 50);
        setupTextFieldUI(usernameField, "Arial", 14, 200, Pos.CENTER_LEFT, 160, 50);

        setupLabelUI(passwordLabel, "Arial", 14, 100, Pos.CENTER_LEFT, 50, 100);
        setupTextFieldUI(passwordField, "Arial", 14, 200, Pos.CENTER_LEFT, 160, 100);

        setupButtonUI(loginButton, "Arial", 14, 100, Pos.CENTER, 160, 150);
        setupLabelUI(messageLabel, "Arial", 14, 300, Pos.CENTER, 160, 200);

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> errorMessage(""));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> errorMessage(""));
        
        // Set the login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if(login(username,password)) {
            	if(isAfterRegister) {
            		sceneController.switchTo("CompleteProfile");
            	} else {
            		sceneController.switchTo("Home");
            	}
            }
            else {
                errorMessage("Invalid username or password.");
            }
        });

        // Add all components to the provided Pane
        root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, messageLabel);
    }
    
    public void errorMessage(String message) {
    	messageLabel.setStyle("-fx-text-fill: red;");
    	messageLabel.setText(message);
    }

    public boolean login(String username, String password) {
    	boolean valid = false;
    	try {
    		databaseHelper.connectToDatabase();
    		valid = databaseHelper.login(username, password);
    	}
    	catch (Exception e){
    		System.out.print(e);
    		valid = false;
    	}
    	finally {
    		databaseHelper.closeConnection();
    	}
    	return valid;
    }
    
    /**********
     * setupLabelUI method
     * 
     * <p> Configures a Label component's properties such as font, width, alignment, 
     *     and position on the screen. </p>
     * 
     * @param label The Label to configure.
     * @param fontFamily The font family for the Label.
     * @param fontSize The font size for the Label.
     * @param width The minimum width of the Label.
     * @param alignment The alignment of the Label text.
     * @param x The x-coordinate position of the Label.
     * @param y The y-coordinate position of the Label.
     */
    private void setupLabelUI(Label label, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        label.setFont(Font.font(fontFamily, fontSize));
        label.setMinWidth(width);
        label.setAlignment(alignment);
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    /**********
     * setupTextFieldUI method
     * 
     * <p> Configures a TextField component's properties such as font, width, alignment, 
     *     and position on the screen. </p>
     * 
     * @param textField The TextField to configure.
     * @param fontFamily The font family for the TextField.
     * @param fontSize The font size for the TextField.
     * @param width The minimum width of the TextField.
     * @param alignment The alignment of the TextField text.
     * @param x The x-coordinate position of the TextField.
     * @param y The y-coordinate position of the TextField.
     */
    private void setupTextFieldUI(TextField textField, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        textField.setFont(Font.font(fontFamily, fontSize));
        textField.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setAlignment(alignment);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
    }

    /**********
     * setupButtonUI method
     * 
     * <p> Configures a Button component's properties such as font, width, and position 
     *     on the screen. </p>
     * 
     * @param button The Button to configure.
     * @param fontFamily The font family for the Button.
     * @param fontSize The font size for the Button.
     * @param width The minimum width of the Button.
     * @param alignment The alignment of the Button text.
     * @param x The x-coordinate position of the Button.
     * @param y The y-coordinate position of the Button.
     */
    private void setupButtonUI(Button button, String fontFamily, double fontSize, double width, Pos alignment, double x, double y) {
        button.setFont(Font.font(fontFamily, fontSize));
        button.setMinWidth(width);
        button.setLayoutX(x);
        button.setLayoutY(y);
    }
}
