// Author: Tytus Felbor
import java.util.HashMap;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ChatScene extends SceneTemplate{

    private static HashMap<String, String> QandA;
    private static ScrollPane scroll;
    private static HBox buttons;

    // Method to add chat messages to the scene
    private void addText(String person, String question) {
        HBox chatblock = new HBox();
        Label personTag = new Label(person + ": ");
        Label personResponse = new Label(question);
        chatblock.getChildren().addAll(personTag, personResponse);
        if (person.equals("You")) {
        	personTag.setStyle("-fx-text-fill: Blue");
        } else {
        	personTag.setStyle("-fx-text-fill: Green");
        }
        center.getChildren().add(chatblock);
        center.setSpacing(5);
    }


    // Constructor to initialize the chat scene
    public ChatScene() {
        setSceneTitle("\t \t Welcome to this simplified chat. \n Use the provided options to find out more about me.");
        setMessage("Chat using the options below and get to know me");
        
        // Initialize questions and answers
        QandA = new HashMap<String, String>();
        QandA.put("Bio:", "What's your demographic Information?");
        QandA.put("Achievements:", " What do you excel at and what are your interests?");
        QandA.put("Character Traits:", "What are you like? What type of person are you?");
        QandA.put("Vices:", "What are your vices and weaknesses?");
        QandA.put("Reason:", "Why should we choose you?");
        
        // Initialize scroll pane and adjust layout
        scroll = new ScrollPane();
        root.getChildren().remove(center);
        root.setCenter(scroll);
        scroll.setContent(center);
        
        // Initialize buttons for each question
        buttons = new HBox();
        Button bio = new Button("Bio:");
        bio.setOnAction(e -> {
        	addText("You", QandA.get("Bio:"));
        	addText("Me", "I'm a creative and ambitious international student from Poland, who enjoys coding \n"
                    + "and learning new things. I enjoy anything from picking up new coding languages to \n"
                    + "learning about AI.");    
        });
        Button achievements = new Button("Achievements:");
        achievements.setOnAction(e -> {
        	addText("You", QandA.get("Achievements:"));
        	addText("Me", "I've received academic scholarships to attend both a boarding school in PA \n"
                    + "for 3 years, but also to Hobart and William Smith Colleges. When I lived in Poland,\n "
                    + "I also was a part of Polish National Team in Squash, which I owe my work ethic and\n "
                    + "competetiveness to.");   
        });
        Button traits = new Button("Character traits:");
        traits.setOnAction(e -> {
        	addText("You", QandA.get("Character Traits:"));
        	addText("Me", "I'm extroverted, I like to laugh and socialize. Nevertheless, I value highly \n"
                    + "self-improvement, athletics and discipline. I love to work on myself in the gym,\n"
                    + "but also learning new skills and putting them to test.");    
        });
        Button vices = new Button("Vices:");
        vices.setOnAction(e -> {
        	addText("You", QandA.get("Vices:"));
        	addText("Me", "It's not easy finding and acknowledging your weaknesses, however we're all human \n"
                    + "and nobody is perfect. That being said I'm pretty reserved to people I don't know, \n"
                    + "I am my toughest judge, and I tend to not be optimistic, I'm more of a realist.");        
        });
        Button reason = new Button("Reason:");
        reason.setOnAction(e -> {
        	addText("You", QandA.get("Reason:"));
        	addText("Me", "Why should you choose me? Well there are several reasons and they are my values of \n"
                    + "I am my toughest judge, and I tend to not be optimistic, I know the necessity for hard \n"
                    + "work, especially if you're aiming to better than average. Moreover, I'm adaptable and \n"
                    + "a quick learner, which I had to be to survive & thrive on a different continent since \n"
                    + "young age. Lastly, I don't give up easily, which is what my athletic carrer taught me.");    
        });
        // Add buttons to layout
        buttons.getChildren().addAll(bio, achievements, traits, vices, reason);
        buttons.setSpacing(5);
        bottom.getChildren().add(buttons);
        buttons.setAlignment(Pos.CENTER);
        
        // Add "Go back home" button
        Button home = new Button("Go back home");
        home.setOnAction(e -> {
            SceneManager.setScene(SceneManager.SceneType.Home);
        });
        bottom.getChildren().add(home);
        bottom.setSpacing(10);
    }

}
