package Objects;

import Listener.Observer;
import Sounds.Sound;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Fruit extends GeneralElement implements Observer {
    public static Fruit fruit;
    PacMan pacMan = PacMan.newPacman();
    static Random random = new Random();
    public ArrayList<Fruit> fruits = new ArrayList<>();
    public static String Melon = "melon",Apple = "apple",Cherry = "cherry",Orange = "orange",Strawberry = "strawberry";
    public boolean B_cherry, B_apple, B_orange, B_melon, B_strawberry,show;
    public static ArrayList<Point> randomPoint = new ArrayList<>();

    private Fruit() {
    createFruits();
    }

    private Fruit(String booleanFruit,Point point) {
        currentFruit(booleanFruit);
        this.point = point;
    }
    public synchronized static Fruit newFruit(){
        if(Fruit.fruit == null){
            Fruit.fruit = new Fruit();
        }
        return Fruit.fruit;
    }

    private void createFruits(){
        Fruit cherry, apple, orange, melon, strawberry;
        cherry = new Fruit(Fruit.Cherry,randomPoint());
        apple = new Fruit(Fruit.Apple,randomPoint());
        orange = new Fruit(Fruit.Orange,randomPoint());
        melon = new Fruit(Fruit.Melon,randomPoint());
        strawberry = new Fruit(Fruit.Strawberry,randomPoint());
        fruits.add(cherry);
        fruits.add(apple);
        fruits.add(orange);
        fruits.add(melon);
        fruits.add(strawberry);
    }
    @Override
    public boolean getIsEaten() {
        return isEaten;
    }

    @Override
    public Image getImage() {
        System.out.println(fruits.size());
        if (B_cherry)this.image = new ImageIcon("src/Images/cherry.png").getImage();
        if (B_apple)this.image = new ImageIcon("src/Images/apple.png").getImage();
        if (B_orange)this.image = new ImageIcon("src/Images/orange.png").getImage();
        if (B_melon)this.image = new ImageIcon("src/Images/melon.png").getImage();
        if (B_strawberry)this.image = new ImageIcon("src/Images/strawberry.png").getImage();
        return image;
    }


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


    public static Point randomPoint(){
        int rand = random.nextInt(Fruit.randomPoint.size());
        int x = Fruit.randomPoint.get(rand).x;
        int y = Fruit.randomPoint.get(rand).y;
        return new Point(x,y);
    }

    @Override
    public void collisionPacMan() {
        new Sound("src/Sounds/eat_coin.wav");
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).checkCollision(pacMan) && fruits.get(i).show){
                pacMan.scoreUp(fruits.get(i).getValue());
                fruits.remove(fruits.get(i));
            }
        }
    }

    @Override
    public void updatePointLevel(int speed) {

    }
    private void ifShow(){
        fruits.get(4).show = pacMan.score >= 100 && pacMan.score < 250;
        fruits.get(3).show = pacMan.score >= 400 && pacMan.score < 650;
        fruits.get(2).show = pacMan.score >= 1000 && pacMan.score < 1300;
        fruits.get(1).show = pacMan.score >= 2000 && pacMan.score < 2200;
        fruits.get(0).show = pacMan.score >= 3000 && pacMan.score < 3200;
    }

    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver, int x, int y) {
        ifShow();

            if (fruits.get(4).show){
                if (x == -1 && y == -1){
                g.drawImage(fruits.get(4).getImage(), fruits.get(4).getPoint().x, fruits.get(4).getPoint().y, fruit.width, fruit.height, imageObserver);
                }else {
                    g.drawImage(fruits.get(4).getImage(),x,y, fruit.width, fruit.height, imageObserver);
                }

            if (fruits.get(3).show) {
                g.drawImage(fruits.get(3).getImage(), fruits.get(3).getPoint().x, fruits.get(3).getPoint().y, fruit.width, fruit.height, imageObserver);
            }
            if (fruits.get(2).show) {
                g.drawImage(fruits.get(2).getImage(), fruits.get(2).getPoint().x, fruits.get(2).getPoint().y, fruit.width, fruit.height, imageObserver);
            }
            if (fruits.get(1).show) {
                g.drawImage(fruits.get(1).getImage(), fruits.get(1).getPoint().x, fruits.get(1).getPoint().y, fruit.width, fruit.height, imageObserver);
            }
            if (fruits.get(0).show) {
                g.drawImage(fruits.get(0).getImage(), fruits.get(0).getPoint().x, fruits.get(0).getPoint().y, fruit.width, fruit.height, imageObserver);
            }
        }else {
            if (fruits.get(4).show) {
            }
            if (pacMan.score >= 400 && pacMan.score < 650) {
                g.drawImage(fruits.get(2).getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 1000 && pacMan.score < 1300) {
                g.drawImage(fruits.get(1).getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 2000 && pacMan.score < 2200) {
                g.drawImage(fruits.get(1).getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }
            if (pacMan.score >= 3000 && pacMan.score < 3200) {
                g.drawImage(fruits.get(0).getImage(), x, y, fruit.width, fruit.height, imageObserver);
            }



        }
    }


}
