package Player;

import java.awt.*;

public class BigCoins extends Coins implements Eatable{

    @Override
    public int getValue() {
        return 50;
    }

    @Override
    public Point getPoint(int x, int y) {
        return null;
    }

}
