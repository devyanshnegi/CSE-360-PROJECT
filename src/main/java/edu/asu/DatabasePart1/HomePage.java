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


public class HomePage{
	private Label Title2 = new Label("Home Page");
	private Label logOut = new Label("Click Here to LogOut");
	private Button logOutButton = new Button("Logout");
	
public HomePage(Pane Root4) {
	
	// Label theScene with the name of the testbed, centered at the top of the pane
			setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

			Font fontTitle1 = Font.font("Arial", FontWeight.BOLD, 25);
			Title2.setFont(fontTitle1);
			
			// Label the password input field with a title just above it, left aligned
			setupLabelUI(logOut, "Arial", 14, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 70);

			Font fontlogout = Font.font("Arial", FontWeight.BOLD, 14);
			logOut.setFont(fontlogout);
			
			
			logOutButton.setLayoutX(215);
			logOutButton.setLayoutY(100);
			
			
			Root4.getChildren().addAll(Title2, logOut, logOutButton);
			
	
}


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