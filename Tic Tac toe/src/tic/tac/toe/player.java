package tic.tac.toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class player extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private static int count = 0;
    private JButton b[] = new JButton[9];
    private char board[][] = new char[3][3];

    public player() {
        for (int i = 0; i < 9; i++) {
            b[i] = new JButton();
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
        for (int i = 0; i < 9; i++) {
            panel.add(b[i]);
            b[i].addActionListener(this);
            b[i].setFont(new Font("Arial", Font.PLAIN, 100));
        }

        this.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            check_player(0);
            board[0][0] = b[0].getText().charAt(0);
        }
        if (e.getSource() == b[1]) {
            check_player(1);
            board[0][1] = b[1].getText().charAt(0);
        }
        if (e.getSource() == b[2]) {
            check_player(2);
            board[0][2] = b[2].getText().charAt(0);
        }
        if (e.getSource() == b[3]) {
            check_player(3);
            board[1][0] = b[3].getText().charAt(0);
        }
        if (e.getSource() == b[4]) {
            check_player(4);
            board[1][1] = b[4].getText().charAt(0);
        }
        if (e.getSource() == b[5]) {
            check_player(5);
            board[1][2] = b[5].getText().charAt(0);
        }
        if (e.getSource() == b[6]) {
            check_player(6);
            board[2][0] = b[6].getText().charAt(0);
        }
        if (e.getSource() == b[7]) {
            check_player(7);
            board[2][1] = b[7].getText().charAt(0);
        }
        if (e.getSource() == b[8]) {
            check_player(8);
            board[2][2] = b[8].getText().charAt(0);
        }
        check_winner();
    }

    public void check_winner() {

        if (b[0].getText() == b[3].getText() && b[0].getText() == b[6].getText()) {
            if (b[0].getText() == "X") {
                color(b[0], b[3], b[6]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[0].getText() == "O") {
                color(b[0], b[3], b[6]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }
        if (b[1].getText() == b[4].getText() && b[1].getText() == b[7].getText()) {
            if (b[1].getText() == "X") {
                color(b[1], b[4], b[7]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[1].getText() == "O") {
                color(b[1], b[4], b[7]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }
        if (b[2].getText() == b[5].getText() && b[2].getText() == b[8].getText()) {
            if (b[2].getText() == "X") {
                color(b[2], b[5], b[8]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[2].getText() == "O") {
                color(b[2], b[5], b[8]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }
        if (b[0].getText() == b[4].getText() && b[0].getText() == b[8].getText()) {
            if (b[0].getText() == "X") {
                color(b[0], b[4], b[8]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[0].getText() == "O") {
                color(b[0], b[4], b[8]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }
        if (b[2].getText() == b[4].getText() && b[2].getText() == b[6].getText()) {
            if (b[2].getText() == "X") {
                color(b[2], b[4], b[6]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[2].getText() == "O") {
                color(b[2], b[4], b[6]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }

        if (b[0].getText() == b[1].getText() && b[0].getText() == b[2].getText()) {
            if (b[0].getText() == "X") {
                color(b[0], b[1], b[2]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[0].getText() == "O") {
                color(b[0], b[1], b[2]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }
        if (b[3].getText() == b[4].getText() && b[3].getText() == b[5].getText()) {
            if (b[3].getText() == "X") {
                color(b[3], b[4], b[5]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[3].getText() == "O") {
                color(b[3], b[4], b[5]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }
        if (b[6].getText() == b[7].getText() && b[6].getText() == b[8].getText()) {
            if (b[6].getText() == "X") {
                color(b[6], b[7], b[8]);
                JOptionPane.showMessageDialog(this, "X Winner");
                play_again();
            } else if (b[6].getText() == "O") {
                color(b[6], b[7], b[8]);
                JOptionPane.showMessageDialog(this, "O Winner");
                play_again();
            }
        }

        if (count == 9) {
            JOptionPane.showMessageDialog(this, " Balance");
            play_again();
        }
    }

    public void play_again() {
        int choice = JOptionPane.showConfirmDialog(null, "Do You Want To Play Again?",
                "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            new player();
            this.dispose();
        } else if (choice == JOptionPane.NO_OPTION) {
            new start();
            this.dispose();
        } else {
            this.dispose();
        }
        count = 0;
    }

    public void color(JButton a, JButton b, JButton c) {
        a.setBackground(Color.GREEN);
        b.setBackground(Color.GREEN);
        c.setBackground(Color.GREEN);
    }

    public void check_player(int i) {
        if (b[i].getText() == "") {
            if (count % 2 == 0) {
                b[i].setText("X");
                count++;
            } else {
                b[i].setText("O");
                count++;
            }
        }
    }

    public void print(char board[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + board[i][j] + " |");
            }
            System.out.println("\n--------------");
        }
    }

}
