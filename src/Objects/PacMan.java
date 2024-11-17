package Objects;

import javax.swing.*;
import java.awt.*;

public class PacMan extends GeneralElement implements Speed {
    public int life = 2;
    public int counter = 0;
    public int score = 0;
    String currentDirection ;
    String preferredDirection ;

    public String getDirection() {
        return currentDirection;
    }

    public void setDirection(String direction) {
        this.currentDirection = direction;
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
       this.currentDirection = "";
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

    public void lossLife(Ghost ghost){
        if (this.point.x == ghost.getPoint().x && this.point.y == ghost.getPoint().y){
            life--;
        }
    }

}
