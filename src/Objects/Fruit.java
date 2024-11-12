package Objects;

import java.awt.*;

public class Fruit extends GeneralElement implements Eatable {
    private int value;





    @Override
    public Point getPoint() {
        return null;
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
