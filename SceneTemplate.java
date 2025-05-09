// Author: Tytus Felbor
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class SceneTemplate {

    // Fields to store scene components
    protected String title;
    protected Scene sc;
    protected BorderPane root;
    protected VBox center;
    protected VBox bottom;
    protected Label message;
    protected Label sceneTitle;
    
    // Constructor to initialize the scene template
    public SceneTemplate() {
        // Initialize root, center, and bottom layouts
        root = new BorderPane();
        center = new VBox();
        center.setAlignment(Pos.CENTER);
        message = new Label("Message is displayed here");

        bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(10);
        bottom.getChildren().add(message);
        sceneTitle = new Label("Scene Title is displayed here");
        
        // Set layout for root pane
        root.setCenter(center);
        root.setBottom(bottom);
        root.setTop(sceneTitle);
        root.setAlignment(message, Pos.CENTER);
        root.setAlignment(sceneTitle, Pos.CENTER);
        
        // Create a scene with the root pane
        sc = new Scene(root, 510, 350);
    }
    
    // Method to set the scene title
    public void setSceneTitle(String t) {
        this.sceneTitle.setText(t);
        this.sceneTitle.setFont(new Font(20));
        this.sceneTitle.setAlignment(Pos.CENTER);
    }
    
    // Method to set the message displayed on the scene
    public void setMessage(String m) {
        this.message.setText(m);
        this.message.setFont(new Font(12));
        this.message.setAlignment(Pos.CENTER);
    }
    
    // Method to get the scene
    public Scene getScene() {
        return this.sc;
    }
    
    
}
