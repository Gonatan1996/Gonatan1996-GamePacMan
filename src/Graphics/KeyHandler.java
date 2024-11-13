package Graphics;

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
                case KeyEvent.VK_UP -> up = true;
                case KeyEvent.VK_DOWN -> down = true;
                case KeyEvent.VK_LEFT -> left = true;
                case KeyEvent.VK_RIGHT -> right = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_UP -> up = false;
                case KeyEvent.VK_DOWN -> down = false;
                case KeyEvent.VK_LEFT -> left = false;
                case KeyEvent.VK_RIGHT -> right = false;
            }
        }

    }