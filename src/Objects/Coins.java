package Objects;
import Graphics.GamePanel;
import Listener.Observer;
import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Coins extends GeneralElement implements Observer {
    public static Coins coin;

    private static ArrayList<Coins> coins = new ArrayList<>();

    private PacMan pacMan = PacMan.newPacman();

    private Coins() throws FileNotFoundException, AWTException {
    }
    public Coins(int x, int y) throws FileNotFoundException, AWTException {
        setPoint(new Point(x,y));
    }
    public synchronized static Coins newCoins() throws FileNotFoundException, AWTException {
        if (Coins.coin == null){
        Coins.coin = new Coins();
        }
        return Coins.coin;
    }
    public static ArrayList<Coins> getCoins() {
        return coins;
    }

    public Coins addCoins(Coins newCoins){
        coins.add(newCoins);
        return newCoins;
    }

    @Override
    public boolean getIsEaten() {
        return true;
    }

    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon("src/Images/Coins.png");
        return imageIcon.getImage();
    }

    @Override
    public void updatePointLevel(int speed) {
        coins = new ArrayList<>();
    }
    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver, int x, int y) {
    }

    @Override
    public void collisionPacMan() {
        scoreUp(10);
        new Sound("src/Sounds/pacman_eating2.wav");
        for (int i = 0; i < coins.size(); i++) {
            Coins coins1 = coins.get(i);
            if (coins1.checkCollision(pacMan)) {
                GamePanel.changeElement(coins1.getPoint().y / height,coins1.getPoint().x / height,new Empty());
                coins.remove(i);
            }
            }
        if (coins.isEmpty())PacMan.stopGame = true;
        }

}
