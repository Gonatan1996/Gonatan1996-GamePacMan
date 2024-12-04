package Objects;

import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BigCoins extends Coins{
    public static BigCoins bigCoin;
    int counterImage = 0;
    public ArrayList<BigCoins> bigCoinses = new ArrayList<>();

    private BigCoins() {
        super(0,0);
    }
    public static BigCoins newBigCoin(){
        if (BigCoins.bigCoin == null){
            BigCoins.bigCoin = new BigCoins();
        }
        return BigCoins.bigCoin;
    }


    public BigCoins addBigCoins(BigCoins newBigCoins){
        bigCoinses.add(newBigCoins);
        return newBigCoins;
    }

    public BigCoins(int x, int y) {
        super(x,y);
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public Image getImage() {
        counterImage++;
        if (counterImage == 100)counterImage = 0;
        ImageIcon imageIcon = new ImageIcon("src/Images/BigCoins.png");
        if (counterImage % 10 == 0)imageIcon = new ImageIcon("src/Images/רקע שחור.jpg");
        return imageIcon.getImage();
    }

    public static boolean upDateBigCoins(PacMan pacMan,BigCoins bigCoins,GeneralElement[][] generalElements) {
        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y;

        for (int i = 0; i < bigCoins.bigCoinses.size(); i++) {
            BigCoins bigcoins1 = bigCoins.bigCoinses.get(i);
            if (x == bigcoins1.getPoint().x && y == bigcoins1.getPoint().y){
                bigCoins.bigCoinses.remove(i);
                generalElements[y / bigCoins.height][x / bigCoins.width] = new Empty();
                new Sound("src/Sounds/eat_coin.wav");
                pacMan.score += 50;
                return true;
            }
        }
        return false;
    }

    @Override
    public void updatePointLevel() {
        bigCoinses = new ArrayList<>();
    }
}
