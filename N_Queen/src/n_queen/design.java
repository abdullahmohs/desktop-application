package n_queen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public final class design extends Frame implements KeyListener {

    private JFrame frame = new JFrame();
    private static String number;
    private ImageIcon img_black;
    private ImageIcon img_white;
    private JPanel panel[][];
    private solving s1 = new solving();
    private ImageIcon icon = new ImageIcon("icon.png");

    design() {

        frame.addKeyListener(this);
        try {
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
            img_black = new ImageIcon(getClass().getResource("king_black.jpg"));
            img_white = new ImageIcon(getClass().getResource("king_white.jpg"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (number != null) {
            JPanel panel[][] = new JPanel[Integer.parseInt(get_number())][Integer.parseInt(get_number()) + 1];
            for (int i = 0; i < Integer.parseInt(get_number()); i++) {
                for (int j = 0; j < Integer.parseInt(get_number()) + 1; j++) {
                    panel[i][j] = new JPanel();
                    if ((i + j) % 2 == 0) {
                        if (j == 0) {
                            JLabel img_i = new JLabel();
                            img_i = new JLabel(img_black);
                            panel[i][0].setLayout(new GridLayout(1, 1));
                            panel[i][0].add(img_i);
                            panel[i][j].setBackground(Color.black);

                        } else {
                            panel[i][j].setBackground(Color.black);
                        }
                    } else {
                        if (j == 0) {
                            JLabel img_i = new JLabel();
                            img_i = new JLabel(img_white);
                            panel[i][0].setLayout(new GridLayout(1, 1));
                            panel[i][0].add(img_i);
                            panel[i][j].setBackground(Color.white);
                        } else {
                            panel[i][j].setBackground(Color.white);
                        }
                    }
                    frame.setLayout(new GridLayout(Integer.parseInt(get_number()), Integer.parseInt(get_number()) + 1));
                    frame.setTitle("N-Queens");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setLocation(200, 100);
                    frame.setSize(1000, 800);
                    frame.setVisible(true);
                    frame.add(panel[i][j]);
                }
            }
        }
    }

    static public void set_number(String num) {
        number = num;
    }

    public String get_number() {
        number = number.substring(0, 1);
        return number;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            s1.solveNQwiththreads(Integer.parseInt(get_number()));
            frame.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
