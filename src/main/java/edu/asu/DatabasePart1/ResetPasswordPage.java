package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*******
 * <p> NewPasswordPage Class </p>
 * 
 * <p> Description: This class provides a graphical user interface for users to set a new password. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00 2024-11-27 Initial implementation of the NewPasswordPage class for handling password reset.
 */
public class ResetPasswordPage {

    // UI Components
    private Label label_ApplicationTitle = new Label("Reset Password");
    private Label label_NewPassword = new Label("Enter your new password:");
    private Label label_ConfirmPassword = new Label("Confirm your new password:");
    private Label label_PasswordValidity = new Label("");
    private Label label_ConfirmPasswordValidity = new Label("");

    private TextField text_NewPassword = new TextField();
    private TextField text_ConfirmPassword = new TextField();

    private Button button_Submit = new Button("Submit");

    private PasswordEvaluator pwdEval = new PasswordEvaluator();

    private int WINDOW_WIDTH = 700;

    private boolean validPassword = false, validConfirmPassword = false;

    private static DatabaseHelper databaseHelper = new DatabaseHelper();

    /**********
     * Constructor for NewPasswordPage
     * 
     * @param theRoot The root Pane where the UI components will be added.
     * @param sceneController The scene controller to switch scenes after successful password reset.
     */
    public ResetPasswordPage(Pane theRoot, SceneController sceneController) {
        // Title
        setupLabelUI(label_ApplicationTitle, "Arial", 24, WINDOW_WIDTH, Pos.CENTER, 0, 10);

        // New Password Input
        setupLabelUI(label_NewPassword, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 50);
        setupTextUI(text_NewPassword, "Arial", 18, WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 70, true);
        setupLabelUI(label_PasswordValidity, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 115);

        // Confirm Password Input
        setupLabelUI(label_ConfirmPassword, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 150);
        setupTextUI(text_ConfirmPassword, "Arial", 18, WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 170, true);
        setupLabelUI(label_ConfirmPasswordValidity, "Arial", 14, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 215);

        // Listeners for input fields
        text_NewPassword.textProperty().addListener((observable, oldValue, newValue) -> passwordPerformEvaluation());
        text_ConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> setPasswordConfirm());

        // Submit Button
        setupButtonUI(button_Submit, "Arial", 14, 100, Pos.CENTER, WINDOW_WIDTH / 2 - 50, 300);
        button_Submit.setOnAction(event -> {
            if (validPassword && validConfirmPassword) {
                int otp = (int) sceneController.getData("otp");
                if (updatePassword(text_NewPassword.getText(), otp)) {
                    System.out.println("Password reset successful. Redirecting to login...");
                    sceneController.switchTo("Login");
                } else {
                    label_PasswordValidity.setTextFill(Color.RED);
                    label_PasswordValidity.setText("Failed to reset the password. Try again.");
                }
            }
        });

        // Add UI elements to the pane
        theRoot.getChildren().addAll(label_ApplicationTitle, label_NewPassword, label_ConfirmPassword,
                label_PasswordValidity, label_ConfirmPasswordValidity, text_NewPassword, text_ConfirmPassword,
                button_Submit);
    }

    /**********
     * Updates the user's password in the database.
     */
    private boolean updatePassword(String newPassword, int otp) {
        boolean success = false;
        try {
            databaseHelper.connectToDatabase();
            success = databaseHelper.resetPassword(otp, newPassword);
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            databaseHelper.closeConnection();
        }
        return success;
    }

    /**********
     * Validates the new password and displays feedback to the user.
     */
    private void passwordPerformEvaluation() {
        String inputText = text_NewPassword.getText();
        if (inputText.isEmpty()) {
            label_PasswordValidity.setTextFill(Color.RED);
            label_PasswordValidity.setText("No input text found!");
            validPassword = false;
        } else {
            String errMessage = pwdEval.evaluatePassword(inputText);
            if (!errMessage.equals("")) {
                updateFlags();
                validPassword = false;
            } else if (pwdEval.foundUpperCase && pwdEval.foundLowerCase && pwdEval.foundNumericDigit
                    && pwdEval.foundSpecialChar && pwdEval.foundLongEnough) {
                label_PasswordValidity.setTextFill(Color.GREEN);
                label_PasswordValidity.setText("Success! The password satisfies the requirements.");
                validPassword = true;
            } else {
                label_PasswordValidity.setTextFill(Color.RED);
                label_PasswordValidity.setText("The password as currently entered is not yet valid.");
                validPassword = false;
            }
        }
        setPasswordConfirm();
    }

    /**********
     * Validates that the confirmed password matches the new password.
     */
    private void setPasswordConfirm() {
        if (text_ConfirmPassword.getText().equals(text_NewPassword.getText())) {
            label_ConfirmPasswordValidity.setTextFill(Color.GREEN);
            label_ConfirmPasswordValidity.setText("The passwords match!");
            validConfirmPassword = true;
        } else {
            label_ConfirmPasswordValidity.setTextFill(Color.RED);
            label_ConfirmPasswordValidity.setText("The passwords do not match!");
            validConfirmPassword = false;
        }
    }

    /**********
     * Updates error messages for password validity.
     */
    private void updateFlags() {
        if (!pwdEval.foundUpperCase) {
            label_PasswordValidity.setTextFill(Color.RED);
            label_PasswordValidity.setText("At least one upper case letter");
        } else if (!pwdEval.foundLowerCase) {
            label_PasswordValidity.setTextFill(Color.RED);
            label_PasswordValidity.setText("At least one lower case letter");
        } else if (!pwdEval.foundNumericDigit) {
            label_PasswordValidity.setTextFill(Color.RED);
            label_PasswordValidity.setText("At least one numeric digit");
        } else if (!pwdEval.foundSpecialChar) {
            label_PasswordValidity.setTextFill(Color.RED);
            label_PasswordValidity.setText("At least one special character");
        } else if (!pwdEval.foundLongEnough) {
            label_PasswordValidity.setTextFill(Color.RED);
            label_PasswordValidity.setText("At least eight characters");
        }
    }

    // Utility methods for UI setup (same as RegisterPage)
    private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
        l.setFont(Font.font(ff, f));
        l.setMinWidth(w);
        l.setAlignment(p);
        l.setLayoutX(x);
        l.setLayoutY(y);
    }

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
}
