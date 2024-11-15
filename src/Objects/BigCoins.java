package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BigCoins extends Coins implements Eatable{
    ArrayList<BigCoins> bigCoins = new ArrayList<>();

    public BigCoins() {

    }

    public BigCoins addBigCoins(BigCoins newBigCoins){
        bigCoins.add(newBigCoins);
        return newBigCoins;
    }
    public BigCoins(int x, int y) {
        setPoint(x,y);
    }

    @Override
    public int getValue() {
        return 50;
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public void setPoint(int x, int y) {
    this.point = new Point(x,y);
    }
    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon("src/Images/BigCoins.jpg");
        return imageIcon.getImage();
    }
    public static void upDateBigCoins(PacMan pacMan,BigCoins bigCoins,GeneralElement[][] generalElements){
        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y;

        for (int i = 0; i < bigCoins.bigCoins.size(); i++) {
            BigCoins bigcoins1 = bigCoins.bigCoins.get(i);
            if (x == bigcoins1.getPoint().x && y == bigcoins1.getPoint().y){
                bigCoins.bigCoins.remove(i);
                generalElements[y / bigCoins.height][x / bigCoins.width] = new Empty();
                pacMan.score += 200;
            }
        }
    }

}
