package Objects;

import UpDate.Update;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Ghost extends GeneralElement implements Eatable, Speed {
    public Ghost pink,blue,yellow,red;
    Random random = new Random();
    private int Speed = 4;
    public static String Red = "red",Yellow = "yellow",Green = "green",Pink = "pink";
    public boolean B_red, B_yellow, B_blue, B_pink;
    public boolean up, down, left, right,canMove,startPoint;
    String direction = "UP";

    public Ghost() {
        pink =  new Ghost(13*width,13*height,Ghost.Pink);
        blue = new Ghost(15*width,13*height,Ghost.Green);
        yellow = new Ghost(12* width,13*height,Ghost.Yellow);
        red = new Ghost(14*width,13*height,Ghost.Red);
        setImageStart();
    }

    public Ghost(int x, int y, String booleanColor) {
        setPoint(x,y);
        currentColor(booleanColor);
    }

    public String randomMove(Coins coins) {
        startPoint(this,coins);
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
        return isEaten;
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
        if (isEaten)return new ImageIcon("src/Images/GhostDanger.jpg").getImage();
        return image;
    }

    private void setImageStart(){
        this.red.image = new ImageIcon("src/Images/red startPoint.gif").getImage();
        this.blue.image = new ImageIcon("src/Images/blue startPoint.gif").getImage();
        this.pink.image = new ImageIcon("src/Images/pink startPoint.gif").getImage();
        this.yellow.image = new ImageIcon("src/Images/yellow startPoint.gif").getImage();
    }

    public void currentColor(String booleanColor){
        switch (booleanColor){
            case "red" -> B_red = true;
            case "green" -> B_blue = true;
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
                upDatePoint();
                if (B_red){
                  //  this.startPoint = false;
                }
                pacMan.score += 200;
            }
        }
    }

    public void upDatePoint(){
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
        if (B_blue) {
            this.point.x = 15 * 20;
            this.point.y = 13 * 20;
        }
    }

    public void startPoint(Ghost ghost, Coins coins){
        if (ghost.point.x == 280 && ghost.point.y == 260){
            startPoint = true;
            canMove = true;
            up = true;
        }
        if (ghost.point.x > 220 && ghost.point.x < 280
        && ghost.point.y == 260 && coins.coins.size() < 100){
            if (B_yellow && coins.coins.size() < 60){
                startPoint = true;
                canMove = true;
                right = true;
            }else if (B_pink){
                startPoint = true;
                canMove = true;
                right = true;
            }
        }
        if (ghost.point.x > 280 && ghost.point.x <= 300
                && ghost.point.y == 260 && coins.coins.size() < 176){
            startPoint = true;
            canMove = true;
            left = true;
        }

    }

    public void setImageLeft_Right(){
        if (B_yellow)this.image = new ImageIcon("src/Images/yellowLeftRight.gif").getImage();
        if (B_pink)this.image = new ImageIcon("src/Images/pinkLeftRight.gif").getImage();
        if (B_blue)this.image = new ImageIcon("src/Images/blueLeftRight.gif").getImage();
        if (B_red)this.image = new ImageIcon("src/Images/redLeftRight.gif").getImage();
    }

    public void setImageUp_Down(){
        if (B_yellow)this.image = new ImageIcon("src/Images/yellow startPoint.gif").getImage();
        if (B_pink)this.image = new ImageIcon("src/Images/pink startPoint.gif").getImage();
        if (B_blue)this.image = new ImageIcon("src/Images/blue startPoint.gif").getImage();
        if (B_red)this.image = new ImageIcon("src/Images/red startPoint.gif").getImage();
    }


}
