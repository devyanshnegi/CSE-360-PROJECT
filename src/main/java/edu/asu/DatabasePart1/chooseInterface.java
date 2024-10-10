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

public class chooseInterface {

	private Label Title = new Label("Login or Enter Invitation Code");
	private Label Invite = new Label("Enter the Invitation Code Here:");
	private TextField Invite_Code = new TextField();
	private Label noCode = new Label("");
	private Button InviteCodeEnter = new Button("Enter Code");

	private Label loginOption = new Label("Choose to Login:");
	private Button loginRedirect = new Button("Login");

	public chooseInterface(Pane Root) {
		// Label theScene with the name of the testbed, centered at the top of the pane
		setupLabelUI(Title, "Arial", 24, chooseOrRegisterPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Enhancements to the Title of the Page
		Font font = Font.font("Arial", FontWeight.BOLD, 25);
		Title.setFont(font);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(Invite, "Arial", 14, chooseOrRegisterPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 70);

		// Enhancements to the label Text
		Font font1 = Font.font("Arial", FontWeight.BOLD, 14);
		Invite.setFont(font1);

		// placeholder text for textfield
		Invite_Code.setPromptText("Enter Code provided by Admin");

		// the code will process the entire input to ensure that it is valid or in
		// error.
		setupTextUI(Invite_Code, "Arial", 18, chooseOrRegisterPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 100, true);

		// Establish an error message for the case where there is no input
		noCode.setTextFill(Color.RED);
		noCode.setAlignment(Pos.BASELINE_LEFT);
		setupLabelUI(noCode, "Arial", 16, chooseOrRegisterPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 120);

		// redirection on pressing button to the login page
		// InviteCodeEnter.addEventHandler(null, null);

		// Set the layout of the button
		InviteCodeEnter.setLayoutX(200);
		InviteCodeEnter.setLayoutY(150);

		// Label the login option
		setupLabelUI(loginOption, "Arial", 14, chooseOrRegisterPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 200);

		Font font2 = Font.font("Arial", FontWeight.BOLD, 14);
		loginOption.setFont(font2);

		// set up the button onto the scene

		// action of button redirecting to login page
		// loginRedirect.addEventHandler(null, null);
		loginRedirect.setLayoutX(210);
		loginRedirect.setLayoutY(240);

		// add all elements to the scene
		Root.getChildren().addAll(Title, Invite, Invite_Code, noCode, InviteCodeEnter, loginOption, loginRedirect);
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
