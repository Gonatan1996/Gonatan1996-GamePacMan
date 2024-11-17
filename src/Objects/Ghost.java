package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ghost extends GeneralElement implements Eatable, Speed {
    Random random = new Random();
    private double Speed;
    public boolean red,yellow,green,pink;
    public boolean up, down, left, right;
    String direction = "UP";

    public Ghost(int x,int y,String booleanColor) {
        setPoint(x,y);
        currentColor(booleanColor);
    }
    public String randomMove() {
        int random11 = random.nextInt(0, 40);
        if (random11 == 0) {
            int random1 = random.nextInt(1, 5);
            switch (random1) {
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
        if(red)imageIcon = new ImageIcon("src/Images/GhostRed.jpg");
        if (green)imageIcon = new ImageIcon("src/Images/ghostGreen.jpg");
        if (pink)imageIcon = new ImageIcon("src/Images/ghostPink.jpg");
        if (yellow)imageIcon = new ImageIcon("src/Images/ghostYello.jpg");
        if (isEaten)imageIcon = new ImageIcon("src/Images/ghostEaten.jpg");
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

}
