package Objects;

import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Coins extends GeneralElement implements Eatable{
    public ArrayList<Coins> coins = new ArrayList<>();

    public Coins() {
    }

    public Coins addCoins(Coins newCoins){
        coins.add(newCoins);
        return newCoins;
    }
    public Coins(int x, int y) {
        setPoint(x,y);
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public void setPoint(int x, int y) {
        this.point = new Point(x, y);
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
    public int getValue() {
        return 10;
    }


    public static void upDateCoins(PacMan pacMan,Coins coins,GeneralElement[][] generalElements){
        int x = pacMan.getPoint().x,
            y = pacMan.getPoint().y;

        if (coins.coins.isEmpty())pacMan.endGame = true;
        for (int i = 0; i < coins.coins.size(); i++) {
            Coins coins1 = coins.coins.get(i);
            if (x == coins1.getPoint().x && y == coins1.getPoint().y){
                new Sound("src/Sounds/pacman_eating2.wav");
                coins.coins.remove(i);
                generalElements[y / coins.height][x / coins.width] = new Empty();
                pacMan.score += 10;
            }
        }
    }
}
