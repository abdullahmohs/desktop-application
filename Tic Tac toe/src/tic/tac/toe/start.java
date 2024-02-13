package tic.tac.toe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class start extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JButton player = new JButton("2 PLayer");
    private JButton computer = new JButton("Computer");
    private JButton exit = new JButton("Exit");
    private JLabel label = new JLabel("Tic Tac Toe");

    public start() {
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
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        this.add(panel);
        panel.add(player);
        panel.add(exit);
        panel.add(computer);
        panel.add(label);
        label.setBounds(150, 0, 300, 300);
        player.setBounds(200, 250, 150, 40);
        exit.setBounds(200, 450, 150, 40);
        computer.setBounds(200, 350, 150, 40);
        player.setFont(new Font("Arial", Font.PLAIN, 24));
        exit.setFont(new Font("Arial", Font.PLAIN, 24));
        computer.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setFont(new Font("Serif", Font.BOLD, 50));
        player.setForeground(Color.BLACK);
        computer.setForeground(Color.BLACK);
        exit.setForeground(Color.BLACK);
        label.setForeground(Color.BLACK);
        computer.addActionListener(this);
        player.addActionListener(this);
        exit.addActionListener(this);
        this.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == player) {
            new player();
            this.dispose();
        } else if (e.getSource() == exit) {
            int choice = JOptionPane.showConfirmDialog(null, "Do You Want To Exit?",
                    "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                this.dispose();
            } else if (choice == JOptionPane.NO_OPTION) {
                this.dispose();
                new start();
            }
        } else if (e.getSource() == computer) {
            new computer();
            this.dispose();
        }
    }
}
