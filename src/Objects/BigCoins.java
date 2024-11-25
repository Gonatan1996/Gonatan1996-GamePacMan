package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BigCoins extends Coins implements Eatable{
    int counterImage = 0;
    public ArrayList<BigCoins> bigCoins = new ArrayList<>();

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
        counterImage++;
        if (counterImage == 100)counterImage = 0;
        ImageIcon imageIcon = new ImageIcon("src/Images/BigCoins.jpg");
        if (counterImage % 10 == 0)imageIcon = new ImageIcon("src/Images/רקע שחור.jpg");
        return imageIcon.getImage();
    }
    public static boolean upDateBigCoins(PacMan pacMan,BigCoins bigCoins,GeneralElement[][] generalElements) {
        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y;

        for (int i = 0; i < bigCoins.bigCoins.size(); i++) {
            BigCoins bigcoins1 = bigCoins.bigCoins.get(i);
            if (x == bigcoins1.getPoint().x && y == bigcoins1.getPoint().y){
                bigCoins.bigCoins.remove(i);
                generalElements[y / bigCoins.height][x / bigCoins.width] = new Empty();
                pacMan.score += 50;
                return true;
            }
        }
        return false;
    }

}
