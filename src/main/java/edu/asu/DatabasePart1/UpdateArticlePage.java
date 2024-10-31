package edu.asu.DatabasePart1;

import javafx.scene.control.*;

import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/*******
 * <p> CreateArticlePage Class </p>
 * 
 * <p> Description: The CreateArticlePage class provides a graphical user interface 
 *                  for creating a new article, including fields for level, identifiers, 
 *                  title, description, keywords, body, references, and other required 
 *                  information. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-30 Initial implementation of the CreateArticlePage class.
 */

public class UpdateArticlePage {

    private static ArticleDBHelper articleDBHelper = new ArticleDBHelper();

    // Fields for the article data
    private ComboBox<String> levelComboBox;
    private TextField groupingField;
    private TextField authorField; // New field for author name
    private TextField titleField;
    private TextArea descriptionArea;
    private TextField keywordsField;
    private TextArea bodyArea;
    private TextArea referencesArea;

    public UpdateArticlePage(Pane rootPane, SceneController sceneController) {
        // Set up the main layout as a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Add title for the Create Article page
        Label pageTitle = new Label("Create New Article");
        pageTitle.setFont(Font.font("Arial", 24));
        grid.add(pageTitle, 0, 0, 2, 1);

        // Level of the article
        Label levelLabel = new Label("Article Level:");
        levelComboBox = new ComboBox<>();
        levelComboBox.getItems().addAll("Beginner", "Intermediate", "Advanced", "Expert");
        grid.add(levelLabel, 0, 1);
        grid.add(levelComboBox, 1, 1);

        // Grouping Identifiers
        Label groupingLabel = new Label("Grouping Identifiers:");
        groupingField = new TextField();
        grid.add(groupingLabel, 0, 2);
        grid.add(groupingField, 1, 2);

        // Author Name
        Label authorLabel = new Label("Author:");
        authorField = new TextField();
        grid.add(authorLabel, 0, 3);
        grid.add(authorField, 1, 3);

        // Title
        Label titleLabel = new Label("Title:");
        titleField = new TextField();
        grid.add(titleLabel, 0, 4);
        grid.add(titleField, 1, 4);

        // Short Description
        Label descriptionLabel = new Label("Abstract:");
        descriptionArea = new TextArea();
        descriptionArea.setWrapText(true);
        descriptionArea.setPrefHeight(60);
        grid.add(descriptionLabel, 0, 5);
        grid.add(descriptionArea, 1, 5);

        // Keywords
        Label keywordsLabel = new Label("Keywords:");
        keywordsField = new TextField();
        grid.add(keywordsLabel, 0, 6);
        grid.add(keywordsField, 1, 6);

        // Body of the article
        Label bodyLabel = new Label("Article Body:");
        bodyArea = new TextArea();
        bodyArea.setWrapText(true);
        bodyArea.setPrefHeight(200);
        grid.add(bodyLabel, 0, 7);
        grid.add(bodyArea, 1, 7);

        // References
        Label referencesLabel = new Label("References:");
        referencesArea = new TextArea();
        referencesArea.setWrapText(true);
        referencesArea.setPrefHeight(60);
        grid.add(referencesLabel, 0, 8);
        grid.add(referencesArea, 1, 8);

        // Submit button
        Button submitButton = new Button("Submit Article");
        submitButton.setOnAction(e -> handleSubmitAction(sceneController));
        grid.add(submitButton, 1, 9);
        GridPane.setMargin(submitButton, new Insets(10, 0, 0, 0));

        try {
        	articleDBHelper.connectToDatabase();
        	String[] article = articleDBHelper.viewArticle((long)sceneController.getData("uid")).get(0);
        	// Add labels and information fields
        	groupingField.setText(article[2]);
        	authorField.setText(article[3]);  // New Author Label
        	titleField.setText(article[4]);
        	descriptionArea.setText(article[5]);
        	keywordsField.setText(article[6]);
        	bodyArea.setText(article[7]);
        	referencesArea.setText(article[8]);
        }
        catch (SQLException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	articleDBHelper.closeConnection();
        }
        
        // Add the grid to the root pane
        rootPane.getChildren().add(grid);
    }

    /**
     * Handle the submit button action.
     * This method collects data from form fields and saves the article to the database.
     */
    private void handleSubmitAction(SceneController sceneController) {
        // Collect data from input fields
        String level = levelComboBox.getValue();
        String grouping = groupingField.getText();
        String author = authorField.getText();
        String title = titleField.getText();
        String description = descriptionArea.getText();
        String keywords = keywordsField.getText();
        String body = bodyArea.getText();
        String references = referencesArea.getText();

        // Validate required fields
        if (title.isEmpty() || body.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Title and Body are required fields.");
            return;
        }

        try {
            // Store the article in the database
        	articleDBHelper.connectToDatabase();
            articleDBHelper.updateArticle((long)sceneController.getData("uid"), level, title, author, description, keywords, body, references, grouping);
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to store the article in the database.");
        } finally {
        	articleDBHelper.closeConnection();
        }
        sceneController.switchTo("ListArticle");
    }

    /**
     * Shows an alert dialog with a specified type, title, and message.
     *
     * @param alertType The type of alert.
     * @param title     The title of the alert dialog.
     * @param message   The content message of the alert.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
