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
import javafx.stage.Stage;
import javafx.scene.Scene;

/*******
 * <p> RegisterPage Class </p>
 * 
 * <p> Description: RegisterPage provides the graphical user interface for the registration screen. 
 *                  It allows users to enter their username and password, confirming the password, 
 *                  and provides feedback on the validity of the input. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the RegisterPage class for handling user 
 *                  registration and validation of input.
 */

public class RegisterPage {

    /**********************************************************************************************
     * 
     * Attributes
     * 
     **********************************************************************************************/

    /** Label for the application title */
    private Label label_ApplicationTitle = new Label("Register");
    
    /** Label prompting the user to enter the username */
    private Label label_Username = new Label("Enter the username here");
    
    /** Label prompting the user to enter the password */
    private Label label_Password = new Label("Enter the password here");
    
    /** Label prompting the user to re-enter the password */
    private Label label_Password_Confirm = new Label("Re-enter the password here");
    
    /** Label for displaying the username validity message */
    private Label label_Username_Validity = new Label("");
    
    /** Label for displaying the password validity message */
    private Label label_Password_Validity = new Label("");
    
    /** Label for displaying the password confirmation validity message */
    private Label label_Password_Confirm_Validity = new Label("");

    /** TextField for entering the username */
    private TextField text_Username = new TextField();
    
    /** TextField for entering the password */
    private TextField text_Password = new TextField();
    
    /** TextField for re-entering the password */
    private TextField text_Password_Confirm = new TextField();

    /** Button to submit the registration form */
    private Button button_Submit = new Button("Submit");

    /** PasswordEvaluator for evaluating password criteria */
    private PasswordEvaluator PwdEval = new PasswordEvaluator();
    
    /** UserNameRecognizer for checking the validity of the username */
    private UserNameRecognizer UserNameEval = new UserNameRecognizer();

    /** The width of the window for the registration page */
    private int WINDOW_WIDTH = 700;

    /**********************************************************************************************
     * 
     * Constructors
     * 
     **********************************************************************************************/

    /**********
     * Constructor for RegisterPage
     * 
     * <p> Sets up the user interface for the registration page, including input fields for username 
     *     and password, validation messages, and a submit button. The constructor also adds 
     *     listeners for input changes and an action to the submit button to switch to the login 
     *     page. </p>
     * 
     * @param theRoot The root Pane where the UI components will be added.
     * @param theStage The primary Stage for switching scenes.
     * @param loginScene The scene to redirect to after successful registration.
     */
    public RegisterPage(Pane theRoot, SceneController sceneController) {

        // Label the scene with the name of the test bed, centered at the top of the pane
        setupLabelUI(label_ApplicationTitle, "Arial", 24, WINDOW_WIDTH, Pos.CENTER, 0, 10);

        setupLabelUI(label_Username, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 50);
        setupTextUI(text_Username, "Arial", 18, WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 70, true);
        setupLabelUI(label_Username_Validity, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 115);

        setupLabelUI(label_Password, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 150);
        setupTextUI(text_Password, "Arial", 18, WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 170, true);
        setupLabelUI(label_Password_Validity, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 215);

        setupLabelUI(label_Password_Confirm, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 250);
        setupTextUI(text_Password_Confirm, "Arial", 18, WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 270, true);
        setupLabelUI(label_Password_Confirm_Validity, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 315);

        // Listeners for input fields to perform validation
        text_Username.textProperty().addListener((observable, oldValue, newValue) -> setUsername());
        text_Password.textProperty().addListener((observable, oldValue, newValue) -> setPassword());
        text_Password_Confirm.textProperty().addListener((observable, oldValue, newValue) -> setPasswordConfirm());

        setupButtonUI(button_Submit, "Arial", 14, 100, Pos.CENTER, WINDOW_WIDTH / 2 - 50, 400);

        // Submit button action to switch to the login page upon successful registration
        button_Submit.setOnAction(event -> {
            System.out.println("Registration complete. Redirecting to Login Page...");
            sceneController.switchTo("LoginToComplete");
        });

        // Place all of the just-initialized GUI elements into the pane
        theRoot.getChildren().addAll(label_ApplicationTitle, label_Username, label_Password, label_Password_Validity,
                label_Username_Validity, label_Password_Confirm, label_Password_Confirm_Validity, text_Username,
                text_Password, text_Password_Confirm, button_Submit);
    }

