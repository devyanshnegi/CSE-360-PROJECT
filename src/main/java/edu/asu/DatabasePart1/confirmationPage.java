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

public class confirmationPage {

	private Label Title2 = new Label("Are You Sure?");
	
	private Button yesAction = new Button("Yes");
	private Button noAction = new Button("No");


	public confirmationPage(Pane Root2) {
		// Label theScene with the name of the testbed, centered at the top of the pane
		setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		Font fontTitle = Font.font("Arial", FontWeight.BOLD, 25);
		Title2.setFont(fontTitle);

		
		yesAction.setLayoutX(150);
		yesAction.setLayoutY(100);


		noAction.setLayoutX(300);
		noAction.setLayoutY(100);


		Root2.getChildren().addAll(Title2, yesAction, noAction);
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