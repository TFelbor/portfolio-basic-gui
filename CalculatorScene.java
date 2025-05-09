// Author: Tytus Felbor
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

// Import necessary JavaFX classes for GUI components.
public class CalculatorScene extends SceneTemplate{

    // Grid layout for organizing buttons.
	private GridPane buttonLayout;
	
	// Label to display the calculation output.
	private Label output;
	
	// Button for returning to the home scene.
	private Button home;
	
	// String to hold the current equation being entered.
	private String equation = "";
	
	// Flag indicating whether an operator has been used.
	private boolean operatorUsed = false;
	
	// Index of the last operator used in the equation.
	private int indexOfLastOperator = -1;
	
	// Method to perform the calculation based on the input equation.
	public String calculate(String eq){
		// Parse and evaluate the mathematical expression.
		double result = eval(eq);
		String resultString = String.valueOf(result);
		return resultString;
	}
	
	// Method to add a button to the button layout grid.
	public void addButton(String s, int row, int column) {
		// Create a button with the given label.
		Button adder = new Button(s);
		adder.setMaxHeight(Double.MAX_VALUE);
		adder.setMaxWidth(Double.MAX_VALUE);
		
		// Define action to be performed when the button is clicked.
		adder.setOnAction(e -> {
			// Handle button clicks based on their labels.
			if (!(adder.getText().equals("C") || adder.getText().equals("=") || adder.getText().equals("+") || adder.getText().equals("-") || adder.getText().equals("/") || adder.getText().equals("*") || adder.getText().equals("^"))) {
				if (!operatorUsed) {
					// Append input to the equation if no operator used previously.
					equation = equation + adder.getText();
					output.setText(equation);
					System.out.println(equation);
				}
				else {
					// Append input to the equation after an operator is used.
					equation = equation + adder.getText();
					output.setText(equation.substring(indexOfLastOperator + 1, equation.length()));
					System.out.println(equation);
				}
			}
			// Clear the equation and output.
			else if (adder.getText().equals("C")) {
				equation = "";
				output.setText("");
				operatorUsed = false;
				indexOfLastOperator = -1;
			}
			// Calculate the result of the equation.
			else if (adder.getText().equals("=")) {
				output.setText(calculate(equation));
				equation = "";
				operatorUsed = false;
				indexOfLastOperator = -1;
			}
			// Handle arithmetic operators.
			else if (adder.getText().equals("+") || adder.getText().equals("-") || adder.getText().equals("/") || adder.getText().equals("*") || adder.getText().equals("^")) {
				operatorUsed = true;
				equation = equation + adder.getText();
				indexOfLastOperator = equation.length() - 1;
				System.out.println(equation);
			}
		});
		// Add the button to the specified row and column in the grid layout.
		buttonLayout.add(adder, row, column);
	}
	
	// Constructor to initialize the calculator scene.
	public CalculatorScene() {
		// Set the title and message for the scene.
		setSceneTitle("Welcome to my simple calculator");
		setMessage("Use the buttons above to input the calculation & press '='");
		
		// Initialize the output label.
		output = new Label();
		output.setAlignment(Pos.CENTER);
		output.setFont(new Font(40));
		
		// Initialize the button layout grid.
		buttonLayout = new GridPane();
		buttonLayout.setAlignment(Pos.CENTER);
		buttonLayout.setHgap(10);
		buttonLayout.setVgap(10);
		
		// Add output label and button layout grid to the center of the scene.
		center.getChildren().addAll(output, buttonLayout);
		
		// Add buttons to the layout grid.
		addButton("C",0,4);
		addButton("(",1,4);
		addButton(")",2,4);
		addButton("^",3,4);
		addButton("0",0,3);
		addButton(".",1,3);
		addButton("=",2,3);
		addButton("/",3,3);
		addButton("1",0,2);
		addButton("2",1,2);
		addButton("3",2,2);
		addButton("*",3,2);
		addButton("4",0,1);
		addButton("5",1,1);
		addButton("6",2,1);
		addButton("-",3,1);
		addButton("7",0,0);
		addButton("8",1,0);
		addButton("9",2,0);
		addButton("+",3,0);
		
		// Initialize the 'Home' button and its action.
		Button home = new Button("Home");
		home.setOnAction(e -> {
			// Reset calculator state and navigate to the home scene.
			equation = "";
			output.setText("");
			boolean operatorUsed = false;
			int indexOfLastOperator = -1;
			SceneManager.setScene(SceneManager.SceneType.Home);
		});
		bottom.getChildren().add(home);
	}
	
