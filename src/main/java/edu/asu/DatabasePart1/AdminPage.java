package edu.asu.DatabasePart1;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*******
 * <p> AdminPage Class </p>
 * 
 * <p> Description: The AdminPage class provides the graphical user interface for administrators,
 *                  allowing them to perform various administrative tasks. </p>
 * 
 * <p> Copyright: Arizona State University © 2024 </p>
 * 
 * @version 1.00   2024-10-09 Initial implementation of the AdminPage class
 */

public class AdminPage {

    private Label Title2 = new Label("Admin Page");
    private Label Invite = new Label("Invite a New User");
    private Button InviteAction = new Button("Invite a New User");
    private Label ListUsers = new Label("List Users");
    private Button ShowList = new Button("View User List");
    private Button viewArticlesButton = new Button("View Articles");
    private Button publishArticlesButton = new Button("Publish Articles");
    private Button BackupButton = new Button("Backup Articles");
    private Button RestoreBackupButton = new Button("Restore Articles");
    private Button CreateSpecialAccessGroupButton = new Button("Create Special Access Group Articles");
    private Button logoutButton = new Button("Logout");
    private Button ManageSAGPButton = new Button("Manage Special Access Group");
    private Button ManageGGPButton = new Button("Manage General Group");
    private Button addStudent = new Button("Add Student to System");
    private Button removeStudent = new Button("Remove Student from System");

    private static ArticleDBHelper articleDBHelper = new ArticleDBHelper();
    private static DatabaseHelper databaseHelper = new DatabaseHelper();

    public AdminPage(Pane Root, SceneController sceneController) {
        // Set title font
        Font fontTitle = Font.font("Arial", FontWeight.BOLD, 25);
        Title2.setFont(fontTitle);

        // Set fonts for Invite and ListUsers labels
        Font fontInvite = Font.font("Arial", FontWeight.BOLD, 14);
        Invite.setFont(fontInvite);

        Font fontList = Font.font("Arial", FontWeight.BOLD, 14);
        ListUsers.setFont(fontList);

        // Set button actions
        InviteAction.setOnAction(e -> sceneController.switchTo("Invite"));
        ShowList.setOnAction(e -> sceneController.switchTo("UserList"));
        viewArticlesButton.setOnAction(e -> sceneController.switchTo("ListArticle"));
        publishArticlesButton.setOnAction(e -> sceneController.switchTo("CreateArticle"));
        
        BackupButton.setOnAction(e -> {
            ArticleDBHelper helper = new ArticleDBHelper();
            try {
                helper.connectToDatabase();
                helper.createBackup("ArticleBackup.txt");
                showAlert("Backup Successful:", "Articles have been backed up to ArticleBackup.txt file");
            } catch(Exception ex) {
                showAlert("Backup Failed:", "Articles could not be backed up to ArticleBackup.txt file");
                ex.printStackTrace();
            } finally {
                helper.closeConnection();
            }
        });

        RestoreBackupButton.setOnAction(e -> {
            ArticleDBHelper helper1 = new ArticleDBHelper();
            try {
                helper1.connectToDatabase();
                helper1.restoreBackup("ArticleBackup.txt");
                showAlert("Restore Success:", "Articles have been restored from ArticleBackup.txt");
            } catch(Exception ex) {
                showAlert("Restore Failed:", "Articles could not be restored from ArticleBackup.txt");
                ex.printStackTrace();
            } finally {
                helper1.closeConnection();
            }
        });

        CreateSpecialAccessGroupButton.setOnAction(e -> sceneController.switchTo("CreateSpecialAccessGroup"));
        addStudent.setOnAction(e -> sceneController.switchTo("ManageStudentRole"));
        removeStudent.setOnAction(e -> sceneController.switchTo("ManageStudentRole"));
        ManageSAGPButton.setOnAction(e -> sceneController.switchTo("ManageSpecialAccessGroup"));
        ManageGGPButton.setOnAction(e -> sceneController.switchTo("ManageGeneralGroup"));

        logoutButton.setOnAction(event -> sceneController.exit());

        // Create a VBox to align all elements vertically
        VBox vbox = new VBox(15); // 15 px spacing
        vbox.setAlignment(Pos.CENTER);

        // Add all elements to the VBox in the desired order
        vbox.getChildren().addAll(
            Title2,
            //Invite,
            InviteAction,
            //ListUsers, 
            ShowList,
            viewArticlesButton,
            publishArticlesButton,
            BackupButton,
            RestoreBackupButton,
            CreateSpecialAccessGroupButton,
            addStudent,
            removeStudent,
            ManageSAGPButton,
            ManageGGPButton,
            logoutButton
        );

        vbox.setLayoutX(150); 
        vbox.setLayoutY(50);

        // Add the VBox to the root pane
        Root.getChildren().add(vbox);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
