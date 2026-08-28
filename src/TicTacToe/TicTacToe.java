package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    // The grid where the game is played
    private static char[][] grid = new char[3][3];

    // The players, represented as X and O
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    // The current player
    private static char currentPlayer = PLAYER_X;

    // Random object for computer moves
    private static Random random = new Random();

    public static void main(String[] args) {

        // Initialize the grid with empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }

        // Create Scanner once
        Scanner scanner = new Scanner(System.in);

        // Choose game mode
        System.out.println("Choose game mode:");
        System.out.println("1 - Player vs Player");
        System.out.println("2 - Player vs Computer");
        System.out.print("Enter your choice: ");

        int gameMode;

        // Get valid game mode
        while (true) {

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.nextLine();
                continue;
            }

            gameMode = scanner.nextInt();

            if (gameMode == 1 || gameMode == 2) {
                break;
            }

            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        // Start the game loop
        while (true) {

            // Print the grid
            printGrid();

            int row;
            int col;

            // Computer's turn
            if (gameMode == 2 && currentPlayer == PLAYER_O) {

                System.out.println("Computer's turn...");

                // Computer chooses a move
                int[] move = computerMove();

                row = move[0];
                col = move[1];

                // Put O on the board
                grid[row][col] = PLAYER_O;

                System.out.println(
                        "Computer chose: row " + row + ", column " + col);

            } else {

                // Human player's turn
                System.out.println(
                        "Player " + currentPlayer + ", enter your move:");

                // Keep asking until a valid move is entered
                while (true) {

                    System.out.print("Enter row and column (0-2): ");

                    // Check if row is a number
                    if (!scanner.hasNextInt()) {
                        System.out.println(
                                "Invalid input. Please enter numbers.");
                        scanner.nextLine();
                        continue;
                    }

                    row = scanner.nextInt();

                    // Check if column is a number
                    if (!scanner.hasNextInt()) {
                        System.out.println(
                                "Invalid input. Please enter two numbers.");
                        scanner.nextLine();
                        continue;
                    }

                    col = scanner.nextInt();

                    // Check if row and column are between 0 and 2
                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println(
                                "Invalid position. Row and column must be 0-2.");
                        continue;
                    }

                    // Check if the position is already occupied
                    if (grid[row][col] != ' ') {
                        System.out.println(
                                "That position is already taken.");
                        continue;
                    }

                    // Valid move
                    break;
                }

                // Update the grid with the player's move
                grid[row][col] = currentPlayer;
            }

            // Check if the game is over
            if (isGameOver()) {

                // Print the final grid
                printGrid();

                // Print the winner
                if (hasWinner()) {

                    if (gameMode == 2 && currentPlayer == PLAYER_O) {
                        System.out.println("Computer wins!");
                    } else {
                        System.out.println(
                                "Player " + currentPlayer + " wins!");
                    }

                } else {
                    System.out.println("It's a tie!");
                }

                // End the game
                break;
            }

            // Switch to the other player
            currentPlayer = (currentPlayer == PLAYER_X)
                    ? PLAYER_O
                    : PLAYER_X;
        }

        scanner.close();
    }

    // Computer chooses a move
    private static int[] computerMove() {

        // Rule 1:
        // If the player has two X's horizontally,
        // block the third position.
        int[] move = findHorizontalThreat();

        if (move != null) {
            return move;
        }

        // Rule 2:
        // If the player has two X's diagonally,
        // block the third position.
        move = findDiagonalThreat();

        if (move != null) {
            return move;
        }

        // Otherwise, choose a random empty position
        return randomMove();
    }

    // Find two X's horizontally
    private static int[] findHorizontalThreat() {

        for (int row = 0; row < 3; row++) {

            int xCount = 0;
            int emptyCol = -1;

            for (int col = 0; col < 3; col++) {

                if (grid[row][col] == PLAYER_X) {
                    xCount++;
                } else if (grid[row][col] == ' ') {
                    emptyCol = col;
                }
            }

            // Two X's and one empty space
            if (xCount == 2 && emptyCol != -1) {
                return new int[]{row, emptyCol};
            }
        }

        return null;
    }

    // Find two X's on a diagonal
    private static int[] findDiagonalThreat() {

        // First diagonal:
        // [0][0], [1][1], [2][2]

        if (grid[0][0] == PLAYER_X
                && grid[1][1] == PLAYER_X
                && grid[2][2] == ' ') {

            return new int[]{2, 2};
        }

        if (grid[0][0] == PLAYER_X
                && grid[2][2] == PLAYER_X
                && grid[1][1] == ' ') {

            return new int[]{1, 1};
        }

        if (grid[1][1] == PLAYER_X
                && grid[2][2] == PLAYER_X
                && grid[0][0] == ' ') {

            return new int[]{0, 0};
        }

        // Second diagonal:
        // [0][2], [1][1], [2][0]

        if (grid[0][2] == PLAYER_X
                && grid[1][1] == PLAYER_X
                && grid[2][0] == ' ') {

            return new int[]{2, 0};
        }

        if (grid[0][2] == PLAYER_X
                && grid[2][0] == PLAYER_X
                && grid[1][1] == ' ') {

            return new int[]{1, 1};
        }

        if (grid[1][1] == PLAYER_X
                && grid[2][0] == PLAYER_X
                && grid[0][2] == ' ') {

            return new int[]{0, 2};
        }

        return null;
    }

    // Choose a random empty position
    private static int[] randomMove() {

        while (true) {

            // Random row and column from 0 to 2
            int row = random.nextInt(3);
            int col = random.nextInt(3);

            // Make sure the position is empty
            if (grid[row][col] == ' ') {
                return new int[]{row, col};
            }
        }
    }

    // Print the grid to the console
    private static void printGrid() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(grid[i][j]);

                if (j < 2) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (i < 2) {
                System.out.println("-+-+-");
            }
        }
    }

    // Check if the game is over
    private static boolean isGameOver() {

        return hasWinner() || isFull();
    }

    // Check if there is a winner
    private static boolean hasWinner() {

        // Check horizontal wins
        for (int i = 0; i < 3; i++) {

            if (isRowWin(i)) {
                return true;
            }
        }

        // Check vertical wins
        for (int i = 0; i < 3; i++) {

            if (isColWin(i)) {
                return true;
            }
        }

        // Check diagonal wins
        if (isDiag1Win() || isDiag2Win()) {
            return true;
        }

        return false;
    }

    // Check if the given row has a winning combination
    private static boolean isRowWin(int row) {

        return grid[row][0] != ' '
                && grid[row][0] == grid[row][1]
                && grid[row][1] == grid[row][2];
    }

    // Check if the given column has a winning combination
    private static boolean isColWin(int col) {

        return grid[0][col] != ' '
                && grid[0][col] == grid[1][col]
                && grid[1][col] == grid[2][col];
    }

    // Check if the first diagonal has a winning combination
    private static boolean isDiag1Win() {

        return grid[0][0] != ' '
                && grid[0][0] == grid[1][1]
                && grid[1][1] == grid[2][2];
    }

    // Check if the second diagonal has a winning combination
    private static boolean isDiag2Win() {

        return grid[0][2] != ' '
                && grid[0][2] == grid[1][1]
                && grid[1][1] == grid[2][0];
    }

    // Check if there are no more empty spaces
    private static boolean isFull() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }
}
