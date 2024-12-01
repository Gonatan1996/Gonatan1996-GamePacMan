package Graphics;

import java.awt.event.*;
import java.util.ArrayList;

public class



    KeyHandler implements KeyListener,Runnable {
        Thread thread;
        public ArrayList<Integer> moves;
        public ArrayList<Long> timeStamps = new ArrayList<>();
        private long lastKeyPressedTime;
        public boolean up, down, left, right, gameBreak,keyAuto;

        public KeyHandler() {
            moves = new ArrayList<>();
            timeStamps = new ArrayList<>();
            lastKeyPressedTime = System.currentTimeMillis();
        }

    @Override
        public void keyTyped(KeyEvent e) {


        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!keyAuto) {
                long currentTime = System.currentTimeMillis();
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
                moves.add(code);
                long timeElapsed = currentTime - lastKeyPressedTime;
                timeStamps.add(timeElapsed);
                System.out.println(timeElapsed);
                lastKeyPressedTime = currentTime;
                }

        }
        public void startMoveAuto(){
            if (thread == null) {
                keyAuto = true;
                thread = new Thread(this);
                thread.start();
            }
        }
        public void moveAuto (){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < moves.size(); i++) {
                int move = moves.get(i);
                switch (move) {
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
                long delay = timeStamps.get(i);
                System.out.println(delay);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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

    @Override
    public void run() {
        this.moveAuto();
    }
}