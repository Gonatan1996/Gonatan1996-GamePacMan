package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ghost extends GeneralElement implements Eatable, Speed {
    Random random = new Random();
    private double Speed;
    public boolean red,yellow,green,pink;
    public boolean up, down, left, right;
    String direction = "UP";

    public Ghost(int x,int y,String booleanColor) {
        setPoint(x,y);
        currentColor(booleanColor);

        switch (random.nextInt(5)){

        }
    }
    public String randomMove() {
        final int[] random1 = new int[1];
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                random1[0] = random.nextInt(5);
            }
        }, 5);
        switch (random1[0]) {
            case 1 -> {
                up = true;
                return "UP";
            }
            case 2 -> {
                down = true;
                return "DOWN";
            }
            case 3 -> {
                right = true;
                return "RIGHT";
            }
            case 4 -> {
                left = true;
                return "LEFT";
            }

        }
        return "";
    }

    @Override
    public Point getPoint() {
        return this.point;
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

    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon();
        if(red && ! isEaten)imageIcon = new ImageIcon("src/Images/GhostRed.jpg");
        if (green && ! isEaten)imageIcon = new ImageIcon("src/Images/ghostGreen.jpg");
        if (pink && ! isEaten)imageIcon = new ImageIcon("src/Images/ghostPink.jpg");
        if (yellow && ! isEaten)imageIcon = new ImageIcon("src/Images/ghostYello.jpg");
        if (isEaten)imageIcon = new ImageIcon("src/Images/ghostIten.jpg");
        return imageIcon.getImage();
    }

    public void currentColor(String booleanColor){
        switch (booleanColor){
            case "red" -> red = true;
            case "green" -> green = true;
            case "pink" -> pink = true;
            case "yellow" -> yellow = true;
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public void GhostInDanger(PacMan pacMan){
    if (pacMan.getPoint().x == this.getPoint().x && pacMan.getPoint().y == this.getPoint().y) {
        if (yellow) {
            this.point.x = 12 * 20;
            this.point.y = 13 * 20;
        }
        if (pink) {
            this.point.x = 13 * 20;
            this.point.y = 13 * 20;
        }
        if (red) {
            this.point.x = 14 * 20;
            this.point.y = 13 * 20;
        }
        if (green) {
            this.point.x = 15 * 20;
            this.point.y = 13 * 20;
        }
        pacMan.score += 200;
    }
    }

}
