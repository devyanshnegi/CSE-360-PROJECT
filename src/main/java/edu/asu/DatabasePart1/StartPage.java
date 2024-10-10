//package edu.asu.DatabasePart1;                                                                                     
//                                                                                                                   
//import javafx.geometry.Pos;                                                                                        
//import javafx.scene.control.Label;                                                                                 
//import javafx.scene.control.TextField;                                                                             
//import javafx.scene.layout.Pane;                                                                                   
//import javafx.scene.paint.Color;                                                                                   
//import javafx.scene.text.Font;                                                                                     
//import javafx.scene.text.FontWeight;                                                                               
//import javafx.scene.control.Button;                                                                                
//                                                                                                                   
//public class StartPage {                                                                                           
//                                                                                                                   
//    private Label Title = new Label("Login or Enter Invitation Code");                                             
//    private Label Invite = new Label("Enter the Invitation Code Here:");                                           
//    private TextField Invite_Code = new TextField();                                                               
//    private Label noCode = new Label("");                                                                          
//    private Button InviteCodeEnter = new Button("Enter Code");                                                     
//                                                                                                                   
//    private Label loginOption = new Label("Choose to Login:");                                                     
//    private Button loginRedirect = new Button("Login");                                                            
//                                                                                                                   
//    public StartPage(Pane Root) {                                                                                  
//        // Label theScene with the name of the testbed, centered at the top of the pane                            
//        setupLabelUI(Title, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);                                
//                                                                                                                   
//        // Enhancements to the Title of the Page                                                                   
//        Font font = Font.font("Arial", FontWeight.BOLD, 25);                                                       
//        Title.setFont(font);                                                                                       
//                                                                                                                   
//        // Label the password input field with a title just above it, left aligned                                 
//        setupLabelUI(Invite, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 70);                  
//                                                                                                                   
//        // Enhancements to the label Text                                                                          
//        Font font1 = Font.font("Arial", FontWeight.BOLD, 14);                                                      
//        Invite.setFont(font1);                                                                                     
//                                                                                                                   
//        // placeholder text for text field                                                                         
//        Invite_Code.setPromptText("Enter Code provided by Admin");                                                 
//                                                                                                                   
//        // Set up the invitation code text field                                                                   
//        setupTextUI(Invite_Code, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 100, true);       
//                                                                                                                   
//        // Establish an error message for the case where there is no input                                         
//        noCode.setTextFill(Color.RED);                                                                             
//        noCode.setAlignment(Pos.BASELINE_LEFT);                                                                    
//        setupLabelUI(noCode, "Arial", 16, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 120);                 
//                                                                                                                   
//        // Set the layout of the invitation code button                                                            
//        InviteCodeEnter.setLayoutX(200);                                                                           
//        InviteCodeEnter.setLayoutY(150);                                                                           
//                                                                                                                   
//        // Label the login option                                                                                  
//        setupLabelUI(loginOption, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 200);            
//        Font font2 = Font.font("Arial", FontWeight.BOLD, 14);                                                      
//        loginOption.setFont(font2);                                                                                
//                                                                                                                   
//        // Set the layout of the login button                                                                      
//        loginRedirect.setLayoutX(210);                                                                             
//        loginRedirect.setLayoutY(240);                                                                             
//                                                                                                                   
//        // Add all elements to the scene                                                                           
//        Root.getChildren().addAll(Title, Invite, Invite_Code, noCode, InviteCodeEnter, loginOption, loginRedirect);
//    }                                                                                                              
//                                                                                                                   
//    // Method to return the invitation button                                                                      
//    public Button InviteButton() {                                                                                 
//        return InviteCodeEnter;                                                                                    
//    }                                                                                                              
//                                                                                                                   
//    // Method to return the login button (used in MainPage to switch scenes)                                       
//    public Button LoginButton() {                                                                                  
//        return loginRedirect;                                                                                      
//    }                                                                                                              
//                                                                                                                   
//    // Private local method to initialize the standard fields for a label                                          
//    private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {                 
//        l.setFont(Font.font(ff, f));                                                                               
//        l.setMinWidth(w);                                                                                          
//        l.setAlignment(p);                                                                                         
//        l.setLayoutX(x);                                                                                           
//        l.setLayoutY(y);                                                                                           
//    }                                                                                                              
//                                                                                                                   
//    // Private local method to initialize the standard fields for a text field                                     
//    private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {   
//        t.setFont(Font.font(ff, f));                                                                               
//        t.setMinWidth(w);                                                                                          
//        t.setMaxWidth(w);                                                                                          
//        t.setAlignment(p);                                                                                         
//        t.setLayoutX(x);                                                                                           
//        t.setLayoutY(y);                                                                                           
//        t.setEditable(e);                                                                                          
//    }                                                                                                              
//}                                                                                                                  
//                                    

