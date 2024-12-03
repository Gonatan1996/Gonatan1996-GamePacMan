package Objects;

import Listener.BigCoinEat;
import Listener.DrawImage;
import Listener.Observer;
import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;


public class PacMan extends GeneralElement implements Speed, DrawImage,Observer {
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
       this.point = new Point(13 * width,21 * height);
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
    public void setPoint(int x, int y) {
        this.point = new Point(x, y);
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
        if (lossLife(ghostPink) || lossLife(ghostGreen) || lossLife(ghostRed) || lossLife(ghostYellow)){
            ghostPink.upDatePoint();
            ghostYellow.upDatePoint();
            ghostRed.upDatePoint();
            ghostGreen.upDatePoint();
            return true;
        }
        return false;
    }
    public boolean ifOnSamePosition(Ghost ghost){
        Rectangle rectangle1 = new Rectangle(this.point.x,this.point.y,width,height);
        Rectangle rectangle2 = new Rectangle(ghost.getPoint().x,ghost.getPoint().y,width,height);
        return rectangle1.intersects(rectangle2);
    }

    public boolean lossLife(Ghost ghost) throws InterruptedException {
        if (ifOnSamePosition(ghost) && eatTimer == 0){
            new Sound("src/Sounds/died.wav");
            life--;
            Thread.sleep(2000);
            if (ghost.B_red)ghost.startPoint = false;
            return true;
        }else{
            ghost.GhostInDanger(this);
        }
        return false;
    }

    public void startAgain(){
        setPoint(13 * width,21 * height);
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
    public void drawImage(Graphics g, ImageObserver imageObserver) {
        g.drawImage(image,getPoint().x,getPoint().y,width,height,imageObserver);
    }

    @Override
    public Object notify(Object obg) {
        return null;
    }
}

