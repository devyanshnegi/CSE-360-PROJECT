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
 * <p> HomePage Class </p>
 * 
 * <p> Description: A JavaFX class representing the home page interface, allowing users 
 * to log out of the application. </p>
 * 
 * <p> Copyright: Your Name © 2024 </p>
 * 
 * @author Your Name
 * 
 * @version 1.00	2024-10-09 Initial creation of the HomePage class for user logout
 * 
 */

public class HomePage {
	
	/** Label for the title of the home page */
	private Label Title2 = new Label("Home Page");
	/** Label prompting the user to log out */
	private Label logOut = new Label("Click Here to LogOut");
	/** Button to initiate the logout process */
	private Button logOutButton = new Button("Logout");
	
	/** 
	 * The constructor for the HomePage class.
	 * Initializes the user interface elements and adds them to the given pane.
	 * 
	 * @param Root4 The pane where all the UI elements will be added
	 */
	public HomePage(Pane Root4) {
		// Set up the title label UI with specified font and position
		setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Set the font for the title
		Font fontTitle1 = Font.font("Arial", FontWeight.BOLD, 25);
		Title2.setFont(fontTitle1);
		
		// Set up the log out label UI
		setupLabelUI(logOut, "Arial", 14, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 70);
		
		// Set the font for the log out label
		Font fontlogout = Font.font("Arial", FontWeight.BOLD, 14);
		logOut.setFont(fontlogout);
		
		// Position the log out button on the pane
		logOutButton.setLayoutX(215);
		logOutButton.setLayoutY(100);
		
		// Add all UI components to the root pane
		Root4.getChildren().addAll(Title2, logOut, logOutButton);
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