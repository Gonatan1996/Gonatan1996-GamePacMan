package Graphics;

import Objects.PacMan;

import javax.swing.*;
import java.awt.event.*;

    public class



    KeyHandler implements KeyListener {
        boolean up, down, left, right;



        @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_UP -> {
                    up = true;
                    flipToFalse("Up");
                }
                case KeyEvent.VK_DOWN -> {
                    down = true;
                    flipToFalse("Down");
                }
                case KeyEvent.VK_LEFT -> {
                    left = true;
                    flipToFalse("Left");
                }
                case KeyEvent.VK_RIGHT -> {
                    right = true;
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