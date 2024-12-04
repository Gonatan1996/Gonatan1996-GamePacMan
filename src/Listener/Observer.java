package Listener;

import Objects.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface Observer {
    default void updatePointLevel(int speed){

    }
    default void drawImages(Graphics g, ImageObserver imageObserver, int x, int y){

    }

    default void collisionPacMan() throws InterruptedException {

    }

    default void collisionGhost() throws InterruptedException {

    }
    default void collisionCoins() throws InterruptedException {

    }
    default void collisionBigCoins() throws InterruptedException {

    }

    default void collisionFruit() {

    }
    default void scoreUp(int score){
        PacMan.newPacman().scoreUp(score);
    }

}
