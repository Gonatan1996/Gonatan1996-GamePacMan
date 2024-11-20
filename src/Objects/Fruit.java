package Objects;

import javax.swing.*;
import java.awt.*;

public class Fruit extends GeneralElement implements Eatable {
    public Fruit cherry,apple,orange,melon,strawberry;
    public static String Melon = "melon",Apple = "apple",Cherry = "cherry",Orange = "orange",Strawberry = "strawberry";
    public boolean B_cherry, B_apple, B_orange, B_melon, B_strawberry;
    ImageIcon imageIcon = new ImageIcon();

    public Fruit() {
        cherry = new Fruit(Fruit.Cherry,9*width,22*height);
        apple = new Fruit(Fruit.Apple,18*width,15*height);
        orange = new Fruit(Fruit.Orange,2*width,27*height);
        melon = new Fruit(Fruit.Melon,width,2*height);
        strawberry = new Fruit(Fruit.Strawberry,26*width,2*height);
    }

    public Fruit(String booleanFruit, int x, int y) {
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
        if (B_cherry) imageIcon = new ImageIcon("src/Images/cherry.jpg");
        if (B_apple) imageIcon = new ImageIcon("src/Images/apple.jpg");
        if (B_melon) imageIcon = new ImageIcon("src/Images/melon.jpg");
        if (B_orange) imageIcon = new ImageIcon("src/Images/orange.jpg");
        if (B_strawberry) imageIcon = new ImageIcon("src/Images/strawberry.jpg");
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
        if (B_cherry)return 100;
        if (B_strawberry)return 300;
        if (B_orange)return 500;
        if (B_apple)return 700;
        if (B_melon)return 1000;
        return -1;
    }

    public void currentFruit(String booleanFruit){
        if (booleanFruit.equals(Fruit.Cherry)) B_cherry = true;
        if (booleanFruit.equals(Fruit.Apple)) B_apple = true;
        if (booleanFruit.equals(Fruit.Orange)) B_orange = true;
        if (booleanFruit.equals(Fruit.Melon)) B_melon = true;
        if (booleanFruit.equals(Fruit.Strawberry)) B_strawberry = true;
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
