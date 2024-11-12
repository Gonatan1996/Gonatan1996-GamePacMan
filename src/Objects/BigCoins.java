package Objects;

import java.awt.*;

public class BigCoins extends Coins implements Eatable{

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

}
