package Graphics;

import java.awt.event.*;

    public class



    KeyHandler implements KeyListener {
        boolean up, down, left, right,GameBreak = true;



        @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_ENTER -> {
                    if (!GameBreak) GameBreak = true;
                    else GameBreak = false;
                }
                case KeyEvent.VK_UP -> {
                    GameBreak = false;
                    up = true;
                    flipToFalse("Up");
                }
                case KeyEvent.VK_DOWN -> {
                    GameBreak = false;
                    down = true;
                    flipToFalse("Down");
                }
                case KeyEvent.VK_LEFT -> {
                    GameBreak = false;
                    left = true;
                    flipToFalse("Left");
                }
                case KeyEvent.VK_RIGHT -> {
                    GameBreak = false;
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