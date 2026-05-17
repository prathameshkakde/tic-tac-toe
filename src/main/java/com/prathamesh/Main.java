package com.prathamesh;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for the Tic-Tac-Toe game.
 */
public class Main extends Application {

    // size of the Tic-Tac-Toe board
    private static final int BOARD_SIZE = 3;

    // Track current player
    private String currentPlayer = "X";

    // 2D array to store board state
    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

    // Track whether game has ended
    private boolean gameOver = false;

    // Store button references
    private Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];

    // Label to display game status
    private Label statusLabel = new Label("Player X's Turn");

    @Override
    public void start(Stage primaryStage) {

        // Create a GidePane layout
        GridPane gridPane = new GridPane();

        // Center the grid on screen
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

        // Create 3 by 3 buttons using nested loops
        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                // Store current row and column for event handling
                final int currentRow = row;
                final int currentCol = col;

                // Create a button
                Button button = new Button();

                // Store button reference
                buttons[currentRow][currentCol] = button;

                // Set button size
                button.setPrefSize(100, 100);

                // Style game buttons
                applyNormalButtonStyle(button);

                // Button click event
                button.setOnAction(event -> {

                    // Only allow click if button is empty
                    if (button.getText().isEmpty() && !gameOver){

                        // set current player's symbol
                        button.setText(currentPlayer);

                        // Store move in board array
                        board[currentRow][currentCol] = currentPlayer;

                        // Check if current player has won
                        if (checkWinner()) {

                            System.out.println("Player " + currentPlayer + " Wins!!");
                            statusLabel.setText("Player " + currentPlayer + " Wins!");
                            statusLabel.setStyle(
                                    "-fx-font-size: 20px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-text-fill: #2E8B57;"
                            );
                            gameOver = true;
                            // Disable all buttons visually
                            disableBoard();
                        }
                        // Check for draw
                        else if (checkDraw()) {

                            System.out.println("The game is a draw!");
                            statusLabel.setText("It's a Draw!");
                            statusLabel.setStyle(
                                    "-fx-font-size: 20px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-text-fill: #FF8C00;"
                            );

                            gameOver = true;
                            disableBoard();
                        }

                        // Switch player only if game is still running
                        if (!gameOver) {

                            // Switch players turn
                            if (currentPlayer.equals("X")) {
                                currentPlayer = "O";
                            } else {
                                currentPlayer = "X";
                            }

                            // Update turn message
                            statusLabel.setText("Player " + currentPlayer + "'s Turn");
                            statusLabel.setStyle(
                                    "-fx-font-size: 20px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-text-fill: #333333;"
                            );
                        }
                    }
                });

                // Add button to GridPane
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

        // Restart button click event
        restartButton.setOnAction(event -> resetGame());

        // Create vertical layout
        VBox root = new VBox(20);

        // Center layout
        root.setAlignment(Pos.CENTER);

        // Add game board and restart button
        root.getChildren().addAll(statusLabel, gridPane, restartButton);

        // Create scene
        Scene scene = new Scene(root, 450, 550);

        // Configure stage
        primaryStage.setTitle("Tic-Tac-Toe Game");
        primaryStage.setScene(scene);

        // Show application window
        primaryStage.show();
    }

    /**
     *  Checks if the current player has won
     */
    private boolean checkWinner() {

        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {

            if (board[row][0] != null &&
                    board[row][0].equals(board[row][1]) &&
                    board[row][1].equals(board[row][2])) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {

            if (board[0][col] != null &&
                    board[0][col].equals(board[1][col]) &&
                    board[1][col].equals(board[2][col])) {
                return true;
            }
        }

        // Check main diagonal
        if (board[0][0] != null &&
                board[0][0].equals(board[1][1]) &&
                board[1][1].equals(board[2][2])) {
            return true;
        }

        // Check opposite diagonal
        if (board[0][2] != null &&
                board[0][2].equals(board[1][1]) &&
                board[1][1].equals(board[2][0])) {
            return true;
        }

        // No winner found
        return false;
    }

    /**
     * Checks if the game is a draw.
     */
    private boolean checkDraw() {

        // Check every cell in the board
        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                // If any cell is empty, game is not draw
                if (board[row][col] == null) {
                    return false;
                }
            }
        }

        // No empty cells found
        return true;
    }

    /**
     * Reset the game board
     */
    private void resetGame() {

        // Reset board array and buttons
        for  (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                // Clear board data
                board[row][col] = null;

                // Clear button text
                buttons[row][col].setText("");

                // Re-enable buttons
                buttons[row][col].setDisable(false);

                // Restore button style
                applyDisabledButtonStyle(buttons[row][col]);
            }
        }

        // Reset current player
        currentPlayer = "X";

        // Reset status label
        statusLabel.setText("Player X's Turn");

        // Allow game again
        gameOver = false;

        System.out.println("Game restarted!");
    }

    /**
     * Disables the board visually after game ends.
     */
    private void disableBoard() {

        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                buttons[row][col].setDisable(true);

                buttons[row][col].setStyle(
                        "-fx-font-size: 32px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-color: #d3d3d3;" +
                                "-fx-border-color: #b0b0b0;" +
                                "-fx-background-radius: 15px;" +
                                "-fx-border-radius: 15px;"
                );
            }
        }
    }

    /**
     * Applies the normal button style.
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
     * Applies the disabled button style.
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
     * Main method.
     * Launches the JavaFX application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}