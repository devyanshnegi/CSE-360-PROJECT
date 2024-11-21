package edu.asu.DatabasePart1;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import java.util.List;

/*******
 * <p> ArticleSearchPage Class </p>
 * 
 * <p> Description: The ArticleSearchPage class allows the user to search for articles
 *                  by a keyword and displays the results in the GUI. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-11-20 Initial implementation of the ArticleSearchPage class.
 */
public class searchArticles {

    private TextField keywordField;
    private ListView<String> resultList;

    /**
     * Constructor to create and display the Article Search pop-up window.
     *
     * @param root              The pane to be used to hold the scene.
     * @param sceneController   The scene controller to manage scene transitions.
     */
    public searchArticles(Pane root, SceneController sceneController) {
        // Create a new stage for the popup
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Search Articles");

        // Main layout as a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Page title
        Label titleLabel = new Label("Search Articles by Keyword");
        titleLabel.setFont(Font.font("Arial", 18));
        grid.add(titleLabel, 0, 0, 2, 1);

        // Keyword field
        Label keywordLabel = new Label("Enter Keyword: ");
        keywordField = new TextField();
        keywordField.setPromptText("Enter keyword to search");
        grid.add(keywordLabel, 0, 1);
        grid.add(keywordField, 1, 1);

        // Search Button to trigger the search
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearchAction(popupStage));
        grid.add(searchButton, 1, 2);

        // Result ListView to display the search results
        resultList = new ListView<>();
        grid.add(resultList, 0, 3, 2, 1);

        // Back Button to go back to the previous page
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> sceneController.switchTo("StudentHome"));
        grid.add(backButton, 1, 4);

        // Add the grid to the root pane
        root.getChildren().addAll(grid);
    }

    /**
     * Handles the search action when the "Search" button is clicked.
     *
     * @param popupStage       The stage for the popup window.
     */
    private void handleSearchAction(Stage popupStage) {
        String keyword = keywordField.getText();

        try {
            // Create an instance of DatabaseHelper to call the method
            DatabaseHelper dbHelper = new DatabaseHelper();
            dbHelper.connectToDatabase();
            List<String[]> articles = dbHelper.listArticlesByKeyword(keyword);

            // Clear previous results
            resultList.getItems().clear();

            // Display the results in the ListView
            for (String[] article : articles) {
                String result = "Title: " + article[2] + ", Author(s): " + article[3];
                resultList.getItems().add(result);
            }

            if (articles.isEmpty()) {
                resultList.getItems().add("No articles found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultList.getItems().add("Error fetching articles.");
        }
    }
}
