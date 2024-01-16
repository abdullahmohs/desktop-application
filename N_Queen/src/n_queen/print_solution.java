package n_queen;

import java.awt.*;
import javax.swing.*;

public final class print_solution extends Frame {

    private JFrame frame = new JFrame();
    private ImageIcon img_black;
    private ImageIcon img_white;
    private JPanel p = new JPanel();
    private ImageIcon icon = new ImageIcon("icon.png");

    print_solution() {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        frame.setTitle("N-Queens");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(200, 100);
        frame.setSize(1000, 800);
    }

    public void print(int size, int board[][]) {

        try {
            img_black = new ImageIcon(getClass().getResource("king_black.jpg"));
            img_white = new ImageIcon(getClass().getResource("king_white.jpg"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JPanel panel[][] = new JPanel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                panel[i][j] = new JPanel();
                if ((i + j) % 2 == 0) {
                    if (board[i][j] == 1) {
                        JLabel img_i = new JLabel();
                        img_i = new JLabel(img_black);
                        panel[i][j].setLayout(new GridLayout(1, 1));
                        panel[i][j].add(img_i);
                        panel[i][j].setBackground(Color.black);

                    } else {
                        panel[i][j].setBackground(Color.black);
                    }
                } else {
                    if (board[i][j] == 1) {
                        JLabel img_i = new JLabel();
                        img_i = new JLabel(img_white);
                        panel[i][j].setLayout(new GridLayout(1, 1));
                        panel[i][j].add(img_i);
                        panel[i][j].setBackground(Color.white);
                    } else {
                        panel[i][j].setBackground(Color.white);
                    }
                }
                frame.setLayout(new GridLayout(size, size));
                frame.setTitle("N-Queens");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocation(150, 100);
                frame.setSize(800, 800);
                frame.setVisible(true);
                frame.add(panel[i][j]);
            }
        }
    }
}
