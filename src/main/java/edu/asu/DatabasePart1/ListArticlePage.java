package edu.asu.DatabasePart1;

import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/*******
 * <p> ListArticlesPage Class </p>
 * 
 * <p> Description: The ListArticlesPage class provides a graphical user interface 
 *                  for viewing, updating, and deleting articles based on grouping identifiers.
 *                  Displays all articles if no identifier is entered, with radio buttons to 
 *                  select specific articles. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.04   2024-10-30 Updated to add a View Article button with no functionality.
 */

public class ListArticlePage {

    private VBox articlesContainer;
    private ToggleGroup articleToggleGroup;
    private static ArticleDBHelper articleDBHelper = new ArticleDBHelper();
    private Map<String, Long> UIDdb = new HashMap<>();

    // Updated constructor
    public ListArticlePage(Pane root, SceneController sceneController) {
        // Main layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        // Page title
        Label pageTitle = new Label("View Articles");
        pageTitle.setFont(Font.font("Arial", 24));
        layout.getChildren().add(pageTitle);

        // Grouping Identifier Input
        HBox identifierBox = new HBox(5);
        identifierBox.setAlignment(Pos.CENTER_LEFT);

        Label groupingLabel = new Label("Grouping Identifier:");
        TextField groupingField = new TextField();
        groupingField.setPromptText("Enter grouping identifier");
        
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> handleSubmit(groupingField.getText())); // Displays articles on submit

        identifierBox.getChildren().addAll(groupingLabel, groupingField, submitButton);
        layout.getChildren().add(identifierBox);

        // Article Display Area with Radio Buttons
        articlesContainer = new VBox(10);
        articlesContainer.setPadding(new Insets(10, 0, 0, 0));
        articleToggleGroup = new ToggleGroup();
        layout.getChildren().add(articlesContainer);

        // Action Buttons (View, Update, Delete)
        HBox actionButtonsBox = new HBox(10);
        actionButtonsBox.setAlignment(Pos.CENTER);

        Button viewButton = new Button("View Article");  // View button with no functionality yet
        Button updateButton = new Button("Update Article");
        Button deleteButton = new Button("Delete Article");

        // Set button actions for Update and Delete
        viewButton.setOnAction(e -> handleViewArticle(sceneController));
        updateButton.setOnAction(e -> handleUpdateArticle(sceneController));
        deleteButton.setOnAction(e -> handleDeleteArticle(sceneController));

        actionButtonsBox.getChildren().addAll(viewButton, updateButton, deleteButton);
        layout.getChildren().add(actionButtonsBox);

        // Add the layout to the root pane
        root.getChildren().add(layout);
    }

    /**
     * Handle the Submit button action.
     * Retrieves and displays articles based on the grouping identifier.
     * If no identifier is provided, displays all articles with radio buttons.
     *
     * @param groupingIdentifier The grouping identifier entered by the user.
     */
    private void handleSubmit(String groupingIdentifier) {
        articlesContainer.getChildren().clear();
        articleToggleGroup.getToggles().clear();
        try {
        	articleDBHelper.connectToDatabase();
	        if (groupingIdentifier.isEmpty()) {
	        	displayArticles(articleDBHelper.listArticlesByGroups("%"));
	        } else {
	        	displayArticles(articleDBHelper.listArticlesByGroups(groupingIdentifier));
	        }
        }
        catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	articleDBHelper.closeConnection();
        }
    }

    /**
     * Display articles in the articles container with radio buttons.
     *
     * @param articles Array of article descriptions to display.
     */
    private void displayArticles(List<String[]> articles) {
        for (String[] article : articles) {
        	String s = String.format("Level: %s, Title: %s, Author(s): %s, Group(s): %s", article[1],article[2],article[3],article[4]);
        	UIDdb.put(s, Long.parseLong(article[0]));
        	
            RadioButton radioButton = new RadioButton(s);
            radioButton.setToggleGroup(articleToggleGroup);
            articlesContainer.getChildren().add(radioButton);
        }
    }
    
    private void handleViewArticle(SceneController sceneController) {
        RadioButton selectedRadioButton = (RadioButton) articleToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            String selectedArticle = selectedRadioButton.getText();
            sceneController.setData("uid", UIDdb.get(selectedArticle));
        } else {
            showAlert("View Article", "Please select an article to view.");
        }
    }

    /**
     * Handle the Update Article button action.
     * Opens the selected article in an editable format for updating.
     */
    private void handleUpdateArticle(SceneController sceneController) {
        RadioButton selectedRadioButton = (RadioButton) articleToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            String selectedArticle = selectedRadioButton.getText();
            sceneController.setData("uid", UIDdb.get(selectedArticle));
        } else {
            showAlert("Update Article", "Please select an article to update.");
        }
    }

    /**
     * Handle the Delete Article button action.
     * Deletes the selected article.
     */
    private void handleDeleteArticle(SceneController sceneController) {
        RadioButton selectedRadioButton = (RadioButton) articleToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            String selectedArticle = selectedRadioButton.getText();
            try {
            	articleDBHelper.connectToDatabase();
            	articleDBHelper.deleteArticle(UIDdb.get(selectedArticle));
            }
            catch (SQLException e) {
            	e.printStackTrace();
            } catch (Exception e) {
				e.printStackTrace();
			} finally {
            	articleDBHelper.closeConnection();
            }
            showAlert("Delete Article", "Article '" + selectedArticle + "' has been deleted.");
            articlesContainer.getChildren().remove(selectedRadioButton); // Remove deleted article from display
        } else {
            showAlert("Delete Article", "Please select an article to delete.");
        }
    }

    /**
     * Display an alert dialog.
     *
     * @param title   The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}