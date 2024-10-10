package edu.asu.DatabasePart1;

//JavaFX imports needed to support the Graphical User Interface
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

public class chooseInterfaceController {
	private Label Title1 = new Label("Complete Setting Up your Profile");
	private Label FirstName = new Label("Enter Your First Name");
	private TextField firstName = new TextField();
	private Label MiddleName = new Label("Enter Your Middle Name");
	private TextField middleName = new TextField();
	private Label LastName = new Label("Enter Your Last Name");
	private TextField lastName = new TextField();
	private Label PrefName = new Label("Choose Your Preferred Name");
	private TextField prefName = new TextField();
	private Label Email = new Label("Enter Your Email Address");
	private TextField emailEnter = new TextField();
	private Button completeProfile = new Button("Complete Profile");

	public chooseInterfaceController(Pane Root1) {
		// Label theScene with the name of the testbed, centered at the top of the pane
		setupLabelUI(Title1, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		Font font3 = Font.font("Arial", FontWeight.BOLD, 25);
		Title1.setFont(font3);

		// Label the password input field with a title just above it, left aligned
		setupLabelUI(FirstName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 50);

		// the code will process the entire input to ensure that it is valid or in
		// error.
		setupTextUI(firstName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 75, true);

		// Enhancements to the label Text
		Font font4 = Font.font("Arial", FontWeight.BOLD, 14);
		FirstName.setFont(font4);

		// Label the login option
		setupLabelUI(MiddleName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 115);

		Font font5 = Font.font("Arial", FontWeight.BOLD, 14);
		MiddleName.setFont(font5);

		setupTextUI(middleName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 145, true);

		// Label the login option
		setupLabelUI(LastName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 190);

		Font font6 = Font.font("Arial", FontWeight.BOLD, 14);
		LastName.setFont(font6);

		setupTextUI(lastName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 215, true);

		// Label the login option
		setupLabelUI(PrefName, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 260);

		Font font7 = Font.font("Arial", FontWeight.BOLD, 14);
		PrefName.setFont(font7);

		setupTextUI(prefName, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 290, true);

		// Label the login option
		setupLabelUI(Email, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 335);

		Font font8 = Font.font("Arial", FontWeight.BOLD, 14);
		Email.setFont(font8);

		setupTextUI(emailEnter, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 355, true);

		completeProfile.setLayoutX(200);
		completeProfile.setLayoutY(400);

		Root1.getChildren().addAll(Title1, FirstName, firstName, MiddleName, middleName, LastName, lastName, PrefName,
				prefName, Email, emailEnter, completeProfile);
	}

//Private local method to initialize the standard fields for a label

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
