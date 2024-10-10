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

/*******
 * <p> confirmationPage Class </p>
 * 
 * <p> Description: confirmationPage provides a simple user interface for confirming 
 *                  an action. It displays a prompt asking if the user is sure and 
 *                  provides "Yes" and "No" buttons for the user's response. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the confirmationPage class for 
 *                  action confirmation within the multi-page application.
 */

public class confirmationPage {

    /** Label displaying the confirmation question */
    private Label Title2 = new Label("Are You Sure?");
    
    /** Button for confirming the action */
    private Button yesAction = new Button("Yes");
    
    /** Button for canceling the action */
    private Button noAction = new Button("No");

    /**********
     * Constructor for confirmationPage
     * 
     * <p> Sets up the user interface layout for the confirmation prompt, including 
     *     the label and buttons. </p>
     * 
     * @param Root2 The root Pane where the UI components will be added.
     */
    public confirmationPage(Pane Root2) {
        // Set up the title label at the top of the pane
        setupLabelUI(Title2, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

        Font fontTitle = Font.font("Arial", FontWeight.BOLD, 25);
        Title2.setFont(fontTitle);

        // Set up the "Yes" button
        yesAction.setLayoutX(150);
        yesAction.setLayoutY(100);

        // Set up the "No" button
        noAction.setLayoutX(300);
        noAction.setLayoutY(100);

        // Add components to the pane
        Root2.getChildren().addAll(Title2, yesAction, noAction);
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
