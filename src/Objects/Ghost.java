package Objects;

import javax.swing.*;
import java.awt.*;

public class Ghost extends GeneralElement implements Eatable, Speed{
    private double Speed;

    public Ghost(int x,int y) {
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

    public Image getImagePink() {
        ImageIcon image1 = new ImageIcon("src/Images/ghostPink.jpg");
        return image1.getImage();
    }

    public Image getImageYellow() {
        ImageIcon image1 = new ImageIcon("src/Images/ghostYello.jpg");
        return image1.getImage();
    }

    public Image getImageGreen() {
        ImageIcon image1 = new ImageIcon("src/Images/ghostGreen.jpg");
        return image1.getImage();
    }

    public Image getImageRed() {
        ImageIcon image1 = new ImageIcon("src/Images/GhostRed.jpg");
        return image1.getImage();
    }
    public Image getImageEaten() {
        ImageIcon image1 = new ImageIcon("src/Images/ghostEaten.jpg");
        return image1.getImage();
    }

}