	/*
	This method was found under a question thread at: stackoverflow.com. Credits:
	Function author's profile: https://stackoverflow.com/users/964243/boann
	stackoverflow.com question: https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form
	
	This method implements a recursive descent parser to handle arithmetic expressions. 
	*/ 
	public static double eval(final String str) {
	    return new Object() {
	        // Initialize position and current character variables.
	        int pos = -1, ch;
	        
	        // Method to advance to the next character in the input string.
	        void nextChar() {
	            // Increment position and assign the next character, or -1 if the end of the string is reached.
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }
	        
	        // Method to consume the specified character if it matches the current character.
	        // Returns true if the character is consumed, false otherwise.
	        boolean eat(int charToEat) {
	            // Skip any whitespace characters.
	            while (ch == ' ') nextChar();
	            // If the current character matches the specified character, consume it and return true.
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            // Otherwise, return false.
	            return false;
	        }
	        
	        // Method to initiate parsing and evaluation of the expression.
	        double parse() {
	            // Move to the next character in the input.
	            nextChar();
	            // Parse and evaluate the expression.
	            double x = parseExpression();
	            // If there are remaining characters in the input, throw an exception.
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            // Return the result of the expression evaluation.
	            return x;
	        }
	        
	        // Grammar for expression:
	        // expression = term | expression `+` term | expression `-` term
	        // This method handles addition and subtraction operations.
	        double parseExpression() {
	            // Parse the first term in the expression.
	            double x = parseTerm();
	            // Continue parsing and evaluating additional terms while there are '+' or '-' operators.
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }
	        
	        // Grammar for term:
	        // term = factor | term `*` factor | term `/` factor
	        // This method handles multiplication and division operations.
	        double parseTerm() {
	            // Parse the first factor in the term.
	            double x = parseFactor();
	            // Continue parsing and evaluating additional factors while there are '*' or '/' operators.
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }
	        
	        // Grammar for factor:
	        // factor = `+` factor | `-` factor | `(` expression `)` | number
	        //        | functionName `(` expression `)` | functionName factor
	        //        | factor `^` factor
	        // This method handles unary operators, parentheses, numbers, and function calls.
	        double parseFactor() {
	            // Handle unary operators: '+', '-'
	            if (eat('+')) return +parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus
	            
	            double x;
	            // Store the starting position of the factor.
	            int startPos = this.pos;
	            // Check for parentheses to handle grouped expressions.
	            if (eat('(')) { // parentheses
	                // Parse and evaluate the inner expression.
	                x = parseExpression();
	                // Ensure that the closing parenthesis is present.
	                if (!eat(')')) throw new RuntimeException("Missing ')'");
	            } 
	            // Check for numbers or decimal points.
	            else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                // Consume all consecutive digits and optional decimal point.
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                // Parse the numeric substring and convert it to a double.
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } 
	            // Check for mathematical functions.
	            else if (ch >= 'a' && ch <= 'z') { // functions
	                // Consume all characters in the function name.
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                // Retrieve the function name substring.
	                String func = str.substring(startPos, this.pos);
	                // Check for function arguments.
	                if (eat('(')) {
	                    // Parse and evaluate the argument expression.
	                    x = parseExpression();
	                    // Ensure that the closing parenthesis is present.
	                    if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
	                } else {
	                    // If no arguments, parse the factor without a function call.
	                    x = parseFactor();
	                }
	                // Perform computation based on the recognized function.
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } 
	            // Throw an exception if the character is not recognized.
	            else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }
	            
	            // Check for exponentiation.
	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
	            
	            // Return the evaluated value of the factor.
	            return x;
	        }
	    }.parse(); // Initiate parsing and evaluation of the expression.
	}
}