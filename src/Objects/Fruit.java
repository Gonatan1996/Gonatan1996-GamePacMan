package Objects;

import Listener.Observer;
import Sounds.Sound;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Fruit extends GeneralElement implements Eatable , Observer {
    public static Fruit fruit;
    PacMan pacMan = PacMan.newPacman();
    Coins coins = Coins.newCoins();
    static Random random = new Random();
    public Fruit cherry,apple,orange,melon,strawberry;
    public static String Melon = "melon",Apple = "apple",Cherry = "cherry",Orange = "orange",Strawberry = "strawberry";
    public boolean B_cherry, B_apple, B_orange, B_melon, B_strawberry;
    public boolean show;
    public static ArrayList<Point> randomPoint = new ArrayList<>();
    ImageIcon imageIcon = new ImageIcon();

    private Fruit() {
        cherry = new Fruit(Fruit.Cherry,randomPoint());
        apple = new Fruit(Fruit.Apple,randomPoint());
        orange = new Fruit(Fruit.Orange,randomPoint());
        melon = new Fruit(Fruit.Melon,randomPoint());
        strawberry = new Fruit(Fruit.Strawberry,randomPoint());
    }

    private Fruit(String booleanFruit,Point point) {
        currentFruit(booleanFruit);
        this.point = point;
    }
    public static Fruit newFruit(){
        if(Fruit.fruit == null){
            Fruit.fruit = new Fruit();
        }
        return Fruit.fruit;
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
            new Sound("src/Sounds/eat_coin.wav");
        show = false;
        this.isEaten = true;
            pacMan.score += this.getValue();

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
        System.out.println(Fruit.randomPoint.size());
        int rand = random.nextInt(Fruit.randomPoint.size());
        int x = Fruit.randomPoint.get(rand).x;
        int y = Fruit.randomPoint.get(rand).y;
        return new Point(x,y);
    }

    @Override
    public void collisionPacMan() {
        new Sound("src/Sounds/eat_coin.wav");
        show = false;
        this.isEaten = true;
        pacMan.scoreUp(this.getValue());
    }

    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver, int x, int y) {
        if (x == -1 && y == -1) {
            if (pacMan.score >= 100 && pacMan.score < 250 && !cherry.getIsEaten()) {
                g.drawImage(cherry.getImage(), cherry.getPoint().x, cherry.getPoint().y, fruit.width, fruit.height, imageObserver);
                cherry.show = true;
            } else cherry.show = false;
            if (pacMan.score >= 400 && pacMan.score < 650 && !fruit.strawberry.getIsEaten()) {
                g.drawImage(fruit.strawberry.getImage(), fruit.strawberry.getPoint().x, fruit.strawberry.getPoint().y, fruit.width, fruit.height, imageObserver);
                strawberry.show = true;
            } else strawberry.show = false;
            if (pacMan.score >= 1000 && pacMan.score < 1300 && !fruit.orange.getIsEaten()) {
                g.drawImage(fruit.orange.getImage(), fruit.orange.getPoint().x, fruit.orange.getPoint().y, fruit.width, fruit.height, imageObserver);
                orange.show = true;
            } else orange.show = false;
            if (pacMan.score >= 2000 && pacMan.score < 2200 && !fruit.apple.getIsEaten()) {
                g.drawImage(fruit.apple.getImage(), fruit.apple.getPoint().x, fruit.apple.getPoint().y, fruit.width, fruit.height, imageObserver);
                apple.show = true;
            } else apple.show = false;
            if (pacMan.score >= 3000 && pacMan.score < 3200 && !fruit.melon.getIsEaten()) {
                g.drawImage(fruit.melon.getImage(), fruit.melon.getPoint().x, fruit.melon.getPoint().y, fruit.width, fruit.height, imageObserver);
                melon.show = true;
            } else melon.show = false;
        } else {
            if (pacMan.score >= 100 && pacMan.score < 250 && !cherry.getIsEaten()) {
                g.drawImage(cherry.getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 400 && pacMan.score < 650 && !fruit.strawberry.getIsEaten()) {
                g.drawImage(fruit.strawberry.getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 1000 && pacMan.score < 1300 && !fruit.orange.getIsEaten()) {
                g.drawImage(fruit.orange.getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 2000 && pacMan.score < 2200 && !fruit.apple.getIsEaten()) {
                g.drawImage(fruit.apple.getImage(), x, y, width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 3000 && pacMan.score < 3200 && !fruit.melon.getIsEaten()) {
                g.drawImage(fruit.melon.getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
        }
    }

}
