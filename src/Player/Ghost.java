package Player;

import java.awt.*;

public class Ghost extends GeneralElement implements Eatable, Speed{
    private double Speed;


    @Override
    public Point getPoint(int x, int y) {
        return null;
    }

    @Override
    public boolean getIsEaten() {
        return false;
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
    public double getSpeed() {
        return Speed;
    }

    @Override
    public int getValue() {
        return 200;
    }
}
