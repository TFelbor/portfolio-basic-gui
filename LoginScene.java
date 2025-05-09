// Author: Tytus Felbor
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class LoginScene extends SceneTemplate{

    // Static fields for admin and user credentials
	private static String adminUsername = "admin";
	private static String adminPassword = "adminadmin";
	private static String userName = "user";
	private static String userPassword = "user123";
    
    // Text field for username input
    protected static TextField usernameField;
    // Password field for password input
    protected static PasswordField passwordField;
    
    
    // Constructor to initialize the login scene
    public LoginScene() {
        // Set the title and message of the scene
        setSceneTitle("\n Welcome");
        setMessage("Login using appropriate credentials \n");
        
        // Create an HBox layout for username input
        HBox usernameInput = new HBox();
        usernameInput.setSpacing(5);
        Label usernameLabel = new Label("Username:");
        // Create a text field for username input
        usernameField = new TextField();
        usernameInput.getChildren().addAll(usernameLabel, usernameField);
        
        // Create a label and password field for password input
        Label password = new Label("Password:");
        passwordField = new PasswordField();
        HBox passwordInput = new HBox();
        passwordInput.setSpacing(5);
        passwordInput.getChildren().addAll(password, passwordField);
        
        // Create a button for login
        Button logIn = new Button("Login");
        // Action for login button to authenticate credentials and switch to home scene
        logIn.setOnAction(e -> {
            if (usernameField.getText().equals(adminUsername)) {
                if (passwordField.getText().equals(adminPassword)) {
                    SceneManager.setScene(SceneManager.SceneType.Home);
                }
                else {
                    setMessage("Wrong password. Try again");
                }
            }
            else if (usernameField.getText().equals(userName)) {
            	if (passwordField.getText().equals(userPassword)) {
                    SceneManager.setScene(SceneManager.SceneType.Home);
                }
            	else {
                    setMessage("Wrong password. Try again");
                }
            }
            else {
                setMessage("Wrong username. Try again");
            }   
        });
        
        // Add username and password input layouts to the center layout
        center.getChildren().addAll(usernameInput, passwordInput);
        usernameInput.setAlignment(Pos.CENTER);
        passwordInput.setAlignment(Pos.CENTER);
        center.setSpacing(10);
        
        // Add login button to the bottom layout
        bottom.getChildren().add(logIn);
        bottom.setSpacing(10);
        
    }
    
}
