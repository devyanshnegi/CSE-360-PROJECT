package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*******
 * <p> ViewArticlesPageApp Class </p>
 * 
 * <p> Description: The ViewArticlesPageApp class provides a graphical user interface 
 *                  for viewing articles by entering grouping identifiers to retrieve 
 *                  and display the article content. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-30 Initial implementation of the ViewArticlesPageApp class.
 */

public class ViewArticlesPageApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set up the main layout as a VBox
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        // Page title
        Label pageTitle = new Label("View Article");
        pageTitle.setFont(Font.font("Arial", 24));
        layout.getChildren().add(pageTitle);

        // Grouping Identifier Input
        Label groupingLabel = new Label("Enter Grouping Identifier:");
        TextField groupingField = new TextField();
        groupingField.setPromptText("Enter grouping identifier");
        layout.getChildren().addAll(groupingLabel, groupingField);

        // Article Display Area
        Label articleContentLabel = new Label("Article Content:");
        TextArea articleContentArea = new TextArea();
        articleContentArea.setEditable(false);
        articleContentArea.setWrapText(true);
        articleContentArea.setPrefHeight(300);
        layout.getChildren().addAll(articleContentLabel, articleContentArea);

        // View Article Button
        Button viewButton = new Button("View Article");
        viewButton.setOnAction(e -> handleViewArticle(groupingField.getText(), articleContentArea));
        layout.getChildren().add(viewButton);

        // Set up the scene and stage
        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setTitle("View Articles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Handle the View Article button action.
     * Simulates retrieving and displaying an article based on the grouping identifier.
     *
     * @param groupingIdentifier The grouping identifier entered by the user.
     * @param articleContentArea The TextArea where the article content will be displayed.
     */
    private void handleViewArticle(String groupingIdentifier, TextArea articleContentArea) {
        // For this example, simulate an article retrieval based on the grouping identifier.
        // Replace this with real data retrieval logic as needed.
        if (groupingIdentifier.isEmpty()) {
            articleContentArea.setText("Please enter a valid grouping identifier.");
        } else {
            // Simulated article content
            String articleContent = "Title: Example Article\n"
                    + "Level: Intermediate\n"
                    + "Description: This article is about example content.\n"
                    + "Keywords: Example, Sample, Test\n"
                    + "Body:\n"
                    + "This is the body of the article associated with the grouping identifier: " 
                    + groupingIdentifier + "\n"
                    + "References:\n- Reference 1\n- Reference 2";

            articleContentArea.setText(articleContent);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
