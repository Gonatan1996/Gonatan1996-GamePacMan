package Objects;

import javax.swing.*;
import java.awt.*;

public class PacMan extends GeneralElement implements Speed {
    private int life = 3;

    public PacMan(int x,int y) {
        setPoint(x,y);
    }

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
        return false;
    }

    @Override
    public Image getImage() {
        ImageIcon image1 = new ImageIcon("src/Images/pacman.jpg");
        return image1.getImage();
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
