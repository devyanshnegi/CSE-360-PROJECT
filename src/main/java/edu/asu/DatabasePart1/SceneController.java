package edu.asu.DatabasePart1;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Map<String, Scene> scenes = new HashMap<>();
    
    public SceneController(Stage stage) {
        this.stage = stage;
    }

    // Method to add a new scene
    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }
    
    // Method to switch scenes
    public void switchTo(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            stage.setScene(scene);
        } else {
            System.out.println("Scene not found: " + name);
        }
    }
    
    public void exit() {
    	System.out.println("Closing application.");
        stage.close(); // Close the stage
    }
}
