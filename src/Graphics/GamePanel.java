package Graphics;

import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    Random random = new Random();
    int x, y;
    final int speed = 5;
    final int width_height = 25;
    static int[][] numOfElement = numOfElement();
    GeneralElement[][] generalElements = new GeneralElement[numOfElement.length][numOfElement[0].length];


    KeyHandler keyHandler = new KeyHandler();
    Thread gameTread;

    Block block = new Block();
    PacMan pacMan = new PacMan();
    Coins coins = new Coins();
    BigCoins bigCoins = new BigCoins();
    Ghost ghostYellow = new Ghost(12*width_height,13*width_height,"yellow"),
            ghostPink = new Ghost(13*width_height,13*width_height,"pink"),
            ghostRed = new Ghost(14*width_height,13*width_height,"red"),
            ghostGreen = new Ghost(15*width_height,13*width_height,"green");


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Paint");
        createScreen(g);
    }

    public GamePanel() {
        this.addKeyListener(keyHandler);
        this.generalElements = createArrayElement();
        setFocusable(true);
    }

    public void updatePacMan(PacMan pacMan) {
        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;

        if (keyHandler.up) {
            if (tempX * width_height == x) {
                if (!(generalElements[(y - speed) / width_height][tempX] instanceof Block)) {
                    upDateMoveUp(pacMan);
                }
            }
        }if (keyHandler.down) {
            if (tempX * width_height == x) {
                if (!(generalElements[(y + width_height) / width_height][tempX] instanceof Block)) {
                    upDateMoveDown(pacMan);
                }
            }
        }if (keyHandler.left) {
            if (tempY * width_height == y) {
                if (!(generalElements[tempY][(x - speed) / width_height] instanceof Block)) {
                    if ((x) == 0) {
                        pacMan.getPoint().x = 700;
                        pacMan.image = new ImageIcon("src/Images/pacman.jpg");
                    } else {
                        upDateMoveLeft(pacMan);
                    }
                }
            }
        }if (keyHandler.right) {
            if (tempY * width_height == y) {
                if ((x + width_height) / width_height == 28) {
                    pacMan.getPoint().x = -24;
                    pacMan.image = new ImageIcon("src/Images/pacManBack.jpg");
                } else if (!(generalElements[tempY][(x + width_height) / width_height] instanceof Block)) {
                    upDateMoveRight(pacMan);
                }
            }
        }
    }

    public void upDateGhost(Ghost ghost){
        int random1 = random.nextInt(1,5);
        ghost.randomMove(random1);
        int x = ghost.getPoint().x,
                y = ghost.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;
        if (ghost.up){
            if (tempX * width_height == x) {
                if (!(generalElements[(y - speed) / width_height][tempX] instanceof Block)) {
                    upDateMoveUp(ghost);
                }
            }
        } else if (ghost.down) {
            if (tempX * width_height == x) {
                if (!(generalElements[(y + width_height) / width_height][tempX] instanceof Block)) {
                    upDateMoveDown(ghost);
                }
            }
        } else if (ghost.left) {
            if (tempY * width_height == y) {
                if (!(generalElements[tempY][(x - speed) / width_height] instanceof Block)) {
                    if ((x) == 0) {
                        ghost.getPoint().x = 700;
                    } else {
                        upDateMoveLeft(ghost);
                    }
                }
            }
        } else if (ghost.right) {
            if (tempY * width_height == y) {
                if ((x + width_height) / width_height == 28) {
                    ghost.getPoint().x = -24;
                } else if (!(generalElements[tempY][(x + width_height) / width_height] instanceof Block)) {
                    upDateMoveRight(ghost);
                }
            }
        }


    }


    public void createScreen(Graphics g) {
        for (int i = 0; i < generalElements.length; i++) {
            for (int j = 0; j < generalElements[i].length; j++) {
                x = j * width_height;
                y = i * width_height;
                if (generalElements[i][j] != null) {
                    g.drawImage(generalElements[i][j].getImage(), x, y, width_height, width_height, this);
                }
            }
        }
        g.drawImage(ghostRed.getImage(), ghostRed.getPoint().x,ghostRed.getPoint().y,width_height, width_height, this);
        g.drawImage(ghostYellow.getImage(), ghostYellow.getPoint().x,ghostYellow.getPoint().y,width_height, width_height, this);
        g.drawImage(ghostPink.getImage(), ghostPink.getPoint().x,ghostPink.getPoint().y,width_height, width_height, this);
        g.drawImage(ghostGreen.getImage(), ghostGreen.getPoint().x,ghostGreen.getPoint().y,width_height, width_height, this);
        g.drawImage(pacMan.getImage(), pacMan.getPoint().x, pacMan.getPoint().y, width_height, width_height, this);

    }

    public void upDateMoveUp(GeneralElement element) {
        element.getPoint().y -= speed;
//        if (element instanceof Ghost){
//            Ghost ghost = (Ghost) element;
//        }
        if (element instanceof PacMan) {
            if (pacMan.counter % 5 == 0){
                pacMan.setImage(new ImageIcon("src/Images/pacmanUp1.jpg"));
            }else {
                pacMan.setImage(new ImageIcon("src/Images/pacmanUp2.jpg"));
            }
        }
    }

    public void upDateMoveDown(GeneralElement element) {
        element.getPoint().y += speed;
        if (element instanceof PacMan) {
            if (pacMan.counter % 7 == 0){
                pacMan.setImage(new ImageIcon("src/Images/pacmanDown1.jpg"));
            }else {
                pacMan.setImage(new ImageIcon("src/Images/pacmanDown2.jpg"));
            }
        }
    }

    public void upDateMoveRight(GeneralElement element) {
        element.getPoint().x += speed;
        if (element instanceof PacMan) {
            if (pacMan.counter % 7 == 0) {
                pacMan.setImage(new ImageIcon("src/Images/pacmanRight1.jpg"));
            } else {
                pacMan.setImage(new ImageIcon("src/Images/pacmanRight2.jpg"));
            }
        }
    }

    public void upDateMoveLeft(GeneralElement element) {
        element.getPoint().x -= speed;
        if (element instanceof PacMan) {
            if (pacMan.counter % 7 == 0){
                pacMan.setImage(new ImageIcon("src/Images/pacmanLeft1.jpg"));
            }else {
                pacMan.setImage(new ImageIcon("src/Images/pacmanLeft2.jpg"));
            }
        }
    }



    public  GeneralElement[][] createArrayElement(){
        for (int i = 0; i < numOfElement.length; i++) {
            for (int j = 0; j < numOfElement[i].length; j++) {
                x = j * width_height;
                y = i * width_height;
                if (numOfElement[i][j] == 0){
                    generalElements[i][j] = new Empty();
                }
                if (numOfElement[i][j] == 1){
                    generalElements[i][j] = block.addBlock(new Block(x,y));
                }
                else if (numOfElement[i][j] == 2){
                    generalElements[i][j] = coins.addCoins(new Coins(x,y));
                }
                else if (numOfElement[i][j] == 3){
                    generalElements[i][j] = bigCoins.addBigCoins(new BigCoins(x,y));
                }
            }
        }
        return generalElements;
    }

    public static int[][] numOfElement(){
        // Empty = 0,Block = 1,Coins = 2,BigCoins = 3,
        // PacMan = 4,GhostPink = 5,GhostYellow = 6,
        // GhostGreen = 7,GhostRed = 8;
        int[][] board = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                {1,3,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,3,1},
                {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
                {1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
                {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
                {1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1},
                {0,0,0,0,0,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,0,0,0,0,0},
                {0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
                {1,1,1,1,1,1,2,1,1,0,1,1,1,1,0,1,1,1,0,1,1,2,1,1,1,1,1,1},
                {0,0,0,0,0,0,2,0,0,0,1,1,0,0,0,0,1,1,0,0,0,2,0,0,0,0,0,0},
                {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
                {0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
                {0,0,0,0,0,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,0,0,0,0,0},
                {1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                {1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
                {1,3,2,2,1,1,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,1,1,2,2,3,1},
                {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
                {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
                {1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
                {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
                {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        return board;
    }


    @Override
    public void run() {
        while (true){
            updatePacMan(pacMan);
            upDateGhost(ghostYellow);
            upDateGhost(ghostRed);
            upDateGhost(ghostPink);
            upDateGhost(ghostGreen);
            Coins.upDateCoins(pacMan,coins,generalElements);
            repaint();

            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void startGame(){
        gameTread = new Thread(this);
        gameTread.start();
    }


}
