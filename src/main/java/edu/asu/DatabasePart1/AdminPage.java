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

public class AdminPage {

	private Label Title2 = new Label("Admin Page");
	private Label Invite = new Label("Invite a New User");
	private Button InviteAction = new Button("Click here to Start Invite");
	private Label ListUsers = new Label("List Users");
	private Button ShowList = new Button("Click here to List all Users");
	private Label DeleteUsers = new Label("Delete User");
	private Button deleteUser = new Button("Click here to Delete User");
	private Label AddorRemoveRole = new Label("Add or Remove Role of a User");
	private Button addOrRemove = new Button("Click here to edit Role");
	private Label passwordReset = new Label("Password Reset");
	private Button PasswordReset = new Button("Click here to Reset Password");
	
	// New Logout button
	private Button logoutButton = new Button("Logout");

	public AdminPage(Pane Root2) {
		// Label theScene with the name of the testbed, centered at the top of the pane
		setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		Font fontTitle = Font.font("Arial", FontWeight.BOLD, 25);
		Title2.setFont(fontTitle);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(Invite, "Arial", 14, MainPage.WINDOW_WIDTH, Pos.CENTER, 10, 70);

		Font fontInvite = Font.font("Arial", FontWeight.BOLD, 14);
		Invite.setFont(fontInvite);

		InviteAction.setLayoutX(190);
		InviteAction.setLayoutY(100);

		// Label theScene with the name of the testbed, centered at the top of the pane
		setupLabelUI(ListUsers, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 10, 140);

		Font fontList = Font.font("Arial", FontWeight.BOLD, 14);
		ListUsers.setFont(fontList);

		ShowList.setLayoutX(190);
		ShowList.setLayoutY(170);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(DeleteUsers, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 210);

		Font fontDelete = Font.font("Arial", FontWeight.BOLD, 14);
		DeleteUsers.setFont(fontDelete);

		deleteUser.setLayoutX(190);
		deleteUser.setLayoutY(240);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(AddorRemoveRole, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 280);

		Font fontRole = Font.font("Arial", FontWeight.BOLD, 14);
		AddorRemoveRole.setFont(fontRole);

		addOrRemove.setLayoutX(190);
		addOrRemove.setLayoutY(310);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(passwordReset, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.CENTER, 10, 360);

		Font fontReset = Font.font("Arial", FontWeight.BOLD, 14);
		passwordReset.setFont(fontReset);

		PasswordReset.setLayoutX(170);
		PasswordReset.setLayoutY(390);

		// Set up the logout button
		setupLogoutButton();

		// Add all elements to the root pane
		Root2.getChildren().addAll(Title2, Invite, InviteAction, ListUsers, ShowList, DeleteUsers, deleteUser,
				AddorRemoveRole, addOrRemove, passwordReset, PasswordReset, logoutButton);

	}

	// Method to initialize and set up the logout button
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

	// Private local method to initialize the standard fields for a label
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	// Private local method to initialize the standard fields for a text
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
