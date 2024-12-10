package Objects;
import Graphics.KeyHandler;
import Listener.Observer;
import Sounds.Sound;
import UpDate.Update;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;


public class PacMan extends GeneralElement implements Observer {

    public static PacMan pacMan;
    private int life = 3;
    private int score = 0;
    private int eatTimer = 0;
    private String currentDirection;
    private String preferredDirection ;
    private boolean stopGame,endGame;
    private Update update = Update.newUpdate();



    public String getDirection() {
        return currentDirection;
    }

    public void setDirection(String direction) {
        this.currentDirection = direction;
    }

    public String getPreferredDirection() {
        return preferredDirection;
    }

    public void setPreferredDirection(String preferredDirection) {
        this.preferredDirection = preferredDirection;
    }

    private PacMan() {
       setPoint(new Point(13 * width,21 * height));
       this.speed = 4;
       this.image = new ImageIcon("src/Images/pacmanLeft.gif").getImage() ;
       this.currentDirection = "";
    }
    public synchronized static PacMan newPacman (){
        if (PacMan.pacMan == null){
            PacMan.pacMan = new PacMan();
        }
        return pacMan;
    }

    @Override
    public Point getPoint() {
        return point;
    }
    @Override
    public boolean getIsEaten() {
        return false;
    }
    @Override
    public Image getImage() {
        return image;
    }

    public void setImage(ImageIcon image){
       this.image = image.getImage();
    }

    public boolean getEndGame() {
        return endGame;
    }

    public int getLife() {
        return life;
    }

    public boolean getStopGame() {
        return stopGame;
    }
    //
//    public boolean lossLife(Ghost ghostPink,Ghost ghostGreen,Ghost ghostRed,Ghost ghostYellow) throws InterruptedException {
//        return(lossLife(ghostPink) || lossLife(ghostGreen) || lossLife(ghostRed) || lossLife(ghostYellow));
//    }

//    public boolean lossLife(Ghost ghost) throws InterruptedException {
//        if (checkCollision(ghost)){
//            collisionGhost();
//            ghost.setEaten(eatTimer != 0);
//            ghost.collisionPacMan();
//            return true;
//        }
//        return false;
//    }

    @Override
    public void collisionGhost() {
        if (eatTimer == 0) {
            new Sound("src/Sounds/died.wav");
            life--;
            if (life == 0)stopGame = true;
        }else scoreUp(200);
    }

    public void startAgain(){
        setPoint(new Point(13 * width,21 * height));
    }

    public void pacManEatGhosts(Ghost ghostPink,Ghost ghostGreen,Ghost ghostRed,Ghost ghostYellow){
        pacManEatGhost(ghostPink);
        pacManEatGhost(ghostGreen);
        pacManEatGhost(ghostRed);
        pacManEatGhost(ghostYellow);
    }

    public void pacManEatGhost(Ghost ghost){
        ghost.setEaten(true);
        eatTimer = 1;
        Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    eatTimer = 0;
                    ghost.setEaten(false);
                }
                },8000);
        }

    @Override
    public void updatePointLevel(int speed) {
        this.speed = speed;
        life += 1;
        score = 0;
        stopGame = false;
        endGame = false;
        setPoint(new Point(13 * width,21 * height));
    }

    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver,int x,int y) {
        if(x == -1 && y == -1){
            g.drawImage(image,getPoint().x,getPoint().y,width,height,imageObserver);
        }else {
            int xy = x;
            for (int i = 0; i < life; i++) {
                g.drawImage(new ImageIcon("src/Images/pacmanLeft.gif").getImage(),xy,y,width,height,imageObserver);
            xy+=30;
            }
        }
    }

    public int getSpeed() {
        return speed;
    }


    @Override
    public void scoreUp(int score) {
        this.score += score;
    }

    public void updateMovePacMan(KeyHandler keyHandler) {
        if (keyHandler.isUp()) pacMan.setPreferredDirection("UP");
        if (keyHandler.isDown()) pacMan.setPreferredDirection("DOWN");
        if (keyHandler.isRight()){
            update.flipDirectionRight(pacMan);
            pacMan.setPreferredDirection("RIGHT");
        }
        if (keyHandler.isLeft()){
            update.flipDirectionLeft(pacMan);
            pacMan.setPreferredDirection("LEFT");
        }
        String preferredDirection = pacMan.getPreferredDirection();

        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y,
                tempX = x / width,
                tempY = y / height;


        if (preferredDirection != null && update.canTurn(pacMan,preferredDirection, tempX, tempY, x, y)) {
            pacMan.setDirection(preferredDirection);
            pacMan.setPreferredDirection(null);
        }
        update.moveElement(pacMan,tempX,tempY,x,y);
    }

    @Override
    public void moveLeft() {
//        super.moveLeft();
      //  System.out.println("LEFT");
        System.out.println(point.x);
        point.x -= speed;
        image = new ImageIcon("src/Images/pacmanLeft.gif").getImage();
    }

    @Override
    public void moveRight() {
        super.moveRight();
        image = new ImageIcon("src/Images/pacmanRight.gif").getImage();
    }

    @Override
    public void moveDown() {
        super.moveDown();
        image = new ImageIcon("src/Images/pacmanDown.gif").getImage();
    }

    @Override
    public void moveUp() {
        super.moveUp();
        image = new ImageIcon("src/Images/pacmanUp.gif").getImage();
    }



}

