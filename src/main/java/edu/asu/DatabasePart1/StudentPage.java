package edu.asu.DatabasePart1;

// JavaFX imports needed to support the Graphical User Interface
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.application.Platform;

/*******
 * <p> StudentPage Class </p>
 * 
 * <p> Description: StudentPage provides the graphical user interface for the student page of the 
 *                  application. It displays a title and a logout button that allows users to 
 *                  exit the application. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 2.01   2024-11-19 Removed the logout label and simplified the layout.
 */

public class StudentPage {

    private Label Title2 = new Label("Student Home Page");
    
    /** Label prompting the user to click the logout button */
    private Label logOut = new Label("Click Here to LogOut");
    
    /** Button for logging out of the application */
    private Button logOutButton = new Button("Logout");
    
    /** Button for logging out of the application */
    private Button helpMessage = new Button("Send Help Messagw to Instructor");
    
    /** Button for searching Article */
    private Button search = new Button("Search Articles using keywords");
    /** VBox layout for the StudentPage */
    private VBox root;


    /** Constructor for StudentPage
     * 
     * <p> Sets up the user interface layout for the student page, including the title label 
     *     and the logout button. The constructor also adds an action to the 
     *     logout button to close the application when clicked. </p>
     * 
     * @param sceneController The SceneController instance for scene switching.
     */
    public StudentPage(Pane parent, SceneController sceneController) {
        // Initialize the VBox layout
        root = new VBox();
        root.setSpacing(20); // Set spacing between elements
        root.setAlignment(Pos.CENTER); // Center align all elements
        root.setPrefSize(MainApp.WINDOW_WIDTH, MainApp.WINDOW_HEIGHT);

        Font fontlogout = Font.font("Arial", FontWeight.BOLD, 14);
        logOut.setFont(fontlogout);
        
        
        //Setup the send message button
        helpMessage.setLayoutX(215);
        helpMessage.setLayoutY(140);
        helpMessage.setOnAction(e -> sceneController.switchTo("HelpMessage"));
        // Title label
        Label titleLabel = new Label("Home Page");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        root.getChildren().add(titleLabel);

        Button viewArticles = new Button("View Articles");
        viewArticles.setOnAction(e -> sceneController.switchTo("ListArticle"));
        
        // Logout button
        Button logOutButton = new Button("Logout");
        logOutButton.setOnAction(event -> {
            System.out.println("Logout button clicked. Closing the application...");
            Platform.exit(); // Gracefully close the application
        });
        
        //setup search button
        search.setLayoutX(215);
        search.setLayoutY(180);
        search.setOnAction(e -> sceneController.switchTo("searchArticles"));
        
        root.getChildren().add(logOutButton);
        root.getChildren().add(viewArticles);
  
        root.getChildren().add(helpMessage);
        root.getChildren().add(search);

        // Add the VBox layout to the parent Pane
        parent.getChildren().add(root);
    }
}