package edu.asu.DatabasePart1;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPage {

    private Label Title = new Label("Login or Enter Invitation Code");
    private Label Invite = new Label("Enter the Invitation Code Here:");
    private TextField Invite_Code = new TextField();
    private Label noCode = new Label("");
    private Button InviteCodeEnter = new Button("Enter Code");

    private Label loginOption = new Label("Choose to Login:");
    private Button loginRedirect = new Button("Login");

    // Constructor takes the Pane, Stage, and RegisterPage scene as parameters
    public StartPage(Pane Root, Stage theStage, Scene registerScene) {
        // Label the Scene with the name of the testbed, centered at the top of the pane
        setupLabelUI(Title, "Arial", 24, MainPage.WINDOW_WIDTH, Pos.CENTER, 0, 10);

        // Enhancements to the Title of the Page
        Font font = Font.font("Arial", FontWeight.BOLD, 25);
        Title.setFont(font);

        // Label the password input field with a title just above it, left aligned
        setupLabelUI(Invite, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 70);

        // Enhancements to the label Text
        Font font1 = Font.font("Arial", FontWeight.BOLD, 14);
        Invite.setFont(font1);

        // Placeholder text for the invitation code text field
        Invite_Code.setPromptText("Enter Code provided by Admin");

        // Set up the invitation code text field
        setupTextUI(Invite_Code, "Arial", 18, MainPage.WINDOW_WIDTH - 20, Pos.BASELINE_LEFT, 10, 100, true);

        // Establish an error message for the case where there is no input
        noCode.setTextFill(Color.RED);
        noCode.setAlignment(Pos.BASELINE_LEFT);
        setupLabelUI(noCode, "Arial", 16, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 120);

        // Set up the layout of the button
        InviteCodeEnter.setLayoutX(200);
        InviteCodeEnter.setLayoutY(150);

        // Label the login option
        setupLabelUI(loginOption, "Arial", 14, MainPage.WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, 10, 200);
        Font font2 = Font.font("Arial", FontWeight.BOLD, 14);
        loginOption.setFont(font2);

        // Set up the login button onto the scene
        loginRedirect.setLayoutX(210);
        loginRedirect.setLayoutY(240);

        // Add all elements to the scene
        Root.getChildren().addAll(Title, Invite, Invite_Code, noCode, InviteCodeEnter, loginOption, loginRedirect);

        // Add functionality for the Enter Code button to switch to RegisterPage
        InviteCodeEnter.setOnAction(event -> {
            String inviteCode = Invite_Code.getText();
            if (!inviteCode.isEmpty()) {
                // Switch to the RegisterPage
                theStage.setScene(registerScene);
            } else {
                // Display an error message if no code is entered
                noCode.setText("Please enter a valid invitation code.");
            }
        });
    }

    // Private local method to initialize the standard fields for a label
    public Button InviteButton() {
        return InviteCodeEnter;
    }

    public Button LoginButton() {
        return loginRedirect;
    }

    private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
        l.setFont(Font.font(ff, f));
        l.setMinWidth(w);
        l.setAlignment(p);
        l.setLayoutX(x);
        l.setLayoutY(y);
    }

    // Private local method to initialize the standard fields for a text
    private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
        t.setFont(Font.font(ff, f));
        t.setMinWidth(w);
        t.setMaxWidth(w);
        t.setAlignment(p);
        t.setLayoutX(x);
        t.setLayoutY(y);
        t.setEditable(e);
    }
}
