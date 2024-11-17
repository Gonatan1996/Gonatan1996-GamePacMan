package Graphics;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(){
        GamePanel gamePanel = new GamePanel();
        this.setLayout(new BorderLayout());
        TopPanel topSpace = new TopPanel(gamePanel.pacMan);
        topSpace.upDateScoreStart();
        topSpace.setBackground(Color.black);
        BottomSpace bottomSpace = new BottomSpace(gamePanel.pacMan);
        bottomSpace.startUpDateLife();
        bottomSpace.setBackground(Color.BLACK);
        topSpace.setPreferredSize(new Dimension(0, 50));
        bottomSpace.setPreferredSize(new Dimension(0, 50));
        this.add(topSpace, BorderLayout.NORTH);      // פס ריק עליון
        this.add(gamePanel, BorderLayout.CENTER);    // פאנל המשחק במרכז
        this.add(bottomSpace, BorderLayout.SOUTH);   // פס ריק תחתון
        gamePanel.setBackground(Color.BLACK);
        this.setSize(715, 865);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
        gamePanel.startGame();
    }
}
