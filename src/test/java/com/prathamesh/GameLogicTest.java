package com.prathamesh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GameLogic class.
 */
public class GameLogicTest {

    /**
     * Test initial game state.
     */
    @Test
    void testInitialGameState() {

        // Create GameLogic object
        GameLogic gameLogic = new GameLogic();

        // Verify current player starts as X
        assertEquals(
                "X",
                gameLogic.getCurrentPlayer()
        );

        // Verify game is not over initially
        assertFalse(
                gameLogic.isGameOver()
        );

        // Verify board is empty
        String[][] board = gameLogic.getBoard();

        for (int row = 0; row < GameLogic.BOARD_SIZE; row++) {

            for (int col = 0; col < GameLogic.BOARD_SIZE; col++) {

                assertNull(board[row][col]);
            }
        }
    }

    /**
     * Test storing a move on the board.
     */
    @Test
    void testMakeMove() {

        // Create GameLogic object
        GameLogic gameLogic = new GameLogic();

        // Store move
        gameLogic.makeMove(1, 1, "X");

        // Get board
        String[][] board = gameLogic.getBoard();

        // Verify move was stored correctly
        assertEquals(
                "X",
                board[1][1]
        );
    }

    /**
     * Test winner detection for a row.
     */
    @Test
    void testCheckWinnerRow() {

        // Create GameLogic object
        GameLogic gameLogic = new GameLogic();

        // Create winning row
        gameLogic.makeMove(0, 0, "X");
        gameLogic.makeMove(0, 1, "X");
        gameLogic.makeMove(0, 2, "X");

        // Verify winner detected
        assertTrue(
                gameLogic.checkWinner()
        );
    }

    /**
     * Test winner detection for a column.
     */
    @Test
    void testCheckWinnerColumn() {

        // Create GameLogic object
        GameLogic gameLogic = new GameLogic();

        // Create winning column
        gameLogic.makeMove(0, 1, "O");
        gameLogic.makeMove(1, 1, "O");
        gameLogic.makeMove(2, 1, "O");

        // Verify winner detected
        assertTrue(
                gameLogic.checkWinner()
        );
    }

    /**
     * Test winner detection for diagonal.
     */
    @Test
    void testCheckWinnerDiagonal() {

        // Create GameLogic object
        GameLogic gameLogic = new GameLogic();

        // Create diagonal win
        gameLogic.makeMove(0, 0, "X");
        gameLogic.makeMove(1, 1, "X");
        gameLogic.makeMove(2, 2, "X");

        // Verify winner detected
        assertTrue(
                gameLogic.checkWinner()
        );
    }
}