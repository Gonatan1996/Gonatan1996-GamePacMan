package Objects;

import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Fruit extends GeneralElement implements Eatable {
    static Random random = new Random();
    public Fruit cherry,apple,orange,melon,strawberry;
    public static String Melon = "melon",Apple = "apple",Cherry = "cherry",Orange = "orange",Strawberry = "strawberry";
    public boolean B_cherry, B_apple, B_orange, B_melon, B_strawberry;
    public boolean show;
    public static ArrayList<Point> randomPoint = new ArrayList<>();
    ImageIcon imageIcon = new ImageIcon();

    public Fruit() {
        cherry = new Fruit(Fruit.Cherry,randomPoint());
        apple = new Fruit(Fruit.Apple,randomPoint());
        orange = new Fruit(Fruit.Orange,randomPoint());
        melon = new Fruit(Fruit.Melon,randomPoint());
        strawberry = new Fruit(Fruit.Strawberry,randomPoint());
    }

    public Fruit(String booleanFruit,Point point) {
        currentFruit(booleanFruit);
        this.point = point;
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
        if (B_cherry) imageIcon = new ImageIcon("src/Images/cherry.png");
        if (B_apple) imageIcon = new ImageIcon("src/Images/apple.png");
        if (B_melon) imageIcon = new ImageIcon("src/Images/melon.png");
        if (B_orange) imageIcon = new ImageIcon("src/Images/orange.png");
        if (B_strawberry) imageIcon = new ImageIcon("src/Images/strawberry.png");
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
        if (x == this.getPoint().x && y == this.getPoint().y && show){
            Sound sound = new Sound("src/Sounds/eat_coin.wav");
        show = false;
        pacMan.score += this.getValue();
        this.isEaten = true;
    }
    }
    public void upDateScoreOfFruits(PacMan pacMan){
        melon.upDateScoreOfFruit(pacMan);
        cherry.upDateScoreOfFruit(pacMan);
        strawberry.upDateScoreOfFruit(pacMan);
        orange.upDateScoreOfFruit(pacMan);
        apple.upDateScoreOfFruit(pacMan);
    }

    public static Point randomPoint(){
        int rand = random.nextInt(Fruit.randomPoint.size());
        int x = Fruit.randomPoint.get(rand).x;
        int y = Fruit.randomPoint.get(rand).y;
        return new Point(x,y);
    }
}
