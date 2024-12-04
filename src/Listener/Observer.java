package Listener;

import Objects.*;

import java.awt.*;
import java.awt.image.ImageObserver;

public interface Observer {
    void drawImages(Graphics g, ImageObserver imageObserver, int x, int y);

    default void collisionPacMan() {

    }

    default void collisionGhost() throws InterruptedException {

    }

    default void collisionFruit() {

    }
    default void scoreUp(int score){}

}
