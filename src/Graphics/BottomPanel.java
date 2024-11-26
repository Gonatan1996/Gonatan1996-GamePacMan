package Graphics;

import Objects.Fruit;
import Objects.PacMan;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel implements Runnable {
    GamePanel gamePanel;
    PacMan pacMan;
    Fruit fruit;
    Thread thread;


    public BottomPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.pacMan = gamePanel.pacMan;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pacMan.life; i++) {
            ImageIcon imageIcon = new ImageIcon("src/Images/pacmanLeft.gif");
            g.drawImage(imageIcon.getImage(), i * 30,0,25,25,this);
        }
        if (fruit == null)fruit = gamePanel.drawImageFruit(g);
        else
            if (fruit.melon.show) g.drawImage(fruit.melon.getImage(), 535, 0, 25, 25, this);
            if (fruit.cherry.show) g.drawImage(fruit.cherry.getImage(), 535, 0, 25, 25, this);
            if (fruit.strawberry.show) g.drawImage(fruit.strawberry.getImage(), 535, 0, 25, 25, this);
            if (fruit.orange.show) g.drawImage(fruit.orange.getImage(), 535, 0, 25, 25, this);
            if (fruit.apple.show) g.drawImage(fruit.apple.getImage(), 535, 0, 25, 25, this);

    }

    public void startUpDateLife(){
      thread = new Thread(this);
      thread.start();
    }

    @Override
    public void run() {
    while (true){
        repaint();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
