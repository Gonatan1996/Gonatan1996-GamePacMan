package Graphics;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        GamePanel gamePanel = new GamePanel();
        this.setLayout(new BorderLayout());
        TopPanel topSpace = new TopPanel(gamePanel.pacMan);
        topSpace.upDateScoreStart();
        BottomSpace bottomSpace = new BottomSpace(gamePanel.pacMan);
        bottomSpace.startUpDateLife();
        this.add(topSpace, BorderLayout.NORTH);      // פס ריק עליון
        this.add(gamePanel, BorderLayout.CENTER);    // פאנל המשחק במרכז
        this.add(bottomSpace, BorderLayout.SOUTH);   // פס ריק תחתון
        this.setSize(575, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        gamePanel.startGame();
    }
}
