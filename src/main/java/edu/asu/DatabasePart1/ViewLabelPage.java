package edu.asu.DatabasePart1;

import java.sql.SQLException;
import java.util.List;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/*******
 * <p> ViewLabelPage Class </p>
 * 
 * <p> Description: The ViewLabelPage class provides a graphical user interface 
 *                  for viewing article details, displaying labels and corresponding
 *                  information in a structured layout. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-30 Initial implementation of the ViewLabelPage class.
 */

public class ViewLabelPage {
    
    private static ArticleDBHelper articleDBHelper = new ArticleDBHelper();

    public ViewLabelPage(Pane rootPane, SceneController sceneController) {
        // Set up the main layout as a GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.TOP_CENTER);

        // Page title
        Label pageTitle = new Label("View Article Details");
        pageTitle.setFont(Font.font("Arial", 24));
        grid.add(pageTitle, 0, 0, 2, 1);
        try {
        	articleDBHelper.connectToDatabase();
        	String[] article = articleDBHelper.viewArticle((long)sceneController.getData("uid")).get(0);
        	// Add labels and information fields
            addLabelAndInfo(grid, "Article Level:", article[1], 1);
            addLabelAndInfo(grid, "Title:", article[2], 2);
            addLabelAndInfo(grid, "Author:", article[3], 3);  // New Author Label
            addLabelAndInfo(grid, "Abstract:", article[4], 4);
            addLabelAndInfo(grid, "Keywords:", article[5], 5);
            addLabelAndInfo(grid, "Article Body:", article[6], 6);
            addLabelAndInfo(grid, "References:", article[7], 7);
            addLabelAndInfo(grid, "Grouping Identifiers:", article[8], 8);
            sceneController.removeData("uid");
        }
        catch (SQLException e) {
        	e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
        	articleDBHelper.closeConnection();
        }

        // Add the GridPane to the provided root Pane
        rootPane.getChildren().add(grid);
    }

    /**
     * Utility method to add a label and its corresponding information to the grid.
     *
     * @param grid   The grid pane to add elements to.
     * @param labelText The label text to display.
     * @param infoText  The information text to display next to the label.
     * @param row       The row in the grid for placement.
     */
    private void addLabelAndInfo(GridPane grid, String labelText, String infoText, int row) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 14));
        
        Label info = new Label(infoText);
        info.setFont(Font.font("Arial", 14));
        info.setWrapText(true);
        
        grid.add(label, 0, row);
        grid.add(info, 1, row);
    }
}
