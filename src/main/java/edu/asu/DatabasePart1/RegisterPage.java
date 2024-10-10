
package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * <p>
 * UserInterface Class
 * </p>
 * 
 * <p>
 * Description: The Java/FX-based user interface testbed to develop and test UI
 * ideas.
 * </p>
 * 
 * <p>
 * Copyright: Lynn Robert Carter © 2024
 * </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00 2022-02-21 The JavaFX-based GUI for the implementation of a
 *          testbed
 * @version 2.00 2024-09-22 Updated for use at ASU
 * 
 */

public class RegisterPage {

	/**********************************************************************************************
	 * 
	 * Attributes
	 * 
	 **********************************************************************************************/

	// These are the application values required by the Graphical User Interface
	// The names of the variables specify their function and each is initialize as
	// required
	private Label label_ApplicationTitle = new Label("Register");
	private Label label_Username = new Label("Enter the username here");
	private Label label_Password = new Label("Enter the password here");
	private Label label_Password_Confirm = new Label("Re-enter the password here");
	private Label label_Username_Validity = new Label("");
	private Label label_Password_Validity = new Label("");
	private Label label_Password_Confirm_Validity = new Label("");
	
	private TextField text_Username = new TextField();
	private TextField text_Password = new TextField();
	private TextField text_Password_Confirm = new TextField();
	
	private Button button_Submit = new Button("Submit");
	
	private PasswordEvaluator PwdEval = new PasswordEvaluator();
	private UserNameRecognizer UserNameEval = new UserNameRecognizer();


