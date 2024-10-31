package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*******
 * <p> InstructorPage Class </p>
 * 
 * <p> Description: The InstructorPage class provides a graphical user interface for instructors, 
 *                  allowing them to view or publish articles. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-30 Initial implementation of the InstructorPage class.
 */

public class InstructorPage {

    /** Label for the instructor page title */
    private Label titleLabel = new Label("Instructor Page");

    /** Button to view articles */
    private Button viewArticlesButton = new Button("View Articles");

    /** Button to publish articles */
    private Button publishArticlesButton = new Button("Publish Articles");

    /**********
     * Constructor for InstructorPage
     * 
     * <p> Sets up the user interface for the instructor page, including options 
     *     for viewing and publishing articles. </p>
     * 
     * @param root The root Pane where the UI components will be added.
     */
    public InstructorPage(Pane root, SceneController sceneController) {
        // Set up the page title label
        setupLabelUI(titleLabel, "Arial", 24, 300, Pos.CENTER, 100, 20);
        Font fontTitle = Font.font("Arial", FontWeight.BOLD, 24);
        titleLabel.setFont(fontTitle);

        // Set up the View Articles button
        viewArticlesButton.setLayoutX(150);
        viewArticlesButton.setLayoutY(100);
        viewArticlesButton.setOnAction(e -> showAlert("View Articles", "Displaying all articles..."));

        // Set up the Publish Articles button
        publishArticlesButton.setLayoutX(150);
        publishArticlesButton.setLayoutY(150);
        publishArticlesButton.setOnAction(e -> showAlert("Publish Articles", "Publishing new article..."));

        // Add all components to the root pane
        root.getChildren().addAll(titleLabel, viewArticlesButton, publishArticlesButton);
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
     * showAlert method
     * 
     * <p> Displays an informational alert dialog with a given title and message. </p>
     * 
     * @param title The title of the alert dialog.
     * @param message The content message displayed in the alert dialog.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}