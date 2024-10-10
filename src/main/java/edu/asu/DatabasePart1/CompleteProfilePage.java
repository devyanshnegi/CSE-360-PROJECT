package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;

/*******
 * <p> CompleteProfilePage Class </p>
 * 
 * <p> Description: CompleteProfilePage handles the graphical user interface for setting up a 
 *                  user's profile in the application. It allows users to input personal 
 *                  information like first name, middle name, last name, preferred name, and 
 *                  email, and provides a button to complete the profile setup. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the CompleteProfilePage class to facilitate 
 *                  profile completion as part of the multi-page application.
 */

public class CompleteProfilePage {
    
    /** Label prompting user to complete the profile setup */
    private Label Title1 = new Label("Complete Setting Up your Profile");
    
    /** Label for the first name field */
    private Label FirstName = new Label("Enter Your First Name");
    
    /** Text field for user to input their first name */
    private TextField firstName = new TextField();
    
    /** Label for the middle name field */
    private Label MiddleName = new Label("Enter Your Middle Name");
    
    /** Text field for user to input their middle name */
    private TextField middleName = new TextField();
    
    /** Label for the last name field */
    private Label LastName = new Label("Enter Your Last Name");
    
    /** Text field for user to input their last name */
    private TextField lastName = new TextField();
    
    /** Label for the preferred name field */
    private Label PrefName = new Label("Choose Your Preferred Name");
    
    /** Text field for user to input their preferred name */
    private TextField prefName = new TextField();
    
    /** Label for the email field */
    private Label Email = new Label("Enter Your Email Address");
    
    /** Text field for user to input their email address */
    private TextField emailEnter = new TextField();
    
    /** Button to complete the profile setup and proceed */
    private Button completeProfile = new Button("Complete Profile");

    /**********
     * Constructor for CompleteProfilePage
     * 
     * <p> Sets up the user interface layout and initializes components for profile completion. 
     *     The constructor also adds event handling for the "Complete Profile" button, which 
     *     redirects to the home scene. </p>
     * 
     * @param Root1 The root Pane where the UI components will be added.
     * @param theStage The primary stage for switching scenes.
     * @param homeScene The Scene to redirect to after completing the profile.
     */
    public CompleteProfilePage(Pane Root1, Stage theStage, Scene homeScene) {
        // Set up the layout and UI components
        setupLabelUI(Title1, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

        Font font3 = Font.font("Arial", FontWeight.BOLD, 25);
        Title1.setFont(font3);

        // First Name
        setupLabelUI(FirstName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 50);
        setupTextUI(firstName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 75, true);

        // Middle Name
        setupLabelUI(MiddleName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 115);
        setupTextUI(middleName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 145, true);

        // Last Name
        setupLabelUI(LastName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 190);
        setupTextUI(lastName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 215, true);

        // Preferred Name
        setupLabelUI(PrefName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 260);
        setupTextUI(prefName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 290, true);

        // Email
        setupLabelUI(Email, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 335);
        setupTextUI(emailEnter, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 355, true);

        // Complete Profile Button
        completeProfile.setLayoutX(200);
        completeProfile.setLayoutY(400);

        // Add all components to the pane
        Root1.getChildren().addAll(Title1, FirstName, firstName, MiddleName, middleName, LastName, lastName, PrefName,
                prefName, Email, emailEnter, completeProfile);

        // Add action to Complete Profile button to switch to HomePage
        completeProfile.setOnAction(event -> {
            printProfileInputs();  // Print the inputs to console
            theStage.setScene(homeScene);  // Redirect to HomePage
        });
    }

    /**********
     * printProfileInputs method
     * 
     * <p> Prints the profile information entered by the user to the console. </p>
     */
    private void printProfileInputs() {
        System.out.println("First Name: " + firstName.getText());
        System.out.println("Middle Name: " + middleName.getText());
        System.out.println("Last Name: " + lastName.getText());
        System.out.println("Preferred Name: " + prefName.getText());
        System.out.println("Email: " + emailEnter.getText());
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
