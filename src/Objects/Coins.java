package Objects;

import Listener.Observer;
import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Coins extends GeneralElement implements Observer {

    PacMan pacMan = PacMan.newPacman();
    public static Coins coin;
    public static ArrayList<Coins> coins = new ArrayList<>();

    private Coins() {
    }
    public Coins(int x, int y) {
        setPoint(new Point(x,y));
    }
    public static Coins newCoins(){
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
    public void updatePointLevel() {
        coins = new ArrayList<>();
    }
    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver, int x, int y) {
    }

    @Override
    public void collisionPacMan() {
        scoreUp(10);
        new Sound("src/Sounds/pacman_eating2.wav");
        if (coins.isEmpty())PacMan.endGame = true;
        for (int i = 0; i < coins.size(); i++) {
            Coins coins1 = coins.get(i);
            if (coins1.checkCollision(pacMan)) {

            }
                new Sound("src/Sounds/pacman_eating2.wav");
                coins.remove(i);

               // generalElements[y / coins.height][x / coins.width] = new Empty();
            }
        }

}
