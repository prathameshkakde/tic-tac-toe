package com.prathamesh;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main class for the Tic-Tac-Toe game.
 * This is the entry point of the JavaFX application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create a label to display text
        Label welcomeLabel = new Label("Welcome to Tic-Tac-Toe!");

        // StackPane is a simple layout container
        StackPane root = new StackPane();

        // Add the label into the layout
        root.getChildren().add(welcomeLabel);

        // Create the scene
        Scene scene = new Scene(root, 400, 300);

        // Configure the main window
        primaryStage.setTitle("Tic-Tac-Toe Game");
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();
    }

    /**
     * Main method.
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}