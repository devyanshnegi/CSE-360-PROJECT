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

/***
 * <p> AdminPage Class </p>
 * 
 * <p> Description: A JavaFX class representing the admin interface for user management, 
 * including functionalities such as inviting users, listing users, deleting users, 
 * managing roles, and resetting passwords. </p>
 * 
 * <p> Copyright: Your Name © 2024 </p>
 * 
 * @author Your Name
 * 
 * @version 1.00	2024-10-09 Initial creation of the AdminPage class for managing users
 * 
 */

public class AdminPage {

	/** Label for the title of the admin page */
	private Label Title2 = new Label("Admin Page");
	/** Label for inviting a new user */
	private Label Invite = new Label("Invite a New User");
	/** Button to start the user invitation process */
	private Button InviteAction = new Button("Click here to Start Invite");
	/** Label to show the option to list users */
	private Label ListUsers = new Label("List Users");
	/** Button to display all users */
	private Button ShowList = new Button("Click here to List all Users");
	/** Label for deleting a user */
	private Label DeleteUsers = new Label("Delete User");
	/** Button to initiate the user deletion process */
	private Button deleteUser = new Button("Click here to Delete User");
	/** Label for adding or removing user roles */
	private Label AddorRemoveRole = new Label("Add or Remove Role of a User");
	/** Button to edit user roles */
	private Button addOrRemove = new Button("Click here to edit Role");
	/** Label for the password reset functionality */
	private Label passwordReset = new Label("Password Reset");
	/** Button to reset a user's password */
	private Button PasswordReset = new Button("Click here to Reset Password");

	/** 
	 * The constructor for the AdminPage class.
	 * Initializes the user interface elements and adds them to the given pane.
	 * 
	 * @param Root2 The pane where all the UI elements will be added
	 */
	public AdminPage(Pane Root2) {
		// Set up the title label UI with specified font and position
		setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Set the font for the title
		Font fontTitle = Font.font("Arial", FontWeight.BOLD, 25);
		Title2.setFont(fontTitle);

		// Set up the invite label UI
		setupLabelUI(Invite, "Arial", 14, MainPage.WINDOW_WIDTH, Pos.CENTER, 10, 70);
		Font fontInvite = Font.font("Arial", FontWeight.BOLD, 14);
		Invite.setFont(fontInvite);

		// Position the invite action button
		InviteAction.setLayoutX(190);
		InviteAction.setLayoutY(100);

		// Set up the list users label UI
		setupLabelUI(ListUsers, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 10, 140);
		Font fontList = Font.font("Arial", FontWeight.BOLD, 14);
		ListUsers.setFont(fontList);
		ShowList.setLayoutX(190);
		ShowList.setLayoutY(170);

		// Set up the delete users label UI
		setupLabelUI(DeleteUsers, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 210);
		Font fontDelete = Font.font("Arial", FontWeight.BOLD, 14);
		DeleteUsers.setFont(fontDelete);
		deleteUser.setLayoutX(190);
		deleteUser.setLayoutY(240);

		// Set up the add or remove role label UI
		setupLabelUI(AddorRemoveRole, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 280);
		Font fontRole = Font.font("Arial", FontWeight.BOLD, 14);
		AddorRemoveRole.setFont(fontRole);
		addOrRemove.setLayoutX(190);
		addOrRemove.setLayoutY(310);

		// Set up the password reset label UI
		setupLabelUI(passwordReset, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 360);
		Font fontReset = Font.font("Arial", FontWeight.BOLD, 14);
		passwordReset.setFont(fontReset);
		PasswordReset.setLayoutX(170);
		PasswordReset.setLayoutY(390);

		// Add all UI components to the root pane
		Root2.getChildren().addAll(Title2, Invite, InviteAction, ListUsers, ShowList, DeleteUsers, deleteUser,
				AddorRemoveRole, addOrRemove, passwordReset, PasswordReset);
	}

	/****
	 * This method initializes the standard fields for a label.
	 * 
	 * @param l The label to be set up
	 * @param ff The font family for the label
	 * @param f The font size for the label
	 * @param w The width of the label
	 * @param p The alignment position of the label
	 * @param x The X coordinate for the label's position
	 * @param y The Y coordinate for the label's position
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f)); // Set the font for the label
		l.setMinWidth(w); // Set the minimum width for the label
		l.setAlignment(p); // Set the alignment of the label
		l.setLayoutX(x); // Set the X position of the label
		l.setLayoutY(y); // Set the Y position of the label
	}

	/****
	 * This method initializes the standard fields for a text field.
	 * 
	 * @param t The text field to be set up
	 * @param ff The font family for the text field
	 * @param f The font size for the text field
	 * @param w The width of the text field
	 * @param p The alignment position of the text field
	 * @param x The X coordinate for the text field's position
	 * @param y The Y coordinate for the text field's position
	 * @param e The editability status of the text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
		t.setFont(Font.font(ff, f)); // Set the font for the text field
		t.setMinWidth(w); // Set the minimum width for the text field
		t.setMaxWidth(w); // Set the maximum width for the text field
		t.setAlignment(p); // Set the alignment of the text field
		t.setLayoutX(x); // Set the X position of the text field
		t.setLayoutY(y); // Set the Y position of the text field
		t.setEditable(e); // Set the editability of the text field
	}
}