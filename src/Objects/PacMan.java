package Objects;

import Listener.Observer;
import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;


public class PacMan extends GeneralElement implements Speed,Observer {
    public static PacMan pacMan;
    public int life = 3;
    public int score = 0;
    public int eatTimer = 0;
    String currentDirection;
    String preferredDirection ;
    public boolean stopGame,endGame;



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
       this.image = new ImageIcon("src/Images/pacmanLeft.gif").getImage() ;
       this.currentDirection = "";
    }
    public static PacMan newPacman (){
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

    @Override
    public double getSpeed() {
        return 0;
    }

    public boolean lossLife(Ghost ghostPink,Ghost ghostGreen,Ghost ghostRed,Ghost ghostYellow) throws InterruptedException {
        return(lossLife(ghostPink) || lossLife(ghostGreen) || lossLife(ghostRed) || lossLife(ghostYellow));
    }
//    public boolean ifOnSamePosition(GeneralElement element){
//        Rectangle rectangle1 = new Rectangle(this.point.x,this.point.y,width,height);
//        Rectangle rectangle2 = new Rectangle(element.getPoint().x,element.getPoint().y,width,height);
//        return rectangle1.intersects(rectangle2);
//    }

    public boolean lossLife(Ghost ghost) throws InterruptedException {
        if (checkCollision(ghost)){
            collisionGhost();
            ghost.setEaten(eatTimer != 0);
            ghost.collisionPacMan();
            return true;
        }
        return false;
    }

    @Override
    public void collisionGhost() throws InterruptedException {
        if (eatTimer == 0) {
            new Sound("src/Sounds/died.wav");
            life--;
            Thread.sleep(2000);
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
        ghost.isEaten = true;
        eatTimer = 1;
        Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    eatTimer = 0;
                    ghost.isEaten = false;
                }
                },8000);
        }


    @Override
    public void drawImages(Graphics g, ImageObserver imageObserver,int x,int y) {
        if(x == -1 && y == -1){
            g.drawImage(new ImageIcon("src/Images/pacmanLeft.gif").getImage(),getPoint().x,getPoint().y,width,height,imageObserver);
        }
        g.drawImage(image,getPoint().x,getPoint().y,width,height,imageObserver);
    }


    @Override
    public void scoreUp(int score) {
        this.score += score;
    }
}

