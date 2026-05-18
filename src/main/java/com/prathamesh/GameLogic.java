package com.prathamesh;

/**
 * Handles Tic-Tac-Toe game logic and board state.
 */
public class GameLogic {

    // Board size constant
    public static final int BOARD_SIZE = 3;

    // 2D array to store board state
    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

    // Track current player
    private String currentPlayer = "X";

    // Track game state
    private boolean gameOver = false;

    /**
     * Returns the game board.
     */
    public String[][] getBoard() {
        return board;
    }

    /**
     * Returns the current player.
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player.
     */
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Returns whether the game is over.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets the game over state.
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Stores a move in the board.
     */
    public void makeMove(int row, int col, String player) {
        board[row][col] = player;
    }

    /**
     * Clears the board.
     */
    public void resetBoard() {

        for (int row = 0; row < BOARD_SIZE; row++) {

            for (int col = 0; col < BOARD_SIZE; col++) {

                board[row][col] = null;
            }
        }

        currentPlayer = "X";
        gameOver = false;
    }
}