// Author: Tytus Felbor
import javafx.application.Application;
import javafx.stage.Stage;

// Import necessary JavaFX classes for GUI application

public class AppRunner extends Application{

	private SceneManager manager;
	
	public AppRunner() {
		// Initialize SceneManager to manage scenes in the application
		manager = new SceneManager();
	}
	
	public void start(Stage st) throws Exception {
		// Entry point of the JavaFX application
		
		// Create a new instance of AppRunner.
		AppRunner starter = new AppRunner();
		
		// Set the stage managed by the SceneManager
		starter.manager.setStage(st);
		
		// Set the initial scene of the stage to the Login scene
		st.setScene(starter.manager.scenes.get(SceneManager.SceneType.Login).getScene());
		
		// Set the title of the stage
		st.setTitle("Tytus's Portfolio App");
		
		// Display the stage
		st.show();
	}
	
	public static void main(String[] args) {
		// Main method to launch the JavaFX application
		launch();
	}
}
