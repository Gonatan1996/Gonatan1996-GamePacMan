package Graphics;

import Objects.PacMan;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel implements Runnable{
    PacMan pacMan;
    JLabel label = new JLabel();
    Thread thread;


    public TopPanel(PacMan pacMan) {
        this.pacMan = pacMan;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        label.setText("Score : " + pacMan.score);
        label.setSize(150,50);
        label.setFont(new Font("Arial",Font.BOLD,24));
        this.add(label);
    }

    public void upDateScoreStart(){
       thread = new Thread(this);
       thread.start();
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
