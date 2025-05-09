   # Basic JavaFX GUI Application

   The application is a Java program that with the use of JavaFX features implements some basic techniques and solutions to problems that       building a GUI might present one with.

   ## Table of Contents

   1. Features
   2. Usage
   3. Application Structure


   ## 1. Features 

   - User Authentication ⇒ allows the user to log-in using the appropriate username & password (guest initials: user, user123)
   - Event-Driven programming ⇒ the output is driven by user actions
   - Inheritance & Polymorphism ⇒ the classes of a program are built using a blueprint for classes
   - Parsing JSON Objects ⇒ the outputs are collected using an online request, then from JSON format parsed into the program’s GUI

 
   ## 2. Usage

   - Prerequisites ⇒ you need to make sure you have Java Development Kit (JDK) and JavaFX installed & working on your computer.
   - Building the program ⇒ compile the source code to generate executable class files.
   - Running the program ⇒ Execute the compiled main class file (AppStarter.java) using Java Virtual Machine to run the code.

   
   ## 3. Application Structure

   - `AppRunner.java` ⇒ main class that initiates & launches the program
   - `CalculatorScene.java` ⇒ class responsible for displaying & handling the calculator
   - `ChatScene.java` ⇒ class implementing a simplified chat between you and me
   - `HomeScene.java` ⇒ class responsible for guiding the user through the choices of scenes
   - `LoginScene.java` ⇒ class responsible for the log-in scene
   - `SceneManager.java` ⇒ class that manages and simplifies the scene operations
   - `SceneTemplate.java` ⇒ abstract class serving as a blueprint for other scene classes
   - `StockScene.java` ⇒ class responsible for displaying & looking-up a stock price of a given symbol
