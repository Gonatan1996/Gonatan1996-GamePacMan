package Graphics;

import java.awt.event.*;

    public class



    KeyHandler implements KeyListener {
        public boolean up, down, left, right, gameBreak = true;



        @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_ENTER -> {
                    if (!gameBreak) gameBreak = true;
                    else gameBreak = false;
                }
                case KeyEvent.VK_UP -> {
                    gameBreak = false;
                    up = true;
                    flipToFalse("Up");
                }
                case KeyEvent.VK_DOWN -> {
                    gameBreak = false;
                    down = true;
                    flipToFalse("Down");
                }
                case KeyEvent.VK_LEFT -> {
                    gameBreak = false;
                    left = true;
                    flipToFalse("Left");
                }
                case KeyEvent.VK_RIGHT -> {
                    gameBreak = false;
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