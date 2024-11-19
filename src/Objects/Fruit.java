package Objects;

import javax.swing.*;
import java.awt.*;

public class Fruit extends GeneralElement implements Eatable {
    public boolean cherry,apple,orange,melon,strawberry;
    ImageIcon imageIcon = new ImageIcon();

    public Fruit(String booleanFruit,int x,int y) {
        currentFruit(booleanFruit);
        setPoint(x,y);
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
    public Image getImage() {
        if (cherry) imageIcon = new ImageIcon("src/Images/cherry.jpg");
        if (apple) imageIcon = new ImageIcon("src/Images/apple.jpg");
        if (melon) imageIcon = new ImageIcon("src/Images/melon.jpg");
        if (orange) imageIcon = new ImageIcon("src/Images/orange.jpg");
        if (strawberry) imageIcon = new ImageIcon("src/Images/strawberry.jpg");
        return imageIcon.getImage();
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
    public int getValue() {
        if (cherry)return 100;
        if (strawberry)return 300;
        if (orange)return 500;
        if (apple)return 700;
        if (melon)return 1000;
        return -1;
    }
    public void currentFruit(String booleanFruit){
        switch (booleanFruit){
            case "cherry" ->cherry = true;
            case "apple" -> apple = true;
            case "orange" -> orange = true;
            case "melon" -> melon = true;
            case "strawberry" -> strawberry = true;
        }
    }
    public void upDateScoreOfFruit(PacMan pacMan){
        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y;
        if (x == this.getPoint().x && y == this.getPoint().y){
        pacMan.score += this.getValue();
        this.isEaten = true;
    }
    }
}
