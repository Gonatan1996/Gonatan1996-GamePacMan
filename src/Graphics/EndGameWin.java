package Graphics;

import Objects.PacMan;

import javax.swing.*;
import java.awt.*;

public class EndGameWin extends JPanel {
    PacMan pacMan;
    JLabel label;

    public EndGameWin(PacMan pacMan) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(0, 50));
        this.pacMan = pacMan;
        this.setVisible(true);
    }
}
