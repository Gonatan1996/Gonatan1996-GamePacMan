package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Ghost extends GeneralElement implements Eatable, Speed {
    public Ghost pink,green,yellow,red;
    Random random = new Random();
    private double Speed;
    public static String Red = "red",Yellow = "yellow",Green = "green",Pink = "pink";
    public boolean B_red, B_yellow,B_green, B_pink;
    public boolean up, down, left, right,canMove,startPoint;
    String direction = "UP";

    public Ghost() {
        pink =  new Ghost(13*width,13*height,Ghost.Pink);
        green = new Ghost(15*width,13*height,Ghost.Green);
        yellow = new Ghost(12* width,13*height,Ghost.Yellow);
        red = new Ghost(14*width,13*height,Ghost.Red);
    }

    public Ghost(int x, int y, String booleanColor) {
        setPoint(x,y);
        currentColor(booleanColor);

        switch (random.nextInt(5)){

        }
    }
    public String randomMove() {
        startPoint(this);
        if (startPoint) {
            if (canMove) {
                if (up) return "UP";
                if (down) return "DOWN";
                if (left) return "LEFT";
                if (right) return "RIGHT";
            }
            int random1 = random.nextInt(5);

            switch (random1) {
                case 1 -> {
                    up = true;
                    canMove = true;
                    return "UP";
                }
                case 2 -> {
                    down = true;
                    canMove = true;
                    return "DOWN";
                }
                case 3 -> {
                    right = true;
                    canMove = true;
                    return "RIGHT";
                }
                case 4 -> {
                    left = true;
                    canMove = true;
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
        if(B_red && ! isEaten)imageIcon = new ImageIcon("src/Images/GhostRed.jpg");
        if (B_green && ! isEaten)imageIcon = new ImageIcon("src/Images/ghostGreen.jpg");
        if (B_pink && ! isEaten)imageIcon = new ImageIcon("src/Images/ghostPink.jpg");
        if (B_yellow && ! isEaten)imageIcon = new ImageIcon("src/Images/ghostYello.jpg");
        if (isEaten)imageIcon = new ImageIcon("src/Images/ghostIten.jpg");
        return imageIcon.getImage();
    }

    public void currentColor(String booleanColor){
        switch (booleanColor){
            case "red" -> B_red = true;
            case "green" -> B_green = true;
            case "pink" -> B_pink = true;
            case "yellow" -> B_yellow = true;
        }
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public void GhostInDanger(PacMan pacMan) {
        if (isEaten) {
            if (pacMan.getPoint().x == this.getPoint().x && pacMan.getPoint().y == this.getPoint().y) {
                if (B_yellow) {
                    this.point.x = 12 * 20;
                    this.point.y = 13 * 20;
                }
                if (B_pink) {
                    this.point.x = 13 * 20;
                    this.point.y = 13 * 20;
                }
                if (B_red) {
                    this.point.x = 14 * 20;
                    this.point.y = 13 * 20;
                }
                if (B_green) {
                    this.point.x = 15 * 20;
                    this.point.y = 13 * 20;
                }
                pacMan.score += 200;
            }
        }
    }

    public void startPoint(Ghost ghost){
        if (ghost.point.x == 280 && ghost.point.y == 260){
            startPoint = true;
            canMove = true;
            up = true;
        }
        if (ghost.point.x > 220 && ghost.point.x < 280
        && ghost.point.y == 260){
            startPoint = true;
            canMove = true;
            right = true;
        }
        if (ghost.point.x > 280 && ghost.point.x <= 300
                && ghost.point.y == 260){
            startPoint = true;
            canMove = true;
            left = true;
        }

    }

}
