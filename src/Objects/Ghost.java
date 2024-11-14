package Objects;

import javax.swing.*;
import java.awt.*;

public class Ghost extends GeneralElement implements Eatable, Speed{
    private double Speed;
    public boolean up, down, left, right;
    public boolean red,yellow,green,pink;

    public Ghost(int x,int y,String booleanColor) {
        setPoint(x,y);
        currentColor(booleanColor);
    }
    public void randomMove(int random){
        switch (random){
            case 1 -> up = true;
            case 2 -> down = true;
            case 3 -> left = true;
            case 4 -> right = true;
        }
        flipToFalse();
    }
    public void flipToFalse(){
        if (up){
            down = false;
            left = false;
            right = false;
        }
        else if (down){
            up = false;
            left = false;
            right = false;
        }
        else if (left){
            up = false;
            down = false;
            right = false;
        }
        else if (right){
            up = false;
            down = false;
            left = false;
        }
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

}