	/**********************************************************************************************
	 * 
	 * Constructors
	 * 
	 **********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface.
	 * These assignments determine the location, size, font, color, and change and
	 * event handlers for each GUI object.
	 */
	public RegisterPage(Pane theRoot) {

		// Label theScene with the name of the test bed, centered at the top of the pane
		setupLabelUI(label_ApplicationTitle, "Arial", 24, PasswordEvaluationGUITestbed.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		setupLabelUI(label_Username, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10,
				50);
		
		setupTextUI(text_Username, "Arial", 18, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10,
				70, true);

		setupLabelUI(label_Username_Validity, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10,
				115);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(label_Password, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10,
				150);
		
		setupTextUI(text_Password, "Arial", 18, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10,
				170, true);
		
		setupLabelUI(label_Password_Validity, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10,
				215);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(label_Password_Confirm, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10,
				Pos.BASELINE_LEFT, 10, 250);

		setupTextUI(text_Password_Confirm, "Arial", 18, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 20,
				Pos.BASELINE_LEFT, 10, 270, true);
		
		setupLabelUI(label_Password_Confirm_Validity, "Arial", 14, PasswordEvaluationGUITestbed.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10,
				315);

		text_Username.textProperty().addListener((observable, oldValue, newValue) -> {
			setUsername();
		});
		text_Password.textProperty().addListener((observable, oldValue, newValue) -> {
			setPassword();
		});
		
		text_Password_Confirm.textProperty().addListener((observable, oldValue, newValue) -> {
			setPasswordConfirm();
		});
		
		setupButtonUI(button_Submit, "Arial",14 ,100, Pos.CENTER, PasswordEvaluationGUITestbed.WINDOW_WIDTH/2-50, 400);
		

		// Place all of the just-initialized GUI elements into the pane, whether they
		// have text or not
		theRoot.getChildren().addAll(label_ApplicationTitle, label_Username, label_Password, label_Password_Validity, label_Username_Validity,
				label_Password_Confirm, label_Password_Confirm_Validity, text_Username, text_Password, text_Password_Confirm, button_Submit);
	}

	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
	}
	
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y) {
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setMaxWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}

	/**********************************************************************************************
	 * 
	 * User Interface Actions
	 * 
	 **********************************************************************************************/

	/**********
	 * Reset all the relevant flags and error messages whenever the user changes the
	 * input
	 */
	private void setUsername() {
		usernamePerformEvaluation(); // Perform the evaluation to set all the assessment flags
	}
	
	private void setPassword() {
		passwordPerformEvaluation(); // Perform the evaluation to set all the assessment flags
	}
	
	private void setPasswordConfirm() {
		if(	text_Password_Confirm.getText().equals(text_Password.getText())) {
			label_Password_Confirm_Validity.setTextFill(Color.GREEN);
			label_Password_Confirm_Validity.setText("The passwords match!");
		}
		else {
			label_Password_Confirm_Validity.setTextFill(Color.RED);
			label_Password_Confirm_Validity.setText("The passwords do not match!");
		}
	}
	

	/**********
	 * Evaluate the input whenever the user changes it and update the GUI and the
	 * console so the user knows what is going on
	 */
	private void passwordPerformEvaluation() {
		// Get the user input string from the GUI
		String inputText = text_Password.getText();

		// If the input is empty, set that flag and stop
		if (inputText.isEmpty()) {
			label_Password_Validity.setTextFill(Color.RED);
			label_Password_Validity.setText("No input text found!");
		}
		else {
			// There is input to process. Call the evaluatePassword method to assess each of the remaining criteria
			String errMessage = PwdEval.evaluatePassword(inputText);
			
			if (errMessage != "") {
				updateFlags(); 
			}
			// If no error message was found, check to see if all the criteria has been met
			else if (PwdEval.foundUpperCase && PwdEval.foundLowerCase
					&& PwdEval.foundNumericDigit && PwdEval.foundSpecialChar
					&& PwdEval.foundLongEnough) {
				// All the criteria has been met. display the success message to the console
				System.out.println("Success! The password satisfies the requirements.");

				// Display the success message and make it green on the GUI
				label_Password_Validity.setTextFill(Color.GREEN);
				label_Password_Validity.setText("Success! The password satisfies the requirements.");
			} else {
				// At least one criterion has not been satisfied. Display an appropriate message
				// in red on the console
				label_Password_Validity.setTextFill(Color.RED);
				label_Password_Validity.setText("The password as currently entered is not yet valid.");	
			}
		}
	}
	
	/**********
	 * Evaluate the input whenever the user changes it and update the GUI and the
	 * console so the user knows what is going on
	 */
	private void usernamePerformEvaluation() {
		// Get the user input string from the GUI
		String inputText = text_Username.getText();

		// If the input is empty, set that flag and stop
		if (inputText.isEmpty()) {
			label_Username_Validity.setTextFill(Color.RED);
			label_Username_Validity.setText("No input text found!");
		}
		else {
			// There is input to process. Call the evaluatePassword method to assess each of the remaining criteria
			String errMessage = UserNameEval.checkForValidUserName(inputText);

			System.out.println(errMessage);
			label_Username_Validity.setTextFill(Color.RED);
			label_Username_Validity.setText(errMessage); 
			if (errMessage != "") {
				label_Username_Validity.setTextFill(Color.RED);
				label_Username_Validity.setText(errMessage); 
			}
			// If no error message was found, check to see if all the criteria has been met
			else {
				// All the criteria has been met. display the success message to the console
				System.out.println("Success! The username satisfies the requirements.");

				// Display the success message and make it green on the GUI
				label_Username_Validity.setTextFill(Color.GREEN);
				label_Username_Validity.setText("Success! The username satisfies the requirements.");
			}
		}
	}

	/**********
	 * Check each criterion. If not satisfied, update the text and turn it red
	 */
	
	public Button getButton1() {
		return button_Submit;
	}
	private void updateFlags() {
		// Upper case character
		if (!PwdEval.foundUpperCase) {
			label_Password_Validity.setTextFill(Color.RED);
			label_Password_Validity.setText("At least one upper case letter");
		}

		// Lower case character
		else if (!PwdEval.foundLowerCase) {
			label_Password_Validity.setTextFill(Color.RED);
			label_Password_Validity.setText("At least one lower case letter");
		}

		// Numeric character
		else if (!PwdEval.foundNumericDigit) {
			label_Password_Validity.setTextFill(Color.RED);
			label_Password_Validity.setText("At least one numeric digit");
		}

		// Special character
		else if (!PwdEval.foundSpecialChar) {
			label_Password_Validity.setTextFill(Color.RED);
			label_Password_Validity.setText("At least one special character");
		}

		// Long enough
		else if (!PwdEval.foundLongEnough) {
			label_Password_Validity.setTextFill(Color.RED);
			label_Password_Validity.setText("At least eight characters");
		}
	}
}
