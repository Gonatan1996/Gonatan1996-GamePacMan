package Objects;

import Listener.Observer;
import UpDate.Update;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;

public class Ghost extends GeneralElement implements  Observer {
    public static Ghost ghost;
    private Queue<Ghost> startMove ;
    Random random = new Random();
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private int Speed = 4;
    public static String Red = "red",Yellow = "yellow",Green = "green",Pink = "pink";
    public boolean B_red, B_yellow, B_blue, B_pink;
    public boolean up, down, left, right,canMove,startPoint;
    String direction = "UP";
    public Update update = Update.newUpdate();


    private Ghost() {
        createGhosts();
        startMove = createQueueStart();
    }



    private Ghost(int x, int y, String booleanColor) {
        setPoint(new Point(x,y));
        currentColor(booleanColor);
    }

    public  synchronized static Ghost newGhost(){
        if (Ghost.ghost == null){
            ghost = new Ghost();
        }
        return ghost;
    }
    private void createGhosts(){
        Ghost pink =  new Ghost(13*width,13*height,Ghost.Pink);
        Ghost blue = new Ghost(15*width,13*height,Ghost.Green);
        Ghost yellow = new Ghost(12* width,13*height,Ghost.Yellow);
        Ghost red = new Ghost(14*width,13*height,Ghost.Red);
        ghosts.add(pink);
        ghosts.add(blue);
        ghosts.add(yellow);
        ghosts.add(red);
        setImageStart();
    }

    public String randomMove() {
     //   startPoint();
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
    public void setEaten(boolean eaten) {
        super.setEaten(eaten);
    }

    @Override
    public boolean getIsEaten() {
        return isEaten;
    }


    @Override
    public Image getImage() {
        if (isEaten)return new ImageIcon("src/Images/GhostDanger.jpg").getImage();
        return image;
    }

    private void setImageStart(){
        ghosts.get(0).image = new ImageIcon("src/Images/red startPoint.gif").getImage();
        ghosts.get(1).image = new ImageIcon("src/Images/blue startPoint.gif").getImage();
        ghosts.get(2).image = new ImageIcon("src/Images/pink startPoint.gif").getImage();
        ghosts.get(3).image = new ImageIcon("src/Images/yellow startPoint.gif").getImage();
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


//    public void GhostInDanger(PacMan pacMan) {
//        if (isEaten) {
////            if (pacMan.checkCollision(this)) {
//                upDatePoint();
//                if (B_red){
//                  //  this.startPoint = false;
////                }
////                pacMan.score += 200;
//            }
//        }
//    }

//    public void upDatePoint(){
//        if (B_yellow) {
//            this.point.x = 12 * 20;
//            this.point.y = 13 * 20;
//        }
//        if (B_pink) {
//            this.point.x = 13 * 20;
//            this.point.y = 13 * 20;
//        }
//        if (B_red) {
//            this.point.x = 14 * 20;
//            this.point.y = 13 * 20;
//        }
//        if (B_blue) {
//            this.point.x = 15 * 20;
//            this.point.y = 13 * 20;
//        }
//    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }


    @Override
    public void collisionPacMan()  {
            for (int i = 0; i < ghosts.size(); i++) {
                if (ghosts.get(i).checkCollision(PacMan.newPacman())) {
                    PacMan.newPacman().collisionGhost();
                    ghosts.get(i).setPoint(new Point(14 * width, 13 * height));
            }
        }
    }



    public void startPoint(){
        Ghost ghost1 = pollStart();
        if (ghost1.point.x == 280 && ghost1.point.y == 260){
            up = true;
            startPoint = true;
            canMove = true;
        }
        if (ghost1.point.x > 220 && ghost1.point.x < 280 && ghost1.point.y == 260){
            right = true;
            startPoint = true;
            canMove = true;
        }
        if (ghost1.point.x > 280 && ghost1.point.x <= 300 && ghost1.point.y == 260){
            left = true;
            startPoint = true;
            canMove = true;
        }

    }
    public Queue<Ghost> createQueueStart(){
        Queue<Ghost> start = new LinkedList<>();
        start.add(ghosts.get(0));
        start.add(ghosts.get(1));
        start.add(ghosts.get(2));
        start.add(ghosts.get(3));
        return start;
    }

    public Ghost pollStart(){
        Ghost ghost1 = startMove.peek();
        if (Coins.getCoins().size() == 176)return startMove.poll();
        if (Coins.getCoins().size() == 100)return startMove.poll();
        if (Coins.getCoins().size() == 60 )return startMove.poll();
        return ghost1;
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
    public void updatePointLevel(int speed) {
        ghosts.get(0).setPoint(new Point(13*width,13*height));
        ghosts.get(1).setPoint(new Point(15*width,13*height));
        ghosts.get(2).setPoint(new Point(12*width,13*height));
        ghosts.get(3).setPoint(new Point(14*width,13*height));
    }

    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver,int x,int y) {
        g.drawImage(ghosts.get(0).getImage(),ghosts.get(0).getPoint().x,ghosts.get(0).getPoint().y,width,height, imageObserver);
        g.drawImage(ghosts.get(1).getImage(),ghosts.get(1).getPoint().x,ghosts.get(1).getPoint().y,width,height, imageObserver);
        g.drawImage(ghosts.get(2).getImage(),ghosts.get(2).getPoint().x,ghosts.get(2).getPoint().y,width,height, imageObserver);
        g.drawImage(ghosts.get(3).getImage(),ghosts.get(3).getPoint().x,ghosts.get(3).getPoint().y,width,height, imageObserver);
    }

    public void upDateMoveGhosts(){
        update.ghostRedMove(ghosts.get(0),PacMan.newPacman());
        upDateMoveGhost(ghosts.get(1));
        upDateMoveGhost(ghosts.get(2));
        upDateMoveGhost(ghosts.get(3));
    }

    private void upDateMoveGhost(Ghost ghost){
        int x = ghost.getPoint().x,
                y = ghost.getPoint().y,
                tempX = x / width,
                tempY = y / height;
        ghost.setDirection(ghost.randomMove());
        update.moveElement(ghost,tempX,tempY,x,y);
    }


    @Override
    public void moveLeft() {
        super.moveLeft();

    }

    @Override
    public void moveRight() {
        super.moveRight();
        ghost.setImageLeft_Right();
    }

    @Override
    public void moveUp() {
        super.moveUp();
    }

    @Override
    public void moveDown() {
        super.moveDown();
        setImageUp_Down();
    }
}
