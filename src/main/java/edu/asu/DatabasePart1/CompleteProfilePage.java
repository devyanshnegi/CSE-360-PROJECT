package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;

/**
 * <p>
 * CompleteProfilePage Class
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

public class CompleteProfilePage {
    // Labels and text fields for user profile input
    private Label Title1 = new Label("Complete Setting Up your Profile"); // Title label
    private Label FirstName = new Label("Enter Your First Name"); // First name label
    private TextField firstName = new TextField(); // First name input field
    private Label MiddleName = new Label("Enter Your Middle Name"); // Middle name label
    private TextField middleName = new TextField(); // Middle name input field
    private Label LastName = new Label("Enter Your Last Name"); // Last name label
    private TextField lastName = new TextField(); // Last name input field
    private Label PrefName = new Label("Choose Your Preferred Name"); // Preferred name label
    private TextField prefName = new TextField(); // Preferred name input field
    private Label Email = new Label("Enter Your Email Address"); // Email label
    private TextField emailEnter = new TextField(); // Email input field
    private Button completeProfile = new Button("Complete Profile"); // Button to complete profile

    /**
     * Constructor for the CompleteProfilePage class.
     * 
     * @param Root1 The Pane where the user interface components will be added.
     */
    public CompleteProfilePage(Pane Root1) {
        // Label the scene with the name of the testbed, centered at the top of the pane
        setupLabelUI(Title1, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

        // Set the font for the title label
        Font font3 = Font.font("Arial", FontWeight.BOLD, 25);
        Title1.setFont(font3);

        // Label for the first name input field, left aligned
        setupLabelUI(FirstName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 50);
        // Set font for the first name label
        Font font4 = Font.font("Arial", FontWeight.BOLD, 14);
        FirstName.setFont(font4);
        // Setup the first name input field
        setupTextUI(firstName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 75, true);

        // Label for the middle name input field, left aligned
        setupLabelUI(MiddleName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 115);
        // Set font for the middle name label
        Font font5 = Font.font("Arial", FontWeight.BOLD, 14);
        MiddleName.setFont(font5);
        // Setup the middle name input field
        setupTextUI(middleName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 145, true);

        // Label for the last name input field, left aligned
        setupLabelUI(LastName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 190);
        // Set font for the last name label
        Font font6 = Font.font("Arial", FontWeight.BOLD, 14);
        LastName.setFont(font6);
        // Setup the last name input field
        setupTextUI(lastName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 215, true);

        // Label for the preferred name input field, left aligned
        setupLabelUI(PrefName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 260);
        // Set font for the preferred name label
        Font font7 = Font.font("Arial", FontWeight.BOLD, 14);
        PrefName.setFont(font7);
        // Setup the preferred name input field
        setupTextUI(prefName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 290, true);

        // Label for the email input field, left aligned
        setupLabelUI(Email, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 335);
        // Set font for the email label
        Font font8 = Font.font("Arial", FontWeight.BOLD, 14);
        Email.setFont(font8);
        // Setup the email input field
        setupTextUI(emailEnter, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 355, true);

        // Set the layout position for the complete profile button
        completeProfile.setLayoutX(200);
        completeProfile.setLayoutY(400);

        // Add all components to the root pane
        Root1.getChildren().addAll(Title1, FirstName, firstName, MiddleName, middleName, LastName, lastName, PrefName,
                prefName, Email, emailEnter, completeProfile);
    }

    /**
     * Method to retrieve the complete profile button.
     * 
     * @return The completeProfile button.
     */
    public Button getButton() {
        return completeProfile;
    }

    /**
     * Private local method to initialize the standard fields for a label.
     * 
     * @param l The Label to set up.
     * @param ff The font family.
     * @param f The font size.
     * @param w The minimum width of the label.
     * @param p The alignment position.
     * @param x The layout X position.
     * @param y The layout Y position.
     */
    private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
        l.setFont(Font.font(ff, f)); // Set the font for the label
        l.setMinWidth(w); // Set the minimum width for the label
        l.setAlignment(p); // Set the alignment for the label
        l.setLayoutX(x); // Set the X layout position for the label
        l.setLayoutY(y); // Set the Y layout position for the label
    }

    /**
     * Private local method to initialize the standard fields for a text field.
     * 
     * @param t The TextField to set up.
     * @param ff The font family.
     * @param f The font size.
     * @param w The width of the text field.
     * @param p The alignment position.
     * @param x The layout X position.
     * @param y The layout Y position.
     * @param e Indicates if the text field is editable.
     */
    private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
        t.setFont(Font.font(ff, f)); // Set the font for the text field
        t.setMinWidth(w); // Set the minimum width for the text field
        t.setMaxWidth(w); // Set the maximum width for the text field
        t.setAlignment(p); // Set the alignment for the text field
        t.setLayoutX(x); // Set the X layout position for the text field
        t.setLayoutY(y); // Set the Y layout position for the text field
        t.setEditable(e); // Set whether the text field is editable
    }
}