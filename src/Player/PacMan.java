package Player;

import java.awt.*;

public class PacMan extends GeneralElement implements Speed {
    private int life = 3;


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
        return 0;
    }
}
