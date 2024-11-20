package Graphics;

import Objects.PacMan;

import javax.swing.*;
import java.awt.*;

public class EndGameLoss extends JPanel {
    PacMan pacMan;
    JLabel label;
    public EndGameLoss(PacMan pacMan) {
        this.setBackground(Color.white);
        this.pacMan = pacMan;
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        label.setText("Sorry try again");
        label.setSize(150,450);
        label.setFont(new Font("Arial",Font.BOLD,24));
        this.add(label);
    }
}
