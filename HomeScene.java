// Author Tytus Felbor
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HomeScene extends SceneTemplate{

    // HBox layouts for different options
    private HBox loginScene;
    private HBox stocks;
    private HBox chat;
    private HBox calc;
    private HBox leave;
    
    // Constructor to initialize the home scene
    public HomeScene() {
        // Set the title and message of the scene
        setSceneTitle("\n Home");
        setMessage("Welcome to my Portfolio Application! "
                + "\n        Choose an option to explore :)");
        
        // Initialize HBox layout for logging out
        loginScene = new HBox();
        loginScene.setSpacing(5);
        Button logIn = new Button("Logout");
        // Action for logout button to clear username and password fields and go back to login scene
        logIn.setOnAction(e -> {    LoginScene.usernameField.clear();
                                    LoginScene.passwordField.clear();
                                    SceneManager.setScene(SceneManager.SceneType.Login); });
        Label logbio = new Label("Go back to the login screen");
        loginScene.getChildren().addAll(logIn, logbio);
        loginScene.setAlignment(Pos.CENTER);
        
        // Initialize HBox layout for checking stocks
        stocks = new HBox();
        stocks.setSpacing(5);
        Button stocksbtn = new Button("Stocks");
        // Action for stocks button to switch to stocks scene
        stocksbtn.setOnAction(e -> {SceneManager.setScene(SceneManager.SceneType.Stocks);});
        Label stocksbio = new Label("Check out current prices of your favorite stocks");
        stocks.getChildren().addAll(stocksbtn, stocksbio);
        stocks.setAlignment(Pos.CENTER);
        
        // Initialize HBox layout for accessing chat
        chat = new HBox();
        chat.setSpacing(5);
        Button chatbtn = new Button("Chat");
        // Action for chat button to switch to chat scene
        chatbtn.setOnAction(e -> {SceneManager.setScene(SceneManager.SceneType.Chat); });
        Label chatbio = new Label("Go to the chat screen and find out more about who I am");
        chat.getChildren().addAll(chatbtn, chatbio);
        chat.setAlignment(Pos.CENTER);
        
        // Initialize HBox layout for accessing calculator
        calc = new HBox();
        calc.setSpacing(5);
        Button calcbtn = new Button("Calculator");
        // Action for calculator button to switch to calculator scene
        calcbtn.setOnAction(e -> {SceneManager.setScene(SceneManager.SceneType.Calculator);});
        Label calcbio = new Label("Want to calculate something real quick?");
        calc.getChildren().addAll(calcbtn, calcbio);
        calc.setAlignment(Pos.CENTER);
        
        // Initialize HBox layout for exiting application
        leave = new HBox();
        leave.setSpacing(5);
        Button disconnect = new Button("Exit");
        // Action for exit button to terminate the application
        disconnect.setOnAction(e -> { Platform.exit();});
        Label leaveLabel = new Label("If done, press to quit");
        leave.getChildren().addAll(disconnect, leaveLabel);
        leave.setAlignment(Pos.CENTER);
        
        // Add all HBox layouts to the center layout
        center.getChildren().addAll(chat, stocks, calc, loginScene, leave);
        center.setSpacing(10);
        // Set padding for the center layout
        center.setPadding(new Insets(0, 20, 10, 20));
    }

}
