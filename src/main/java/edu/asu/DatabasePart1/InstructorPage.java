package edu.asu.DatabasePart1;

import java.sql.SQLException;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    
    /** Button to publish articles */
    private Button BackUpArticlesButton = new Button("BackUp Articles");
    
    /** Button to publish articles */
    private Button restoreArticlesButton = new Button("Restore Articles");
    
    /** Button for Instructor to Add Student */
    private Button addStudent = new Button("Add Student to System");
    
    /** Button for Instructor to remove Student */
    private Button removeStudent = new Button("Remove Student from System");
    
    
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
        viewArticlesButton.setLayoutX(200);
        viewArticlesButton.setLayoutY(80);
        viewArticlesButton.setOnAction(e -> sceneController.switchTo("ListArticle"));

        // Set up the Publish Articles button
        publishArticlesButton.setLayoutX(200);
        publishArticlesButton.setLayoutY(120);
        publishArticlesButton.setOnAction(e -> sceneController.switchTo("CreateArticle"));
        
        //Set up the Backup Articles button
        BackUpArticlesButton.setLayoutX(200);
        BackUpArticlesButton.setLayoutY(160);
        BackUpArticlesButton.setOnAction(e -> sceneController.switchTo("BackUpArticle"));
        BackUpArticlesButton.setOnAction(e -> {
        	ArticleDBHelper helper = new ArticleDBHelper();
        	try {
        		helper.connectToDatabase();
        		helper.createBackup("ArticleBackup.txt");
        		showAlert("Backup Successfull message: ", "Articles have been backed up to ArticleBackup.txt file");
 
        		
        	}catch(Exception ex) {
        		showAlert("Backup Failed message: ", "Articles could not be backed up to ArticleBackup.txt file");
        		ex.printStackTrace();
        	}finally {
        		helper.closeConnection();
        	}
        	
        });
        
        //Set up the Restore Articles button
        restoreArticlesButton.setLayoutX(200);
        restoreArticlesButton.setLayoutY(200);
        restoreArticlesButton.setOnAction(e -> sceneController.switchTo("RestoreArticle"));
        restoreArticlesButton.setOnAction(e -> {
        	ArticleDBHelper helper1 = new ArticleDBHelper();
        	try{
        		helper1.connectToDatabase();
        		helper1.restoreBackup("ArticleBackup.txt");
        		showAlert("Restore Success: ", "Articles have been restored from ArticleBackup.txt");
        	}catch(Exception ex){
        		showAlert("Restore Failed: ", "Articles could not be restored from ArticleBackup.txt");
        		ex.printStackTrace();
        		
        	}finally {
        		helper1.closeConnection();
        	}
        	
        });
        
        
      //Set up the addStudent button
        addStudent.setLayoutX(200);
        addStudent.setLayoutY(240);
        addStudent.setOnAction(e -> sceneController.switchTo("ManageStudentRole"));
      
        //Set up the removeStudent button
        removeStudent.setLayoutX(200);
        removeStudent.setLayoutY(280);
        removeStudent.setOnAction(e -> sceneController.switchTo("ManageStudentRole"));        
        
        // Add all components to the root pane
        root.getChildren().addAll(titleLabel, viewArticlesButton, publishArticlesButton, BackUpArticlesButton,restoreArticlesButton,
        		addStudent, removeStudent);
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
