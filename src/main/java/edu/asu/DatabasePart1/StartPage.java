package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*******
 * <p> StartPage Class </p>
 * 
 * <p> Description: The StartPage class provides the graphical user interface for the application's 
 *                  start screen, allowing users to either enter an invitation code or log in. It 
 *                  facilitates switching to a registration scene upon entering a valid invitation 
 *                  code. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the StartPage class for navigating to 
 *                  different user flows based on input.
 */

public class StartPage {

    /** Label for the page title */
    private Label Title = new Label("Login or Enter Invitation Code");
    
    /** Label prompting the user to enter the invitation code */
    private Label Invite = new Label("Enter the Invitation Code Here:");
    
    /** TextField for entering the invitation code */
    private TextField Invite_Code = new TextField();
    
    /** Label for displaying an error message if the invitation code is missing */
    private Label noCode = new Label("");
    
    /** Button to submit the invitation code */
    private Button InviteCodeEnter = new Button("Enter Code");

    /** Label prompting the user to choose the login option */
    private Label loginOption = new Label("Choose to Login:");
    
    /** Button to navigate to the login screen */
    private Button loginRedirect = new Button("Login");

    /**********
     * Constructor for StartPage
     * 
     * <p> Sets up the user interface for the start page, including the invitation code 
     *     input field, error message label, and buttons for submitting the code or logging 
     *     in. Also adds functionality to the invitation code button for switching to the 
     *     registration scene. </p>
     * 
     * @param Root The root Pane where the UI components will be added.
     * @param theStage The primary Stage for switching scenes.
     * @param registerScene The scene to switch to when a valid invitation code is entered.
     */
    public StartPage(Pane Root, SceneController sceneController) {
        // Label the Scene with the name of the testbed, centered at the top of the pane
        setupLabelUI(Title, "Arial", 24, MainApp.WINDOW_WIDTH, Pos.CENTER, 0, 10);

        // Enhancements to the Title of the Page
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        Title.setFont(font);

        // Label for the invitation code field
        setupLabelUI(Invite, "Arial", 14, MainApp.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 70);
        Font font1 = Font.font("Arial", FontWeight.BOLD, 14);
        Invite.setFont(font1);

        // Placeholder text for the invitation code text field
        Invite_Code.setPromptText("Enter Code provided by Admin");

        // Set up the invitation code text field
        setupTextUI(Invite_Code, "Arial", 18, MainApp.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 100, true);

        // Error message label for missing invitation code
        noCode.setTextFill(Color.RED);
        noCode.setAlignment(Pos.BASELINE_LEFT);
        setupLabelUI(noCode, "Arial", 16, MainApp.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 120);

        // Set up the invitation code button layout
        InviteCodeEnter.setLayoutX(200);
        InviteCodeEnter.setLayoutY(150);

        // Label for the login option
        setupLabelUI(loginOption, "Arial", 14, MainApp.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 200);
        Font font2 = Font.font("Arial", FontWeight.BOLD, 14);
        loginOption.setFont(font2);

        // Set up the login button layout
        loginRedirect.setLayoutX(210);
        loginRedirect.setLayoutY(240);

        // Add all elements to the scene
        Root.getChildren().addAll(Title, Invite, Invite_Code, noCode, InviteCodeEnter, loginOption, loginRedirect);

    	// Handle button actions to switch between scenes
        loginRedirect.setOnAction(event -> {
            sceneController.switchTo("Login");
        });
        
        // Add functionality for the Enter Code button to switch to RegisterPage
        InviteCodeEnter.setOnAction(event -> {
            String inviteCode = Invite_Code.getText();
            if (!inviteCode.isEmpty()) {
                // Switch to the RegisterPage
            	sceneController.switchTo("Register");
            } else {
                // Display an error message if no code is entered
                noCode.setText("Please enter a valid invitation code.");
            }
        });
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
}
