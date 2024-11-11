package Player;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setBackground(Color.BLUE);
        JFrame frame = new JFrame("Pac Man");
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        gamePanel.setBounds(0,0, 50 , 50);
        frame.add(gamePanel);
    }
}
