package Player;

import java.awt.*;

public class Fruit extends GeneralElement implements Eatable {
    private int value;





    @Override
    public Point getPoint(int x, int y) {
        return null;
    }

    @Override
    public boolean getIsEaten() {
        return true;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public int getImageWidth() {
        return width;
    }

    @Override
    public int getImageHeight() {
        return height;
    }

    @Override
    public int getValue() {
        return value;
    }
}
