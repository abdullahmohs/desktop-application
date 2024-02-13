package tic.tac.toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Move {

    public int row;
    public int column;
}

public class computer extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JButton b[][] = new JButton[3][3];
    private char board[][] = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    private boolean isFirst = true, lock = false;

    public computer() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = new JButton();
            }
        }

        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("tic.jpg")));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        this.setTitle("Tic Tac Toe");
        this.setResizable(false);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.BLACK);
        this.add(panel);
        panel.setLayout(new GridLayout(3, 3, 8, 8));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                panel.add(b[i][j]);
                b[i][j].addActionListener(this);
                b[i][j].setFont(new Font("Arial", Font.PLAIN, 100));
            }
        }
        this.show();
    }

    // human x || ai o
    public int check_winner() {
        int gameState = 0;

        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[0][0] == 'X') {
                gameState = -2;
            } else if (board[0][0] == 'O') {
                gameState = 2;
            }
        } else if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][2] == 'X') {
                gameState = -2;
            } else if (board[0][2] == 'O') {
                gameState = 2;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][1] == board[i][2] && board[i][1] == board[i][0]) {
                if (board[i][0] == 'X') {
                    gameState = -2;
                } else if (board[i][0] == 'O') {
                    gameState = 2;
                }
            }

        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'X') {
                    gameState = -2;
                } else if (board[0][i] == 'O') {
                    gameState = 2;
                }
            }
        }
        int emptyCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    emptyCells++;
                }
            }
        }
        if (gameState == 0 && emptyCells == 0) {
            gameState = 1;
        }
        return gameState;
    }

    public void play_again() {
        int choice = JOptionPane.showConfirmDialog(null, "Do You Want To Play Again?",
                "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            new computer();
            this.dispose();
        } else if (choice == JOptionPane.NO_OPTION) {
            new start();
            this.dispose();
        } else {
            this.dispose();
        }
    }

    public void color() {
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            b[0][0].setBackground(Color.green);
            b[1][1].setBackground(Color.green);
            b[2][2].setBackground(Color.green);
        } else if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            b[0][2].setBackground(Color.green);
            b[1][1].setBackground(Color.green);
            b[2][0].setBackground(Color.green);
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][1] == board[i][2] && board[i][1] == board[i][0]) {
                b[i][0].setBackground(Color.green);
                b[i][1].setBackground(Color.green);
                b[i][2].setBackground(Color.green);
            }

        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                b[0][i].setBackground(Color.green);
                b[1][i].setBackground(Color.green);
                b[2][i].setBackground(Color.green);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == b[i][j]) {
                    if (b[i][j].getText() == "") {
                        b[i][j].setText("X");
                        board[i][j] = 'X';
                        lock = true;
                    }
                }
            }
        }
        if (lock) {
            Move best = findBestMove();
            if (best.row >= 0 && best.column >= 0) {
                System.out.println(best.row + " " + best.column);
                board[best.row][best.column] = 'O';
                b[best.row][best.column].setText("O");
                displayBoard();
                lock = false;
            }
            if (check_winner() == -2) {
                color();
                JOptionPane.showMessageDialog(this, "You Win");
                play_again();
            }
            if (check_winner() == 1) {
                JOptionPane.showMessageDialog(this, "Tie");
                play_again();
            }
            if (check_winner() == 2) {
                color();
                JOptionPane.showMessageDialog(this, "You Lose");
                play_again();
            }

        }
    }

    public int minimax(int depth, boolean isMaximizing) {
        int gameState = check_winner();
        if (gameState != 0 || depth == 0) {
            return gameState;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int score = minimax(depth - 1, false);
                        board[i][j] = ' ';
                        bestScore = Integer.max(score, bestScore);
                    }
                }
            }

            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int score = minimax(depth - 1, true);
                        board[i][j] = ' ';
                        bestScore = Integer.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public void displayBoard() {
        System.out.println();
        System.out.print("-------\n|");
        String printedSymbols = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case ' ' ->
                        printedSymbols = " " + "|";
                    case 'X' ->
                        printedSymbols = "X" + "|";
                    case 'O' ->
                        printedSymbols = "O" + "|";
                }
                System.out.print(printedSymbols);
            }
            System.out.print("\n-------\n|");
        }
    }

    public Move findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.column = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int moveVal = minimax(100, false);
                    board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.column = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}
