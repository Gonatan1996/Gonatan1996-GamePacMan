package Objects;

import Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class PacMan extends GeneralElement implements Speed{
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

    public PacMan() {
       this.point = new Point(13 * width,21 * height);
       this.image = new ImageIcon("src/Images/pacmanLeft.gif").getImage() ;
       this.currentDirection = "";
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

    public boolean lossLife(Ghost ghost) throws InterruptedException {
        if (this.point.x == ghost.getPoint().x && this.point.y == ghost.getPoint().y && eatTimer == 0){
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

    }

