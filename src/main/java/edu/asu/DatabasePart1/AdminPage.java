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
import javafx.stage.Stage;

/*******
 * <p> AdminPage Class </p>
 * 
 * <p> Description: The AdminPage class provides the graphical user interface for administrators, 
 *                  allowing them to perform various administrative tasks such as inviting users, 
 *                  listing users, deleting users, modifying user roles, resetting passwords, and 
 *                  logging out. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the AdminPage class for managing 
 *                  administrative functionalities.
 */

public class AdminPage {

    /** Label for the admin page title */
    private Label Title2 = new Label("Admin Page");

    /** Label prompting to invite a new user */
    private Label Invite = new Label("Invite a New User");

    /** Button to start the invite process */
    private Button InviteAction = new Button("Click here to Start Invite");

    /** Label for listing users */
    private Label ListUsers = new Label("List Users");

    /** Button to list all users */
    private Button ShowList = new Button("Click here to List all Users");

    /** Label for deleting a user */
    private Label DeleteUsers = new Label("Delete User");

    /** Button to delete a user */
    private Button deleteUser = new Button("Click here to Delete User");

    /** Label for adding or removing a user role */
    private Label AddorRemoveRole = new Label("Add or Remove Role of a User");

    /** Button to edit a user's role */
    private Button addOrRemove = new Button("Click here to edit Role");

    /** Label for password reset */
    private Label passwordReset = new Label("Password Reset");

    /** Button to reset the password */
    private Button PasswordReset = new Button("Click here to Reset Password");

    /** Button for logging out */
    private Button logoutButton = new Button("Logout");

    /**********
     * Constructor for AdminPage
     * 
     * <p> Sets up the user interface for the admin page, including all administrative options 
     *     such as inviting users, listing users, deleting users, modifying roles, resetting 
     *     passwords, and logging out. </p>
     * 
     * @param Root2 The root Pane where the UI components will be added.
     */
    public AdminPage(Pane Root2) {
        // Label the scene with the name of the testbed, centered at the top of the pane
        setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);
        Font fontTitle = Font.font("Arial", FontWeight.BOLD, 25);
        Title2.setFont(fontTitle);

        // Set up the Invite label and button
        setupLabelUI(Invite, "Arial", 14, MainPage.WINDOW_WIDTH, Pos.CENTER, 10, 70);
        Font fontInvite = Font.font("Arial", FontWeight.BOLD, 14);
        Invite.setFont(fontInvite);
        InviteAction.setLayoutX(190);
        InviteAction.setLayoutY(100);

        // Set up the List Users label and button
        setupLabelUI(ListUsers, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 10, 140);
        Font fontList = Font.font("Arial", FontWeight.BOLD, 14);
        ListUsers.setFont(fontList);
        ShowList.setLayoutX(190);
        ShowList.setLayoutY(170);

        // Set up the Delete User label and button
        setupLabelUI(DeleteUsers, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 210);
        Font fontDelete = Font.font("Arial", FontWeight.BOLD, 14);
        DeleteUsers.setFont(fontDelete);
        deleteUser.setLayoutX(190);
        deleteUser.setLayoutY(240);

        // Set up the Add or Remove Role label and button
        setupLabelUI(AddorRemoveRole, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 280);
        Font fontRole = Font.font("Arial", FontWeight.BOLD, 14);
        AddorRemoveRole.setFont(fontRole);
        addOrRemove.setLayoutX(190);
        addOrRemove.setLayoutY(310);

        // Set up the Password Reset label and button
        setupLabelUI(passwordReset, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 360);
        Font fontReset = Font.font("Arial", FontWeight.BOLD, 14);
        passwordReset.setFont(fontReset);
        PasswordReset.setLayoutX(170);
        PasswordReset.setLayoutY(390);

        // Set up the Logout button
        setupLogoutButton();

        // Add all elements to the root pane
        Root2.getChildren().addAll(Title2, Invite, InviteAction, ListUsers, ShowList, DeleteUsers, deleteUser,
                AddorRemoveRole, addOrRemove, passwordReset, PasswordReset, logoutButton);
    }

    /**********
     * setupLogoutButton method
     * 
     * <p> Initializes and sets up the logout button, including its layout and the action to 
     *     perform when clicked, which closes the application. </p>
     */
    private void setupLogoutButton() {
        logoutButton.setText("Logout");
        logoutButton.setLayoutX(190); // Set X position
        logoutButton.setLayoutY(440); // Move the Y position higher, closer to the bottom of the visible window
        logoutButton.setMinWidth(100); // Set minimum width

        // Set action to close the application when clicked
        logoutButton.setOnAction(event -> {
            System.out.println("Logging out... Closing application.");
            Stage stage = (Stage) logoutButton.getScene().getWindow(); // Get the current stage
            stage.close(); // Close the stage
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
