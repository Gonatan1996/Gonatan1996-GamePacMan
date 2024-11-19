package Graphics;

import Objects.PacMan;

import javax.swing.*;
import java.awt.*;

public class BottomSpace extends JPanel implements Runnable {
    PacMan pacMan;
    Thread thread;


    public BottomSpace(PacMan pacMan) {
        this.pacMan = pacMan;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pacMan.life; i++) {
            ImageIcon imageIcon = new ImageIcon("src/Images/pacmanLeft1.jpg");
            g.drawImage(imageIcon.getImage(), i * 30,0,25,25,this);
        }

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
