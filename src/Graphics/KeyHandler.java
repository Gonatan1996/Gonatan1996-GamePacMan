package Graphics;

import java.awt.event.*;
import java.util.ArrayList;

public class



    KeyHandler implements KeyListener {
    public static KeyHandler keyHandler;
        private boolean up, down, left, right, gameBreak;

        private KeyHandler() {

        }

    public boolean isGameBreak() {
        return gameBreak;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public synchronized static KeyHandler newKeyHandler(){
            if (KeyHandler.keyHandler == null){
                KeyHandler.keyHandler = new KeyHandler();
            }
            return KeyHandler.keyHandler;
        }

    @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_ENTER -> {
                        if (!gameBreak) gameBreak = true;
                        else gameBreak = false;
                    }
                    case KeyEvent.VK_UP -> {
                        gameBreak = false;
                        up = true;
                        flipToFalse("Up");
                       // System.out.println("up");
                    }
                    case KeyEvent.VK_DOWN -> {
                        gameBreak = false;
                        down = true;
                        flipToFalse("Down");
                       // System.out.println("down");
                    }
                    case KeyEvent.VK_LEFT -> {
                        gameBreak = false;
                        left = true;
                     //   System.out.println("left");
                        flipToFalse("Left");
                    }
                    case KeyEvent.VK_RIGHT -> {
                        gameBreak = false;
                        right = true;
                    //    System.out.println("right");
                        flipToFalse("Right");
                    }
                }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        public void flipToFalse(String tru){
            if (tru.equals("Up")){
                down = false;
                left = false;
                right = false;
            }
            else if (tru.equals("Down")){
                up = false;
                left = false;
                right = false;
            }
            else if (tru.equals("Left")){
                up = false;
                down = false;
                right = false;
            }
            else if (tru.equals("Right")){
                up = false;
                down = false;
                left = false;
            }
        }

}