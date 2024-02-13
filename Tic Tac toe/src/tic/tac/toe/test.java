/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

/**
 *
 * @author Allah
 */
public class test {
    // A function to evaluate the score of the board
// Returns 10 if AI wins, -10 if human wins, 0 if tie
int evaluate(char[] board) {
  // Check rows for a win
  for (int i = 0; i < 9; i += 3) {
    if (board[i] == board[i + 1] && board[i + 1] == board[i + 2]) {
      if (board[i] == 'X') return 10; // AI is X
      if (board[i] == 'O') return -10; // Human is O
    }
  }
  // Check columns for a win
  for (int i = 0; i < 3; i++) {
    if (board[i] == board[i + 3] && board[i + 3] == board[i + 6]) {
      if (board[i] == 'X') return 10;
      if (board[i] == 'O') return -10;
    }
  }
  // Check diagonals for a win
  if (board[0] == board[4] && board[4] == board[8]) {
    if (board[0] == 'X') return 10;
    if (board[0] == 'O') return -10;
  }
  if (board[2] == board[4] && board[4] == board[6]) {
    if (board[2] == 'X') return 10;
    if (board[2] == 'O') return -10;
  }
  // No winner, return 0
  return 0;
}

// A function to check if there are any moves left on the board
// Returns true if there are empty cells, false otherwise
boolean isMovesLeft(char[] board) {
  for (int i = 0; i < 9; i++) {
    if (board[i] == '-') return true; // Empty cell
  }
  return false; // No empty cells
}

// A recursive function to implement the minimax algorithm
// Returns the best value for the current board state
// isMax is true if AI is the maximizing player, false if human is the minimizing player
int minimax(char[] board, int depth, boolean isMax) {
  // Evaluate the score of the board
  int score = evaluate(board);

  // If AI has won, return the score
  if (score == 10) return score;

  // If human has won, return the score
  if (score == -10) return score;

  // If there are no moves left, return 0 (tie)
  if (isMovesLeft(board) == false) return 0;

  // If AI is the maximizing player
  if (isMax) {
    // Initialize the best value to the lowest possible
    int best = -1000;

    // Loop through all the cells of the board
    for (int i = 0; i < 9; i++) {
      // If the cell is empty
      if (board[i] == '-') {
        // Make the move
        board[i] = 'X';

        // Call minimax recursively and choose the maximum value
        best = Math.max(best, minimax(board, depth + 1, !isMax));

        // Undo the move
        board[i] = '-';
      }
    }
    // Return the best value
    return best;
  }

  // If human is the minimizing player
  else {
    // Initialize the best value to the highest possible
    int best = 1000;

    // Loop through all the cells of the board
    for (int i = 0; i < 9; i++) {
      // If the cell is empty
      if (board[i] == '-') {
        // Make the move
        board[i] = 'O';

        // Call minimax recursively and choose the minimum value
        best = Math.min(best, minimax(board, depth + 1, !isMax));

        // Undo the move
        board[i] = '-';
      }
    }
    // Return the best value
    return best;
  }
}

// A function to find the best move for the AI
// Returns the index of the best move
int findBestMove(char[] board) {
  // Initialize the best value to the lowest possible
  int bestVal = -1000;

  // Initialize the best move to -1
  int bestMove = -1;

  // Loop through all the cells of the board
  for (int i = 0; i < 9; i++) {
    // If the cell is empty
    if (board[i] == '-') {
      // Make the move
      board[i] = 'X';

      // Evaluate the value of the board
      int moveVal = minimax(board, 0, false);

      // Undo the move
      board[i] = '-';

      // If the value of the move is better than the best value
      if (moveVal > bestVal) {
        // Update the best value
        bestVal = moveVal;

        // Update the best move
        bestMove = i;
      }
    }
  }
  // Return the best move
  return bestMove;
}

}
