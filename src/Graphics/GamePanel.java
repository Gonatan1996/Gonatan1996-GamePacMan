package Graphics;

import Listener.Observer;
import Objects.*;
import Sounds.Sound;
import UpDate.Update;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class GamePanel extends JPanel implements Runnable ,Observer{

public static GamePanel gamePanel;

    HashMap<String,ArrayList<Observer>> listeners = new HashMap<>();



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
    User user = User.newUser();

    Block block = Block.newBlock();
    PacMan pacMan = PacMan.newPacman();
    Coins coins = Coins.newCoins();
    BigCoins bigCoins = BigCoins.newBigCoin();
    Ghost ghost = Ghost.newGhost();
    Fruit fruit;
    Update update = Update.newUpdate();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       if (startGame) {
           screenStartGame();

       }else if (pacMan.endGame) {
           if (pacMan.life >= 0){
               if (level2){
                   try {
                       screenLevel2();
                   } catch (FileNotFoundException e) {
                       throw new RuntimeException(e);
                   } catch (AWTException e) {
                       throw new RuntimeException(e);
                   }
               }else if (level3){
                   try {
                       screenLevel3();
                   } catch (FileNotFoundException e) {
                       throw new RuntimeException(e);
                   } catch (AWTException e) {
                       throw new RuntimeException(e);
                   }
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
                    pacMan.stopGame = false;
                    pacMan.startAgain();
                }else {
                   pacMan.endGame = true;
                }
            }
        }
    }

    private GamePanel() throws AWTException, FileNotFoundException {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        setFocusable(true);
        generalElements = createArrayElement();
        register("Draw", ghost);
        register("Draw", pacMan);
        register("Draw", fruit);
        updatePointOfLevel();
        drawImages();
    }
    public static GamePanel newGamePanel() throws FileNotFoundException, AWTException {
        if (GamePanel.gamePanel == null){
            GamePanel.gamePanel = new GamePanel();
        }
        return GamePanel.gamePanel;
    }


    public  GeneralElement[][] createArrayElement() throws FileNotFoundException, AWTException {
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
        fruit = Fruit.newFruit();
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
                pacManChackCollisioin();

                pacMan.updateMovePacMan(keyHandler);
                ghost.upDateMoveGhosts();


                fruit.upDateScoreOfFruits(pacMan);
                try {
                    if (pacMan.lossLife(ghost.pink, ghost.blue, ghost.red, ghost.yellow)) {
                        pacMan.stopGame = true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
              //  Coins.upDateCoins(pacMan,coins, generalElements);
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

    }

    private void pacManChackCollisioin() {
        if (pacMan.checkCollision(coins))coins.collisionPacMan();
        if (pacMan.checkCollision(bigCoins))bigCoins.collisionPacMan();
        if (pacMan.checkCollision(fruit))fruit.collisionPacMan();
        if (pacMan.checkCollision(ghost))ghost.collisionPacMan();
    }

    public synchronized void startGame(){
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
            observer.drawImages(g, this,-1,-1);
        }

    }

    public void screenLevel2() throws FileNotFoundException, AWTException {
        new Sound("src/Sounds/next_level.wav");
        addPanelTextLabel2("level 2",Color.white);
        Timer timer1 = new Timer();
        if (level2){
            timer1.schedule(new TimerTask() {
                @Override
                public void run() {
                        panelText = true;
                        level2 = false;
                        ArrayList<Observer> list = listeners.get("level");
                        for (Observer observer : list) {
                            observer.updatePointLevel();
                        }
                        GamePanel.this.remove(textLabel2);
                        GamePanel.this.requestFocus();
                        revalidate();
                        repaint();
                }
            },2000);
        }
        if (panelText) {
            generalElements = createArrayElement();
            panelText = false;
        }

    }

    public void screenLevel3() throws FileNotFoundException, AWTException {
        new Sound("src/Sounds/next_level.wav");
        addPanelTextLabel2("level 3",Color.white);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (level3) {
                    level3 = false;
                    ArrayList<Observer> list = listeners.get("level");
                    for (Observer observer : list) {
                        observer.updatePointLevel();
                    }
                    GamePanel.this.remove(textLabel2);
                    GamePanel.this.requestFocus();
                    revalidate();
                    repaint();
                }
            }
        },2000);
        if (panelText) {
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


    public void register(String name,Observer listener){
        if (listeners.get(name) == null){
            listeners.put(name,new ArrayList<>());
        }
        listeners.get(name).add(listener);
    }

    private void updatePointOfLevel(){
        register("level",pacMan);
        register("level",ghost);
        register("level",fruit);
        register("level",coins);
        register("level",bigCoins);
    }
    private void drawImages(){
        register("Draw",pacMan);
        register("Draw",ghost);
        register("Draw",fruit);
        register("Draw",coins);
        register("Draw",bigCoins);
    }


    public static void changeElement(int y, int x, GeneralElement newElement) {
        generalElements[y][x] = newElement;
    }
}