    /**********
     * setupLabelUI method
     * 
     * <p> Configures a Label component's properties such as font, width, alignment, 
     *     and position on the screen. </p>
     * 
     * @param l The Label to configure.
     * @param ff The font family for the Label.
     * @param f The font size for the Label.
     * @param w The minimum width of the Label.
     * @param p The alignment of the Label text.
     * @param x The x-coordinate position of the Label.
     * @param y The y-coordinate position of the Label.
     */
    private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
        l.setFont(Font.font(ff, f));
        l.setMinWidth(w);
        l.setAlignment(p);
        l.setLayoutX(x);
        l.setLayoutY(y);
    }

    /**********
     * setupTextUI method
     * 
     * <p> Configures a TextField component's properties such as font, width, alignment, 
     *     editability, and position on the screen. </p>
     * 
     * @param t The TextField to configure.
     * @param ff The font family for the TextField.
     * @param f The font size for the TextField.
     * @param w The minimum width of the TextField.
     * @param p The alignment of the TextField text.
     * @param x The x-coordinate position of the TextField.
     * @param y The y-coordinate position of the TextField.
     * @param e Specifies if the TextField is editable.
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

    /**********
     * setupButtonUI method
     * 
     * <p> Configures a Button component's properties such as font, width, and position 
     *     on the screen. </p>
     * 
     * @param b The Button to configure.
     * @param ff The font family for the Button.
     * @param f The font size for the Button.
     * @param w The minimum width of the Button.
     * @param p The alignment of the Button text.
     * @param x The x-coordinate position of the Button.
     * @param y The y-coordinate position of the Button.
     */
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
     * setUsername method
     * 
     * <p> Evaluates the username input whenever the user changes it. </p>
     */
    private void setUsername() {
        usernamePerformEvaluation(); // Perform the evaluation to set all the assessment flags
    }

    /**********
     * setPassword method
     * 
     * <p> Evaluates the password input whenever the user changes it. </p>
     */
    private void setPassword() {
        passwordPerformEvaluation(); // Perform the evaluation to set all the assessment flags
    }

    /**********
     * setPasswordConfirm method
     * 
     * <p> Checks if the password confirmation matches the password input and updates 
     *     the validity message accordingly. </p>
     */
    private void setPasswordConfirm() {
        if (text_Password_Confirm.getText().equals(text_Password.getText())) {
            label_Password_Confirm_Validity.setTextFill(Color.GREEN);
            label_Password_Confirm_Validity.setText("The passwords match!");
        } else {
            label_Password_Confirm_Validity.setTextFill(Color.RED);
            label_Password_Confirm_Validity.setText("The passwords do not match!");
        }
    }

    /**********
     * passwordPerformEvaluation method
     * 
     * <p> Evaluates the password input against set criteria and updates the validation message 
     *     based on the evaluation results. </p>
     */
    private void passwordPerformEvaluation() {
        String inputText = text_Password.getText();
        if (inputText.isEmpty()) {
            label_Password_Validity.setTextFill(Color.RED);
            label_Password_Validity.setText("No input text found!");
        } else {
            String errMessage = PwdEval.evaluatePassword(inputText);
            if (!errMessage.equals("")) {
                updateFlags();
            } else if (PwdEval.foundUpperCase && PwdEval.foundLowerCase && PwdEval.foundNumericDigit
                    && PwdEval.foundSpecialChar && PwdEval.foundLongEnough) {
                System.out.println("Success! The password satisfies the requirements.");
                label_Password_Validity.setTextFill(Color.GREEN);
                label_Password_Validity.setText("Success! The password satisfies the requirements.");
            } else {
                label_Password_Validity.setTextFill(Color.RED);
                label_Password_Validity.setText("The password as currently entered is not yet valid.");
            }
        }
    }

    /**********
     * usernamePerformEvaluation method
     * 
     * <p> Evaluates the username input for validity and updates the validation message 
     *     accordingly. </p>
     */
    private void usernamePerformEvaluation() {
        String inputText = text_Username.getText();
        if (inputText.isEmpty()) {
            label_Username_Validity.setTextFill(Color.RED);
            label_Username_Validity.setText("No input text found!");
        } else {
            String errMessage = UserNameEval.checkForValidUserName(inputText);
            label_Username_Validity.setTextFill(Color.RED);
            label_Username_Validity.setText(errMessage);
            if (errMessage.equals("")) {
                System.out.println("Success! The username satisfies the requirements.");
                label_Username_Validity.setTextFill(Color.GREEN);
                label_Username_Validity.setText("Success! The username satisfies the requirements.");
            }
        }
    }

    /**********
     * updateFlags method
     * 
     * <p> Checks each password criterion and updates the password validity message 
     *     accordingly. </p>
     */
    private void updateFlags() {
        if (!PwdEval.foundUpperCase) {
            label_Password_Validity.setTextFill(Color.RED);
            label_Password_Validity.setText("At least one upper case letter");
        } else if (!PwdEval.foundLowerCase) {
            label_Password_Validity.setTextFill(Color.RED);
            label_Password_Validity.setText("At least one lower case letter");
        } else if (!PwdEval.foundNumericDigit) {
            label_Password_Validity.setTextFill(Color.RED);
            label_Password_Validity.setText("At least one numeric digit");
        } else if (!PwdEval.foundSpecialChar) {
            label_Password_Validity.setTextFill(Color.RED);
            label_Password_Validity.setText("At least one special character");
        } else if (!PwdEval.foundLongEnough) {
            label_Password_Validity.setTextFill(Color.RED);
            label_Password_Validity.setText("At least eight characters");
        }
    }
}
