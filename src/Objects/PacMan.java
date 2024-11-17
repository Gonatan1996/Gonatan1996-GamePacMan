package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class PacMan extends GeneralElement implements Speed{
    public int life = 2;
    public int counter = 0;
    public int score = 0;
    public int eatTimer = 0;
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
       this.image = new ImageIcon("src/Images/pacmanLeft1.jpg").getImage() ;
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
        return image;
    }

    public void setImage(ImageIcon image){
       this.image = image.getImage();
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

    public boolean lossLife(Ghost ghost) {
        if (this.point.x == ghost.getPoint().x && this.point.y == ghost.getPoint().y && eatTimer == 0){
            life--;
            return true;
        }
        return false;
    }

    public void startAgain(){
        setPoint(13 * width,21 * height);
    }

    public void pacManEatGhost(Ghost ghost){
        ghost.isEaten = true;
        eatTimer = 1;
        System.out.println(eatTimer);

        Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    eatTimer = 0;
                    ghost.isEaten = false;
                    System.out.println(eatTimer);
                }
                },8000);
        }

    }

