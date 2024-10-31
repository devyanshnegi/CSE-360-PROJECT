package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.application.Platform;

/*******
 * <p> HomePage Class </p>
 * 
 * <p> Description: HomePage provides the graphical user interface for the home screen of the 
 *                  application. It displays a title and a logout button that allows users to 
 *                  exit the application. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the HomePage class for displaying the 
 *                  home screen and handling the logout functionality.
 */

public class StudentPage {

    /** Label displaying the title of the home page */
    private Label Title2 = new Label("Home Page");
    
    /** Label prompting the user to click the logout button */
    private Label logOut = new Label("Click Here to LogOut");
    
    /** Button for logging out of the application */
    private Button logOutButton = new Button("Logout");

    /**********
     * Constructor for HomePage
     * 
     * <p> Sets up the user interface layout for the home page, including the title label, 
     *     logout prompt, and logout button. The constructor also adds an action to the 
     *     logout button to close the application when clicked. </p>
     * 
     * @param Root The root Pane where the UI components will be added.
     */
    public StudentPage(Pane Root, SceneController sceneController) {
        // Label the scene with the home page title, centered at the top of the pane
        setupLabelUI(Title2, "Arial", 24, MainApp.WINDOW_WIDTH, Pos.CENTER, 0, 10);

        Font fontTitle1 = Font.font("Arial", FontWeight.BOLD, 25);
        Title2.setFont(fontTitle1);

        // Label for the logout action
        setupLabelUI(logOut, "Arial", 14, MainApp.WINDOW_WIDTH, Pos.CENTER, 0, 70);

        Font fontlogout = Font.font("Arial", FontWeight.BOLD, 14);
        logOut.setFont(fontlogout);

        // Position the logout button
        logOutButton.setLayoutX(215);
        logOutButton.setLayoutY(100);

        // Add action to logout button to close the application
        logOutButton.setOnAction(event -> {
            System.out.println("Logout button clicked. Closing the application...");
            Platform.exit(); // Gracefully close the application
        });

        // Add all components to the pane
        Root.getChildren().addAll(Title2, logOut, logOutButton);
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
