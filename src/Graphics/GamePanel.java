package Graphics;

import Objects.*;
import UpDate.Update;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Runnable {
    int x, y;
    final int width_height = 20;
    boolean startGame = true,endGame;
    JLabel textStart = new JLabel();
    Timer timer = new Timer();
    static int[][] numOfElement = numOfElement();
    public static GeneralElement[][] generalElements = new GeneralElement[numOfElement.length][numOfElement[0].length];


    KeyHandler keyHandler = new KeyHandler();
    Thread gameTread;

    Block block = new Block();
    PacMan pacMan = new PacMan();
    Coins coins = new Coins();
    BigCoins bigCoins = new BigCoins();
    Ghost ghost = new Ghost();
    Fruit fruit = new Fruit();
    Update update = new Update(pacMan,ghost);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       if (startGame) {
           screenStartGame();
       }if (endGame) {
           if (pacMan.life >= 0)screenEndGameWin();
           else screenEndGameLoss();
       }else {
            createScreenGame(g);
            if (pacMan.stopGame) {
                if (pacMan.life >= 0 && !coins.coins.isEmpty()) {
                    pacMan.stopGame = false;
                    pacMan.startAgain();
                }else {
                   endGame = true;
                }
            }
        }
    }

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        setFocusable(true);
        this.generalElements = createArrayElement();
    }

    public void updatePacMan(PacMan pacMan) {
        if (keyHandler.up) pacMan.setPreferredDirection("UP");
        if (keyHandler.down) pacMan.setPreferredDirection("DOWN");
        if (keyHandler.right){
            update.flipDirectionRight(pacMan);
            pacMan.setPreferredDirection("RIGHT");
        }
        if (keyHandler.left){
            update.flipDirectionLeft(pacMan);
            pacMan.setPreferredDirection("LEFT");
        }
        String preferredDirection = pacMan.getPreferredDirection();

        int x = pacMan.getPoint().x,
                y = pacMan.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;


        if (preferredDirection != null && update.canTurn(pacMan,preferredDirection, tempX, tempY, x, y)) {
            pacMan.setDirection(preferredDirection);
            pacMan.setPreferredDirection(null);
        }
        update.moveElement(pacMan,tempX,tempY,x,y);
        }

    public void upDateGhosts(Ghost ghostPink,Ghost ghostGreen,Ghost ghostRed,Ghost ghostYellow){
        upDateGhost(ghostPink);
        upDateGhost(ghostRed);
        upDateGhost(ghostGreen);
        upDateGhost(ghostYellow);
    }

    public void upDateGhost(Ghost ghost){
        int x = ghost.getPoint().x,
                y = ghost.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;
        ghost.setDirection(ghost.randomMove());
        update.moveElement(ghost,tempX,tempY,x,y);

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
        while (!pacMan.stopGame || endGame) {
            if (!keyHandler.GameBreak) {
                updatePacMan(pacMan);
                upDateGhosts(ghost.pink, ghost.green, ghost.red, ghost.yellow);
                fruit.melon.upDateScoreOfFruit(pacMan);
                fruit.cherry.upDateScoreOfFruit(pacMan);
                fruit.strawberry.upDateScoreOfFruit(pacMan);
                fruit.orange.upDateScoreOfFruit(pacMan);
                fruit.apple.upDateScoreOfFruit(pacMan);

                if (pacMan.lossLife(ghost.pink, ghost.green, ghost.red, ghost.yellow)) {
                    pacMan.stopGame = true;
                }
                Coins.upDateCoins(pacMan, coins, generalElements);
                if (BigCoins.upDateBigCoins(pacMan, bigCoins, generalElements)) {
                    pacMan.pacManEatGhosts(ghost.pink, ghost.green, ghost.red, ghost.yellow);
                }
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
        g.drawImage(ghost.red.getImage(), ghost.red.getPoint().x,ghost.red.getPoint().y,ghost.width, ghost.height, this);
        g.drawImage(ghost.yellow.getImage(), ghost.yellow.getPoint().x,ghost.yellow.getPoint().y,ghost.width, ghost.height, this);
        g.drawImage(ghost.pink.getImage(), ghost.pink.getPoint().x,ghost.pink.getPoint().y,ghost.width, ghost.height, this);
        g.drawImage(ghost.green.getImage(), ghost.green.getPoint().x,ghost.green.getPoint().y,ghost.width, ghost.height, this);
        g.drawImage(pacMan.getImage(), pacMan.getPoint().x, pacMan.getPoint().y, pacMan.width, pacMan.height, this);
        if (pacMan.score >= 100 && pacMan.score < 250 && !fruit.cherry.getIsEaten())  g.drawImage(fruit.cherry.getImage(), fruit.cherry.getPoint().x,fruit.cherry.getPoint().y,fruit.width,fruit.height,this);
        if (pacMan.score >= 400 && pacMan.score < 650 && !fruit.strawberry.getIsEaten())  g.drawImage(fruit.strawberry.getImage(), fruit.strawberry.getPoint().x,fruit.strawberry.getPoint().y,fruit.width,fruit.height,this);
        if (pacMan.score >= 1000 && pacMan.score < 1300 && !fruit.orange.getIsEaten())g.drawImage(fruit.orange.getImage(), fruit.orange.getPoint().x,fruit.orange.getPoint().y,fruit.width,fruit.height,this);
        if (pacMan.score >= 2000 && pacMan.score < 2200 && !fruit.apple.getIsEaten())g.drawImage(fruit.apple.getImage(), fruit.apple.getPoint().x,fruit.apple.getPoint().y,fruit.width,fruit.height,this);
        if (pacMan.score >= 3000 && pacMan.score < 3200 && !fruit.melon.getIsEaten())g.drawImage(fruit.melon.getImage(), fruit.melon.getPoint().x,fruit.melon.getPoint().y,fruit.width,fruit.height,this);

    }

    public void screenEndGameWin(){
            textStart.setText("you Winner");
            textStart.setForeground(Color.GREEN);
            textStart.setSize(565,400);
            textStart.setFont(new Font("Arial",Font.BOLD, 50));
        this.add(textStart);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                endGame = false;
//            }
//        },3000);


    }    public void screenEndGameLoss(){
        textStart.setText("try again");
        textStart.setForeground(Color.GREEN);
        textStart.setSize(565,400);
        textStart.setFont(new Font("Arial",Font.BOLD, 50));
        this.add(textStart);
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                endGame = false;
//            }
//        },3000);

    }
    }


