package Graphics;

import Objects.PacMan;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel implements Runnable{
    PacMan pacMan;
    JTextField textScore = new JTextField();
    Thread thread;


    public TopPanel(PacMan pacMan) {
        this.pacMan = pacMan;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        textScore = new JTextField( "Score : " + pacMan.score);
        textScore.setSize(150,25);
        textScore.setFont(new Font("",Font.BOLD,24));
        textScore.setEditable(true);
        this.add(textScore);
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
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
