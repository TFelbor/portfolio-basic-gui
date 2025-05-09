// Author: Tytus Felbor
import java.util.HashMap;
import javafx.stage.Stage;

public class SceneManager {

    // Enum to represent different types of scenes
	protected static enum SceneType {Login, Home, Chat, Calculator, Stocks};
    // HashMap to store scenes
	protected static HashMap<SceneType, SceneTemplate> scenes = new HashMap<SceneType, SceneTemplate>();
    // Stage to display scenes
	protected static Stage stage;

    // Constructor to initialize scenes
    public SceneManager() {
        scenes.put(SceneType.Login, new LoginScene());
        scenes.put(SceneType.Home, new HomeScene());
        scenes.put(SceneType.Chat, new ChatScene());
        scenes.put(SceneType.Calculator, new CalculatorScene());
        scenes.put(SceneType.Stocks, new StockScene());
    }

    // Set the stage to be used by all scenes
    public static void setStage(Stage st) {
        stage = st;
        st.setResizable(false); // Make the stage not resizable
    }

    // Method to switch the view to the selected scene
    public static void setScene(SceneType type) {
        stage.setScene(scenes.get(type).getScene()); // Switch to the selected scene
    }
}
