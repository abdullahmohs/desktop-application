package n_queen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel("Welcome To N-Queens");
    private JLabel l_chosse = new JLabel("Please Chosse Size ");
    private JLabel img1;
    private ImageIcon img;
    private String country[] = {"4×4", "5×5", "6×6", "7×7", "8×8"};
    private JComboBox comb = new JComboBox(country);
    private JButton button = new JButton("Start");
    private ImageIcon icon = new ImageIcon("icon.png");

    GUI() {
        try {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
            img = new ImageIcon(getClass().getResource("design.jpg"));
            img1 = new JLabel(img);
            img1.setSize(270, 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.setTitle("N-Queens");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(300, 200);
        this.setSize(800, 600);
        this.setVisible(true);
        panel.setLayout(null);
        label.setBounds(270, 100, 300, 50);
        img1.setBounds(250, 150, 300, 200);
        comb.setBounds(350, 400, 80, 30);
        l_chosse.setBounds(290, 270, 300, 200);
        button.setBounds(350, 470, 80, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        l_chosse.setFont(new Font("Arial", Font.PLAIN, 24));
        comb.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        comb.setForeground(Color.BLACK);
        l_chosse.setForeground(Color.BLACK);
        label.setForeground(Color.BLACK);
        button.setForeground(Color.BLACK);
        button.addActionListener(this);
        this.add(panel);
        panel.add(label);
        panel.add(img1);
        panel.add(l_chosse);
        panel.add(comb);
        panel.add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            design.set_number(comb.getSelectedItem().toString());
            new design();
            this.dispose();
        }
    }
}
