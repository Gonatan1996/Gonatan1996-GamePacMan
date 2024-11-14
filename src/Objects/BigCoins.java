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
        super(x, y);
    }

    @Override
    public int getValue() {
        return 50;
    }

    @Override
    public Point getPoint() {
        return null;
    }

    @Override
    public void setPoint(int x, int y) {

    }

    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon("src/Images/BigCoins.jpg");
        return imageIcon.getImage();
    }
}
