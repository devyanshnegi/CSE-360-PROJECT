package edu.asu.DatabasePart1;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

    /** Buttons */
    private Button viewArticlesButton = new Button("View Articles");
    private Button publishArticlesButton = new Button("Publish Articles");
    private Button ManageSAGPButton = new Button("Manage Special Access Group");
    private Button ManageGGPButton = new Button("Manage General Group");
    private Button BackUpArticlesButton = new Button("BackUp Articles");
    private Button restoreArticlesButton = new Button("Restore Articles");
    private Button addStudent = new Button("Add Student to System");
    private Button removeStudent = new Button("Remove Student from System");
    private Button viewMessage = new Button("View Messages from Students");
    private Button logOutButton = new Button("Logout");

    private static ArticleDBHelper articleDBHelper = new ArticleDBHelper();

    public InstructorPage(Pane root, SceneController sceneController) {
        // Setup title label font
        Font fontTitle = Font.font("Arial", FontWeight.BOLD, 24);
        titleLabel.setFont(fontTitle);

        // Set button actions
        viewArticlesButton.setOnAction(e -> sceneController.switchTo("ListArticle"));
        publishArticlesButton.setOnAction(e -> sceneController.switchTo("CreateArticle"));
        BackUpArticlesButton.setOnAction(e -> {
            ArticleDBHelper helper = new ArticleDBHelper();
            try {
                helper.connectToDatabase();
                helper.createBackup("ArticleBackup.txt");
                showAlert("Backup Successful:", "Articles have been backed up to ArticleBackup.txt file");
            } catch (Exception ex) {
                showAlert("Backup Failed:", "Articles could not be backed up to ArticleBackup.txt file");
                ex.printStackTrace();
            } finally {
                helper.closeConnection();
            }
        });

        restoreArticlesButton.setOnAction(e -> {
            ArticleDBHelper helper1 = new ArticleDBHelper();
            try {
                helper1.connectToDatabase();
                helper1.restoreBackup("ArticleBackup.txt");
                showAlert("Restore Success:", "Articles have been restored from ArticleBackup.txt");
            } catch (Exception ex) {
                showAlert("Restore Failed:", "Articles could not be restored from ArticleBackup.txt");
                ex.printStackTrace();
            } finally {
                helper1.closeConnection();
            }
        });

        addStudent.setOnAction(e -> sceneController.switchTo("ManageStudentRole"));
        removeStudent.setOnAction(e -> sceneController.switchTo("ManageStudentRole"));
        viewMessage.setOnAction(e -> sceneController.switchTo("MessagesView"));
        ManageSAGPButton.setOnAction(e -> sceneController.switchTo("ManageSpecialAccessGroup"));
        ManageGGPButton.setOnAction(e -> sceneController.switchTo("ManageGeneralGroup"));

        logOutButton.setOnAction(event -> {
            System.out.println("Logout button clicked. Closing the application...");
            Platform.exit(); // Gracefully close the application
        });

        // Create a VBox to hold all components in a vertical layout
        VBox vbox = new VBox(15); // 15 px spacing between elements
        vbox.setAlignment(Pos.CENTER);

        // Add all components to the VBox
        vbox.getChildren().addAll(
            titleLabel,
            viewArticlesButton,
            publishArticlesButton,
//            BackUpArticlesButton,
//            restoreArticlesButton,
            addStudent,
            removeStudent,
            viewMessage,
            ManageSAGPButton,
            ManageGGPButton,
            logOutButton
        );

        // Adjust VBox size/position as needed
        vbox.setLayoutX(150); 
        vbox.setLayoutY(50);

        // Add the VBox to the root pane
        root.getChildren().add(vbox);
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
