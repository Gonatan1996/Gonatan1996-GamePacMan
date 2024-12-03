package Graphics;

import Listener.DrawImage;
import Listener.Observer;
import Objects.*;
import Sounds.Sound;
import UpDate.Update;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class GamePanel extends JPanel implements Runnable, DrawImage {


    HashMap<String,ArrayList<Observer>> listeners = new HashMap<>();
//
    public void register(String name,Observer listener){
        if (listeners.get(name) == null){
            listeners.put(name,new ArrayList<>());
        }
        listeners.get(name).add(listener);
    }

    ArrayList<DrawImage> drawImages = new ArrayList<>();

    int x, y;
    final int width_height = 20;
    boolean startGame = true,level2 = true,level3 = true;
    JLabel textStart = new JLabel();
    JLabel textLabel2 = new JLabel();
    JButton jButton;
    Timer timer = new Timer();
    static int[][] numOfElement = numOfElement();
    public static GeneralElement[][] generalElements = new GeneralElement[numOfElement.length][numOfElement[0].length];

    boolean soundGameForSound = true,soundGameForMove = true,panelText = true;;

    KeyHandler keyHandler = KeyHandler.newKeyHandler();
    Thread gameTread;
    User user;

    Block block = Block.newBlock();
    PacMan pacMan = PacMan.newPacman();
    Coins coins =Coins.newCoins();
    BigCoins bigCoins = BigCoins.newBigCoin();
    Ghost ghost = Ghost.newGhost();
    Fruit fruit = Fruit.newFruit();
    Update update = new Update(pacMan,ghost);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       if (startGame) {
           screenStartGame();

       }else if (pacMan.endGame) {
           if (pacMan.life >= 0){
               if (level2){
                       screenLevel2();
               }else if (level3){
                       screenLevel3();
               }
                else{
               screenEndGameWin();
               }
           }
           else  screenEndGameLoss();

       }
       else {
            createScreenGame(g);
            if (pacMan.stopGame) {
                if (pacMan.life >= 0 && !coins.coins.isEmpty()) {
                   //if (!user.recorders.isEmpty())user.recorders.getLast().recording = false;
                    pacMan.stopGame = false;
                    pacMan.startAgain();
                }else {
                   pacMan.endGame = true;
                }
            }
        }
       // pacMan.stopGame = true;
      //  screenEndGameWin();

    }

    public GamePanel() throws AWTException, FileNotFoundException {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        setFocusable(true);
        generalElements = createArrayElement();
        user = new User();
//        register("drawImages",pacMan);
//        register("drawImages",ghost);

        register("Draw", ghost);
        register("BigCoin", ghost);
        drawImages.add(pacMan);
        drawImages.add(ghost);
        drawImages.add(this);
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
        update.ghostRedMove(ghostRed);
        upDateGhost(ghostPink);
        upDateGhost(ghostGreen);
        upDateGhost(ghostYellow);
    }

    public void upDateGhost(Ghost ghost){
//        if (ghost.getIsEaten()){
//            System.out.println("guyfascjhoasc");
//            update.ghostEatenMove(ghost);
//        }
        int x = ghost.getPoint().x,
                y = ghost.getPoint().y,
                tempX = x / width_height,
                tempY = y / width_height;
        ghost.setDirection(ghost.randomMove(coins));
        update.moveElement(ghost,tempX,tempY,x,y);

    }


    public  GeneralElement[][] createArrayElement(){
        for (int i = 0; i < numOfElement.length; i++) {
            for (int j = 0; j < numOfElement[i].length; j++) {
               int x = j * width_height;
               int y = i * width_height;
                if (numOfElement[i][j] == 0){
                    generalElements[i][j] = new Empty(x,y);
                }
                if (numOfElement[i][j] == 1){
                    generalElements[i][j] = block.addBlock(new Block(x,y));
                }
                else if (numOfElement[i][j] == 2){
                    generalElements[i][j] = coins.addCoins(new Coins(x,y));
                    Fruit.randomPoint.add(new Point(x,y));
                }
                else if (numOfElement[i][j] == 3){
                    generalElements[i][j] = bigCoins.addBigCoins(new BigCoins(x,y));
                    Fruit.randomPoint.add(new Point(x,y));
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
                {1,2,2,2,1,1,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,1,1,2,2,2,1},
                {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
                {1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
                {1,3,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,3,1},
                {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
                {1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
                {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        return board;
    }
    @Override
    public void run() {
        while (!pacMan.stopGame) {
            if (!keyHandler.gameBreak && !soundGameForMove) {
                updatePacMan(pacMan);
                upDateGhosts(ghost.pink, ghost.blue, ghost.red, ghost.yellow);
                fruit.upDateScoreOfFruits(pacMan);
                try {
                    if (pacMan.lossLife(ghost.pink, ghost.blue, ghost.red, ghost.yellow)) {
                        //if (!user.recorders.isEmpty())user.recorders.getLast().ifSave(user);
                        pacMan.stopGame = true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Coins.upDateCoins(pacMan,coins, generalElements);
                if (BigCoins.upDateBigCoins(pacMan, bigCoins, generalElements)) {
                    pacMan.pacManEatGhosts(ghost.pink, ghost.blue, ghost.red, ghost.yellow);
                }
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
      //  user.recorders.getLast().ifSave(user);


    }

    public void startGame(){
        gameTread = new Thread(this);
        gameTread.start();
    }

    public void screenStartGame(){
        addPanelText("Game Start",Color.CYAN);
        addPanelTextLabel2("level 1 ",Color.white);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startGame = false;
                GamePanel.this.remove(textLabel2);
            }
        },1000);

        }

    public void createScreenGame(Graphics g) {
        if (soundGameForSound && soundGameForMove){
            new Sound("src/Sounds/start_game.wav");
            this.remove(textStart);
            Timer timer1 = new Timer();
            timer1.schedule(new TimerTask() {
                @Override
                public void run() {
                  soundGameForMove = false;
                }
            },5000);
            soundGameForSound = false;
        }

        for (int i = 0; i < generalElements.length; i++) {
            for (int j = 0; j < generalElements[i].length; j++) {
                x = j * width_height;
                y = i * width_height;
                if (generalElements[i][j] != null) {
                    g.drawImage(generalElements[i][j].getImage(), x, y, width_height, width_height, this);
                }
            }
        }

        ArrayList<Observer> list = listeners.get("Draw");
        for (Observer observer : list) {
            observer.notify(g, this);
        }

        for (DrawImage drawImage : drawImages) {
            drawImage.drawImage(g,this);
        }
//        drawImageFruit(g);

    }

    public void screenLevel2() {
        new Sound("src/Sounds/next_level.wav");
        addPanelTextLabel2("level 2",Color.white);
        Timer timer1 = new Timer();
        if (level2){
            timer1.schedule(new TimerTask() {
                @Override
                public void run() {
                    level2 = false;
//                        pacMan.endGame = false;
//                        pacMan.stopGame = false;
//                        panelText = true;
//                        level2 = false;
//                        updatePointLevel();
////                        GamePanel.this.keyHandler.gameBreak = true;
////                        if (keyHandler.thread == null) {
////                            keyHandler.startMoveAuto();
////                        }
//                        GamePanel.this.remove(textLabel2);
//                        GamePanel.this.requestFocus();
//                        revalidate();
//                        repaint();
                }
            },2000);
        }
        if (panelText) {
            //        user.recorders.getLast().ifSave(user);

//            int life = pacMan.life;
//            pacMan.life = life + 1;
//            pacMan.score = 0;
//            bigCoins.bigCoins = new ArrayList<>();
//            coins.coins = new ArrayList<>();
//            generalElements = createArrayElement();
//            panelText = false;
        }

    }

    public void screenLevel3() {
        new Sound("src/Sounds/next_level.wav");
        addPanelTextLabel2("level 3",Color.white);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (level3) {
                    level3 = false;
                    pacMan.stopGame = false;
                    pacMan.endGame = false;
                    updatePointLevel();
//                    GamePanel.this.keyHandler.gameBreak = true;
                    GamePanel.this.remove(textLabel2);
                    GamePanel.this.requestFocus();
                    revalidate();
                    repaint();
                }
            }
        },2000);
        if (panelText) {
            int life = pacMan.life;
            pacMan.life = life + 1;
            pacMan.score = 0;
            bigCoins.bigCoinses = new ArrayList<>();
            coins.coins = new ArrayList<>();
            generalElements = createArrayElement();
            panelText = false;
        }
    }

    public void screenEndGameWin(){
        addPanelText("You Winner",Color.white);
        addButtonPlayAgain("play again");
        revalidate();
        repaint();
    }

    public void screenEndGameLoss(){
        addPanelText("  try again",Color.white);
        addButtonPlayAgain("try again");
        revalidate();
        repaint();
    }

    public void addButtonPlayAgain(String text){
        this.setLayout(null);
        jButton = new JButton(text);
        jButton.setBounds(230,270,100,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GameFrame gameFrame = new GameFrame();
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jButton.setBorderPainted(false);
        this.add(jButton);
    }

    public void addPanelText(String text,Color color){
        textStart.setText(text);
        textStart.setForeground(color);
        textStart.setBounds(150,50,300,325);
        textStart.setFont(new Font("Arial",Font.BOLD, 50));
        this.add(textStart);
        revalidate();
        repaint();
    }

    public void addPanelTextLabel2(String text,Color color){
        textLabel2.setText(text);
        textLabel2.setForeground(color);
        textLabel2.setBounds(200,100,300,325);
        textLabel2.setFont(new Font("Arial",Font.BOLD, 50));
        this.add(textLabel2);
        revalidate();
        repaint();
    }


    public Fruit drawImageFruit(Graphics g){
        return fruit;
    }

    public void updatePointLevel(){
        pacMan.point = new Point(13 * width_height,21 * width_height);
        ghost.pink.point = new Point(13*width_height,13*width_height);
        ghost.blue.point = new Point(15*width_height,13*width_height);
        ghost.yellow.point = new Point(12* width_height,13*width_height);
        ghost.red.point = new Point(14*width_height,13*width_height);
    }

    @Override
    public void drawImage(Graphics g, ImageObserver imageObserver) {
        if (pacMan.score >= 100 && pacMan.score < 250 && !fruit.cherry.getIsEaten()){
            fruit.cherry.show = true;
            g.drawImage(fruit.cherry.getImage(),fruit.cherry.getPoint().x,fruit.cherry.getPoint().y ,fruit.width,fruit.height,this);
        }else fruit.cherry.show = false;
        if (pacMan.score >= 400 && pacMan.score < 650 && !fruit.strawberry.getIsEaten()){
            fruit.strawberry.show = true;
            g.drawImage(fruit.strawberry.getImage(),fruit.strawberry.getPoint().x,fruit.strawberry.getPoint().y,fruit.width,fruit.height,this);
        }else fruit.strawberry.show = false;
        if (pacMan.score >= 1000 && pacMan.score < 1300 && !fruit.orange.getIsEaten()){
            fruit.orange.show = true;
            g.drawImage(fruit.orange.getImage(),fruit.orange.getPoint().x,fruit.orange.getPoint().y,fruit.width,fruit.height,this);
        }else fruit.orange.show = false;
        if (pacMan.score >= 2000 && pacMan.score < 2200 && !fruit.apple.getIsEaten()){
            fruit.apple.show = true;
            g.drawImage(fruit.apple.getImage(),fruit.apple.getPoint().x,fruit.apple.getPoint().y,fruit.width,fruit.height,this);
        }else fruit.apple.show = false;
        if (pacMan.score >= 3000 && pacMan.score < 3200 && !fruit.melon.getIsEaten()){
            fruit.melon.show = true;
            g.drawImage(fruit.melon.getImage(),fruit.melon.getPoint().x,fruit.melon.getPoint().y,fruit.width,fruit.height,this);
        }else fruit.melon.show = false;

    }
}


