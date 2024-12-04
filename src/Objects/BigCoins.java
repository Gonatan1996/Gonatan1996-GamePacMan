package Objects;
import Graphics.GamePanel;
import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BigCoins extends Coins{
    public static BigCoins bigCoin;
    int counterImage = 0;
    public ArrayList<BigCoins> bigCoinses = new ArrayList<>();

    private BigCoins() throws FileNotFoundException, AWTException {
        super(0,0);
    }
    public static BigCoins newBigCoin() throws FileNotFoundException, AWTException {
        if (BigCoins.bigCoin == null){
            BigCoins.bigCoin = new BigCoins();
        }
        return BigCoins.bigCoin;
    }


    public BigCoins addBigCoins(BigCoins newBigCoins){
        bigCoinses.add(newBigCoins);
        return newBigCoins;
    }

    public BigCoins(int x, int y) throws FileNotFoundException, AWTException {
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


        return false;
    }

    @Override
    public void collisionPacMan() {
        scoreUp(50);
        new Sound("src/Sounds/eat_coin.wav");
        for (int i = 0; i < bigCoinses.size(); i++) {
            BigCoins bigcoins1 = bigCoinses.get(i);
            if (checkCollision(pacMan)){
                bigCoinses.remove(i);
                GamePanel.changeElement(bigcoins1.getPoint().y / height,bigcoins1.getPoint().x / height,new Empty());
            }
        }
    }

    @Override
    public void updatePointLevel() {
        bigCoinses = new ArrayList<>();
    }
}
