package Objects;

import Listener.DrawImage;
import Listener.GhostEat;
import Listener.Observer;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;

public class Ghost extends GeneralElement implements Eatable, Speed, DrawImage , Observer<Graphics, ImageObserver, NullType>, GhostEat {
    public static Ghost ghost;

    public Ghost pink,blue,yellow,red;
    Random random = new Random();
    private int Speed = 4;
    public static String Red = "red",Yellow = "yellow",Green = "green",Pink = "pink";
    public boolean B_red, B_yellow, B_blue, B_pink;
    public boolean up, down, left, right,canMove,startPoint;
    String direction = "UP";
    public Point point;

    private Ghost() {
        pink =  new Ghost(13*width,13*height,Ghost.Pink);
        blue = new Ghost(15*width,13*height,Ghost.Green);
        yellow = new Ghost(12* width,13*height,Ghost.Yellow);
        red = new Ghost(14*width,13*height,Ghost.Red);
        setImageStart();
    }



    private Ghost(int x, int y, String booleanColor) {
        setPoint(x,y);
        currentColor(booleanColor);
    }
    public static Ghost newGhost(){
        if (Ghost.ghost == null){
            ghost = newGhost();
        }
        return ghost;
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
            if (pacMan.ifOnSamePosition(this)) {
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


    @Override
    public void drawImage(Graphics g, ImageObserver imageObserver) {
        g.drawImage(red.getImage(),red.getPoint().x,red.getPoint().y,width,height, imageObserver);
        g.drawImage(yellow.getImage(),yellow.getPoint().x,yellow.getPoint().y,width,height, imageObserver);
        g.drawImage(pink.getImage(),pink.getPoint().x,pink.getPoint().y,width,height, imageObserver);
        g.drawImage(blue.getImage(),blue.getPoint().x,blue.getPoint().y,width,height, imageObserver);
    }



    @Override
    public void ghostEat(boolean eat) {

    }

    @Override
    public NullType notify(Graphics obg) {
        return null;
    }

    @Override
    public NullType notify(Graphics g, ImageObserver imageObserver) {
        g.drawImage(red.getImage(),red.getPoint().x,red.getPoint().y,width,height, imageObserver);
        g.drawImage(yellow.getImage(),yellow.getPoint().x,yellow.getPoint().y,width,height, imageObserver);
        g.drawImage(pink.getImage(),pink.getPoint().x,pink.getPoint().y,width,height, imageObserver);
        g.drawImage(blue.getImage(),blue.getPoint().x,blue.getPoint().y,width,height, imageObserver);
        return null;
    }
}
