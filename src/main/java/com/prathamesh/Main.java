package com.prathamesh;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main class for the Tic-Tac-Toe game.
 */
public class Main extends Application {

    // size of the Tic-Tac-Toe board
    private static final int BOARD_SIZE = 3;

    @Override
    public void start(Stage primaryStage) {

        // Create a GidePane layout
        GridPane gridPane = new GridPane();

        // Center the grid on screen
        gridPane.setAlignment(Pos.CENTER);

        // Add spacing between buttons
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create 3 by 3 buttons using nested loops
        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                // Create a button
                Button button = new Button();

                // Set button size
                button.setPrefSize(100, 100);

                // Add button to GridPane
                gridPane.add(button, col, row);
            }
        }

        // Create scene
        Scene scene = new Scene(gridPane, 400, 400);

        // Configure stage
        primaryStage.setTitle("Tic-Tac-Toe Game");
        primaryStage.setScene(scene);

        // Show application window
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