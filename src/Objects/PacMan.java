package Objects;

import javax.swing.*;
import java.awt.*;

public class PacMan extends GeneralElement implements Speed {
    private int life = 3;
    public int counter = 0;
    int score;
    Thread thread;
    String currentDirection ;
    String preferredDirection ;

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getPreferredDirection() {
        return preferredDirection;
    }

    public void setPreferredDirection(String preferredDirection) {
        this.preferredDirection = preferredDirection;
    }

    public PacMan() {
       this.point = new Point(13 * width,21 * height);
       this.image = new ImageIcon("src/Images/pacmanLeft1.jpg") ;
    }

    @Override
    public Point getPoint() {
        return point;
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
        counter++;
        return image.getImage();
    }
    public void setImage(ImageIcon image){
       this.image = image;
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
