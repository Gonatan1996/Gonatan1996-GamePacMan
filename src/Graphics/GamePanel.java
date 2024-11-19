package Graphics;

import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Runnable {
    int x, y;
    final int speed = 4;
    final int width_height = 20;
    boolean startGame = true,endGame;
    JLabel textStart = new JLabel();
    Timer timer = new Timer();
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
       if (startGame) {
               screenStartGame();
       }else if (endGame) {
           screenEndGame();
       }else {
            createScreenGame(g);
            if (pacMan.stopGame){
                if ( pacMan.life >= 0) {
                    pacMan.stopGame = false;
                    pacMan.startAgain();
                }else {
                    endGame = true;
                }
            }
        }
    }

    public GamePanel() {
        this.addKeyListener(keyHandler);
        setFocusable(true);
        this.generalElements = createArrayElement();
    }

    private boolean canMoveUp(GeneralElement generalElement,int tempX, int y) {
        return tempX * width_height == generalElement.getPoint().x && !(generalElements[(y-speed)/width_height][tempX] instanceof Block);
    }

    private boolean canMoveDown(GeneralElement generalElement,int tempX, int tempY) {
        return tempX * width_height == generalElement.getPoint().x && !(generalElements[tempY + 1][tempX] instanceof Block);
    }

    private boolean canMoveLeft(GeneralElement generalElement,int x, int tempY) {
        return tempY * width_height == generalElement.getPoint().y && !(generalElements[tempY][(x - speed)/width_height] instanceof Block);
    }

    private boolean canMoveRight(GeneralElement generalElement,int tempX, int tempY) {
        return tempY * width_height == generalElement.getPoint().y && !(generalElements[tempY][tempX + 1] instanceof Block);
    }

    public void updatePacMan(PacMan pacMan) {
        if (keyHandler.up) pacMan.setPreferredDirection("UP");
        if (keyHandler.down) pacMan.setPreferredDirection("DOWN");
        if (keyHandler.right){
            flipDirectionRight(pacMan);
            pacMan.setPreferredDirection("RIGHT");
        }
        if (keyHandler.left){
            flipDirectionLeft(pacMan);
            pacMan.setPreferredDirection("LEFT");
        }
        String preferredDirection = pacMan.getPreferredDirection();

        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;


        if (preferredDirection != null && canTurn(pacMan,preferredDirection, tempX, tempY, x, y)) {
            pacMan.setDirection(preferredDirection);
            pacMan.setPreferredDirection(null);
        }
       moveElement(pacMan,tempX,tempY,x,y);
        }

    private boolean canTurn(GeneralElement generalElement,String preferred,int tempX,int tempY,int x,int y){
        return switch (preferred) {
            case "UP" -> canMoveUp(generalElement,tempX, y);
            case "DOWN" -> canMoveDown(generalElement,tempX, tempY);
            case "RIGHT" -> canMoveRight(generalElement,tempX, tempY);
            case "LEFT" -> canMoveLeft(generalElement,x, tempY);
            default -> false;
        };
    }

    public void upDateGhost(Ghost ghost){
        int x = ghost.getPoint().x,
                y = ghost.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;
        ghost.setDirection(ghost.randomMove());
        moveElement(ghost,tempX,tempY,x,y);

    }

    public void moveElement(GeneralElement generalElement,int tempX,int tempY,int x,int y){

        switch (generalElement.getDirection()) {
            case "UP" -> {
                if (canTurn(generalElement,"UP", tempX, tempY, x, y)){
                    upDateMoveUp(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).up = false;
                    }
                }
            }
            case "DOWN" -> {
                if (canTurn(generalElement,"DOWN", tempX, tempY, x, y)) {
                    upDateMoveDown(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).down = false;
                    }
                }
            }
            case "RIGHT" -> {
                if (canTurn(generalElement,"RIGHT", tempX, tempY, x, y)){
                    upDateMoveRight(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).right = false;
                    }
                }
            }
            case "LEFT" -> {
                if (canTurn(generalElement,"LEFT", tempX, tempY, x, y)){
                    upDateMoveLeft(generalElement);
                }else {
                    if (generalElement instanceof Ghost) {
                        ((Ghost) generalElement).canMove = false;
                        ((Ghost) generalElement).left = false;
                    }
                }
            }
        }
    }

    public void createScreenGame(Graphics g) {
        this.remove(textStart);
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
        // Empty = 0,Block = 1,Coins = 2,BigCoins = 3;

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
        while (!pacMan.stopGame || endGame){
                    updatePacMan(pacMan);
                    upDateGhost(ghostRed);
                    upDateGhost(ghostPink);
                    upDateGhost(ghostGreen);
                    upDateGhost(ghostYellow);

                if(pacMan.lossLife(ghostPink) ||
                    (pacMan.lossLife(ghostRed)) ||
                    (pacMan.lossLife(ghostGreen)) ||
                    (pacMan.lossLife(ghostYellow))){
                    pacMan.stopGame = true;
                }
            Coins.upDateCoins(pacMan,coins,generalElements);
            if (BigCoins.upDateBigCoins(pacMan,bigCoins,generalElements)) {
                pacMan.pacManEatGhost(ghostGreen);
                pacMan.pacManEatGhost(ghostPink);
                pacMan.pacManEatGhost(ghostRed);
                pacMan.pacManEatGhost(ghostYellow);
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startGame(){
        gameTread = new Thread(this);
        gameTread.start();
    }

    public void flipDirectionRight(GeneralElement generalElement) {
        if ((generalElement.getPoint().x + width_height == 555)){
            generalElement.getPoint().x = 0;
        }
    }

    public void flipDirectionLeft(GeneralElement generalElement){
        if ((generalElement.getPoint().x) == 0) {
            generalElement.getPoint().x = 555;
        }
}

    public void screenStartGame(){
        textStart.setText("The Game Start ");
        textStart.setForeground(Color.GREEN);
        textStart.setSize(565,400);
        textStart.setFont(new Font("Arial",Font.BOLD, 50));
        this.add(textStart);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startGame = false;
            }
        },3000);

        }

    public void screenEndGame(){
        System.out.println("end");
        textStart.setText("The Game End ");
        textStart.setForeground(Color.GREEN);
        textStart.setSize(565,400);
        textStart.setFont(new Font("Arial",Font.BOLD, 50));
        this.add(textStart);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endGame = false;
            }
        },3000);

    }
    }


