package com.prathamesh;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class for the Tic-Tac-Toe game.
 */
public class Main extends Application {

    // Board size constant
    private static final int BOARD_SIZE = 3;

    // Game logic object
    private GameLogic gameLogic = new GameLogic();

    // Store button references
    private Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];

    // Label to display game status
    private Label statusLabel = new Label("Player X's Turn");

    @Override
    public void start(Stage primaryStage) {

        // Create GridPane layout
        GridPane gridPane = new GridPane();

        // Center the grid
        gridPane.setAlignment(Pos.CENTER);

        // Add spacing between buttons
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Style status label
        statusLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #333333;"
        );

        // Create board buttons
        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                // Store row and column
                final int currentRow = row;
                final int currentCol = col;

                // Create button
                Button button = new Button();

                // Store button reference
                buttons[currentRow][currentCol] = button;

                // Set button size
                button.setPrefSize(100, 100);

                // Apply style
                applyNormalButtonStyle(button);

                // Button click event
                button.setOnAction(event -> {

                    // Only allow move if button empty and game active
                    if (button.getText().isEmpty()
                            && !gameLogic.isGameOver()) {

                        // Place symbol visually
                        button.setText(gameLogic.getCurrentPlayer());

                        // Store move internally
                        gameLogic.makeMove(
                                currentRow,
                                currentCol,
                                gameLogic.getCurrentPlayer()
                        );

                        // Check winner
                        if (gameLogic.checkWinner()) {

                            System.out.println(
                                    "Player "
                                            + gameLogic.getCurrentPlayer()
                                            + " Wins!"
                            );

                            statusLabel.setText(
                                    "Player "
                                            + gameLogic.getCurrentPlayer()
                                            + " Wins!"
                            );

                            statusLabel.setStyle(
                                    "-fx-font-size: 20px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-text-fill: #2E8B57;"
                            );

                            gameLogic.setGameOver(true);

                            disableBoard();
                        }

                        // Check draw
                        else if (gameLogic.checkDraw()) {

                            System.out.println("The game is a draw!");

                            statusLabel.setText("It's a Draw!");

                            statusLabel.setStyle(
                                    "-fx-font-size: 20px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-text-fill: #FF8C00;"
                            );

                            gameLogic.setGameOver(true);

                            disableBoard();
                        }

                        // Continue game if not over
                        if (!gameLogic.isGameOver()) {

                            // Switch player
                            if (gameLogic.getCurrentPlayer().equals("X")) {
                                gameLogic.setCurrentPlayer("O");
                            } else {
                                gameLogic.setCurrentPlayer("X");
                            }

                            // Update status label
                            statusLabel.setText(
                                    "Player "
                                            + gameLogic.getCurrentPlayer()
                                            + "'s Turn"
                            );

                            statusLabel.setStyle(
                                    "-fx-font-size: 20px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-text-fill: #333333;"
                            );
                        }
                    }
                });

                // Add button to grid
                gridPane.add(button, col, row);
            }
        }

        // Create restart button
        Button restartButton = new Button("Restart Game");

        // Style restart button
        restartButton.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #4CAF50;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-padding: 10px 20px;"
        );

        // Restart button action
        restartButton.setOnAction(event -> resetGame());

        // Create vertical layout
        VBox root = new VBox(20);

        // Center layout
        root.setAlignment(Pos.CENTER);

        // Add UI components
        root.getChildren().addAll(
                statusLabel,
                gridPane,
                restartButton
        );

        // Create scene
        Scene scene = new Scene(root, 450, 550);

        // Configure stage
        primaryStage.setTitle("Tic-Tac-Toe Game");
        primaryStage.setScene(scene);

        // Show application
        primaryStage.show();
    }

    /**
     * Resets the game.
     */
    private void resetGame() {

        // Reset game logic
        gameLogic.resetBoard();

        // Reset buttons
        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                // Clear button text
                buttons[row][col].setText("");

                // Enable button
                buttons[row][col].setDisable(false);

                // Clear old style
                buttons[row][col].setStyle("");

                // Restore style
                applyNormalButtonStyle(buttons[row][col]);
            }
        }

        // Reset status label
        statusLabel.setText("Player X's Turn");

        statusLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #333333;"
        );

        System.out.println("Game restarted!");
    }

    /**
     * Disables board visually.
     */
    private void disableBoard() {

        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                buttons[row][col].setDisable(true);

                applyDisabledButtonStyle(buttons[row][col]);
            }
        }
    }

    /**
     * Applies normal button style.
     */
    private void applyNormalButtonStyle(Button button) {

        button.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #f5f5f5;" +
                        "-fx-border-color: #dcdcdc;" +
                        "-fx-border-width: 2px;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-radius: 15px;"
        );
    }

    /**
     * Applies disabled button style.
     */
    private void applyDisabledButtonStyle(Button button) {

        button.setStyle(
                "-fx-font-size: 32px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #d3d3d3;" +
                        "-fx-border-color: #b0b0b0;" +
                        "-fx-background-radius: 15px;" +
                        "-fx-border-radius: 15px;"
        );
    }

    /**
     * Launches JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}