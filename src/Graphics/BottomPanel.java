package Graphics;

import Listener.Observer;
import Objects.Fruit;
import Objects.PacMan;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BottomPanel extends JPanel implements Runnable {
    GamePanel gamePanel = GamePanel.newGamePanel();
    PacMan pacMan = PacMan.newPacman();
    Fruit fruit = Fruit.newFruit();
    Thread thread;
    ArrayList<Observer> listener = new ArrayList<>();


    public BottomPanel() throws FileNotFoundException, AWTException {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(0, 50));
        listener.add(pacMan);
        listener.add(fruit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(listener.getFirst() +"");
        for (int i = 0; i < pacMan.life; i++) {
            listener.getFirst().drawImages(g,this,0,0);
        }
        System.out.println(listener.getLast() + "");
        listener.getLast().drawImages(g,this,535,0);

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
