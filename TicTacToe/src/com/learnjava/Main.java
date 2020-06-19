// ===== TicTacToe Project =====
// This is a basic console TicTacToe Game with two players.
// This game was created by me to learn the basics of Java.

package com.learnjava;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // @param String[] cells : for accessing its elements to be used for board format
    // @return String formattedBoard : returns formatted game board based on cells
    public static String RenderTicTacToeBoard(String[] cells) {
        String gameBoard = "-------\n|%s|%s|%s|\n-------\n|%s|%s|%s|\n-------\n|%s|%s|%s|\n-------\n";
        String formattedBoard = String.format(gameBoard, cells[0], cells[1], cells[2], cells[3], cells[4], cells[5], cells[6], cells[7], cells[8]);
        return formattedBoard;
    }

    // @param String[] cells : for accessing its elements to check any complete rows/columns/diagonals
    // @return boolean : true means someone has won the game, false means no one has won the game
    public static boolean PlayerWinTheGame(String[] cells) {

        for (int i = 0; i < 3; i++) {
            // Checks each row
            // Checks if the first item of the row equals to the second and the third items && the first item is not blank
            if (cells[i * 3].equals(cells[i * 3 + 1]) && cells[i * 3].equals(cells[i * 3 + 2]) && !cells[i * 3].equals(" "))
                return true;

            // Checks each column
            // Checks if the first item of the column equals to the second and the third items && the first item is not blank
            if (cells[i].equals(cells[i + 3]) && cells[i].equals(cells[i + 6]) && !cells[i].equals(" "))
                return true;
        }

        // Checks for each diagonal
        // First checks if the center cell (index 4) is not blank
        if (!cells[4].equals(" ")) {
            // Checks top-left-to-bottom-right diagonal line
            if (cells[0].equals(cells[4]) && cells[0].equals(cells[8]))
                return true;

            // top-right-to-bottom-left diagonal line is the last possible way to win
            // Therefore: returns true if the diagonal line completes, returns false if the diagonal line does not complete
            else return cells[2].equals(cells[4]) && cells[2].equals(cells[6]);
        }

        // The default return value
        return false;
    }


    public static void main(String[] args) {
        // Declares a Scanner object to take in inputs from console
        Scanner scanner = new Scanner(System.in);

        // Cells of TicTacToe board
        String[] cells = new String[9];

        // Initializes each cell in cells to have its corresponding number
        for (int i = 0; i < cells.length; i++) {
            cells[i] = Integer.toString(i + 1);
        }

        // Initializes the game board
        String ticTacToeBoard = "";

        // Formats the game board with numbers to show each cell's corresponding number
        ticTacToeBoard = RenderTicTacToeBoard(cells);


        // ===== Game Introduction =====
        System.out.println("=== TicTacToe ===");
        System.out.println("The game board looks like this (with corresponding number for each cell): ");
        System.out.println(ticTacToeBoard);

        System.out.println("Required Players: 2");
        System.out.println("Decide which player is \"X\" and \"O\" beforehand.");
        System.out.println("To fill in a cell, type a corresponding number of that cell.");
        // =========================


        // ===== Game Mechanics =====

        // Initializes the game board with blank spaces
        Arrays.fill(cells, " ");
        ticTacToeBoard = RenderTicTacToeBoard(cells);

        // Variables used in the game
        int cellNumberInput = 0; // A placeholder for user inputs, which specifies which cell to fill in
        int nthMove = 1; // Counts how many turns have done
        String cellFiller = ""; // It will switch between "X" or "O". This switches as game proceeds.


        // The Game Loop
        while(true) {

            // Print out the game board every turn
            System.out.println(ticTacToeBoard);


            if (nthMove%2 == 0) { // Every even turn, O-player moves
                System.out.print("Player O: ");
                cellFiller = "O";
            }
            else { // Every odd turn, X-player moves
                System.out.print("Player X: ");
                cellFiller = "X";
            }


            // Error handling
            while(true) {
                // Takes in user input [int], which corresponds to the cell's number
                cellNumberInput = scanner.nextInt();

                // Enforces users to type number between 1 to 9 && checks if the chosen cell is already taken
                if (cellNumberInput >= 1 && cellNumberInput <= 9 && cells[cellNumberInput - 1].equals(" ")) {
                    // Updates the cells array and the game board
                    cells[cellNumberInput - 1] = cellFiller;
                    ticTacToeBoard = RenderTicTacToeBoard(cells);
                    break;
                }

                System.out.print("Type a number between 1-9 or pick a cell that has not been taken: ");
            }


            // Checks if someone has won the game
            if (PlayerWinTheGame(cells)) {
                System.out.println(ticTacToeBoard);
                System.out.println("=== Congratulations! Player " + cellFiller + " won the game! ===");
                break;
            } else if (nthMove == cells.length) { // If all cells are filled up without any winner (= after 9 turns)
                System.out.println(ticTacToeBoard);
                System.out.println("=== The Game Tied! ===");
                break;
            }

            nthMove++;
        }

    }

}
