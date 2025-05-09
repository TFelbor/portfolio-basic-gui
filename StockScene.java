// Author: Tytus Felbor
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StockScene extends SceneTemplate{

    // Fields for the stock scene
    private VBox sideBar;
    private HBox searchBar;
    private Label searchLabel;
    private TextField query;
    private VBox stockDisplay;
    private Label displayedStock;
    private Label displayedPrice;
    private Button lookup;
    private Button again;
    private Label history;
    
    // Constructor for the stock scene
    public StockScene() {
        // Set the scene title and message
        setSceneTitle("Welcome to Stock look-up");
        setMessage("Type in a stock symbol to look up its price."
                + "\n Use the 'Try Again' button to save it and look up another stock's price");
        
        // Initialize layout components
        sideBar = new VBox();
        sideBar.setSpacing(5);
        root.setLeft(sideBar);
        history = new Label("Previously looked up");
        sideBar.getChildren().add(history);
        
        searchBar = new HBox();
        searchBar.setSpacing(10);
        searchLabel = new Label("Search by entering "
                            + "\n a stock symbol");
        query = new TextField();
        lookup = new Button("Find price");
        lookup.setOnAction(e -> {
            getQuote(query.getText());
        });
        again = new Button("Reset & Try a different symbol");
        again.setOnAction(e -> {
            addToHistory();
        });
        searchBar.getChildren().addAll(searchLabel, query, lookup);
        searchBar.setSpacing(10);
        center.getChildren().addAll(searchBar);
        
        // Create a button to navigate back to the home scene
        Button home = new Button("Home");
        home.setOnAction(e -> {SceneManager.setScene(SceneManager.SceneType.Home);});
        bottom.getChildren().add(home);
        
        stockDisplay = new VBox();
        displayedStock = new Label();
        displayedStock.setFont(new Font(40));
        displayedPrice = new Label();
        displayedPrice.setFont(new Font(40));
        stockDisplay.getChildren().addAll(displayedStock, displayedPrice);
        stockDisplay.setAlignment(Pos.CENTER);
        center.getChildren().addAll(stockDisplay, again);
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);

    }
    
    // Method to fetch stock quote using the given symbol
    public void getQuote(String s) {
        String key = "cfr4dppr01qhg1ura7e0cfr4dppr01qhg1ura7eg";
        try {
            URL url = new URL("https://finnhub.io/api/v1/quote?symbol=" + s + "&token="+key);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String json = br.readLine();
            JSONObject j = new JSONObject(json);
            displayedPrice.setText(j.get("c")+"");
            displayedStock.setText(s);
        } catch(Exception e) {
            e.printStackTrace();
        }   
    }
    
    // Method to add the stock symbol and price to the history
    public void addToHistory() {
        if (!query.getText().equals(null)) {
            HBox historicDisplay = new HBox();
            Label historicSymbol = new Label(displayedStock.getText() + " |");
            Label historicPrice = new Label(" " + displayedPrice.getText());
            historicDisplay.getChildren().addAll(historicSymbol, historicPrice);
            sideBar.getChildren().add(historicDisplay);
            query.clear();
            displayedStock.setText("");
            displayedPrice.setText("");
        }
    }
    
}
