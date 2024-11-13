package Graphics;

import Objects.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    int x,y;
    final int speed = 5;
    final int width_height = 25;
    static int[][] numOfElement = numOfElement();

    KeyHandler ketHandler = new KeyHandler();
    Thread gameTread ;

    Block block = new Block();
    PacMan pacMan = new PacMan();
    Ghost ghostPink = null,ghostYellow = null,ghostGreen = null,ghostRed = null;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Paint");
        createScreen(numOfElement(),g,pacMan,ghostPink,ghostYellow,ghostGreen,ghostRed);
    }

    public GamePanel() {
        this.addKeyListener(ketHandler);
        setFocusable(true);
    }

    public void updatePacMan(PacMan pacMan) {
           int x = pacMan.getPoint().x,
               y = pacMan.getPoint().y,
               tempX = x / width_height,
               tempY = y / width_height;


       if (ketHandler.up){
           if (tempX * width_height == x){
               if (numOfElement[(y - speed) / width_height][tempX] != 1){
                   upDateMoveUp(pacMan);
                 //  Coins.upDateCoins(pacMan,numOfElement,(y - speed) / width_height,tempX);
               }
           } else if (numOfElement[(y - speed) / width_height][tempX] != 1 &&
                   numOfElement[(y - speed) / width_height][tempX + 1] != 1) {
               upDateMoveUp(pacMan);
           }
       }

       else if (ketHandler.down) {
           if (tempX * width_height == x) {
               if (numOfElement[(y + width_height ) / width_height][tempX] != 1){
                   upDateMoveDown(pacMan);
                  // Coins.upDateCoins(pacMan,numOfElement,tempX,(y + width_height ) / width_height);
               }
           } else if (numOfElement[(y + width_height) / width_height][tempX] != 1 &&
                   numOfElement[(y + width_height ) / width_height][tempX + 1] != 1) {
               upDateMoveDown(pacMan);
           }
       }

       else if (ketHandler.left) {
           if (tempY * width_height == y){
               if (numOfElement[tempY][(x - speed)/ width_height] != 1) {
                   if ((x - speed)/ width_height == 0){
                       pacMan.getPoint().x = 700;
                       pacMan.image = new ImageIcon("src/Images/pacman.jpg");
                   }else {
                    upDateMoveLeft(pacMan);
                 //   Coins.upDateCoins(pacMan,numOfElement,tempX,tempY);
                 }
               }
           } else if (numOfElement[tempY][(x - speed) / width_height] != 1 &&
                   numOfElement[tempY + 1][(x - speed)/ width_height] != 1) {
               upDateMoveLeft(pacMan);
           }
       }

       else if (ketHandler.right) {
           if (tempY * width_height == y){
               if ((x + width_height) / width_height == 28){
                   pacMan.getPoint().x = -24;
                   pacMan.image = new ImageIcon("src/Images/pacManBack.jpg");
               }
               else if (numOfElement[tempY][(x + width_height) / width_height] != 1){
                   upDateMoveRight(pacMan);
                  // Coins.upDateCoins(pacMan,numOfElement,(x + width_height) / width_height,tempY);
               }
           } else if (numOfElement[tempY][(x + width_height) / width_height] != 1 &&
                   numOfElement[tempY + 1][(x + width_height) / width_height] != 1) {
               upDateMoveRight(pacMan);
           }

       }
    }

    private void upDateMoveRight(PacMan pacMan) {
        pacMan.getPoint().x += speed;
        pacMan.image = new ImageIcon("src/Images/pacManBack.jpg");
    }

    private void upDateMoveLeft(PacMan pacMan) {
        pacMan.getPoint().x -= speed;
        pacMan.image = new ImageIcon("src/Images/pacman.jpg");
    }

    public void createScreen(int [][] board, Graphics g, PacMan pacMan,
                             Ghost ghostPink, Ghost ghostYellow, Ghost ghostGreen, Ghost ghostRed) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                x = j * width_height;
                y = i * width_height;
                if (board[i][j] == 0){
                    g.setColor(Color.black);
                    g.fillOval(x + 7, y + 7, 13, 13);
                }
                else if (board[i][j] == 1) {
                    block.addBlock(new Block(x,y));
                    g.setColor(Color.cyan);
                    g.fillRect(x, y, 25, 25);
                } else if (board[i][j] == 2) {
                    g.setColor(Color.orange);
                    g.fillOval(x + 7, y + 7, 13, 13);
                } else if (board[i][j] == 3) {
                    g.setColor(Color.red);
                    g.fillOval(x + 5, y + 3, 20, 20);
                    BigCoins bigCoins = new BigCoins(x, y);
               }
                 else if (board[i][j] == 5) {
                    if (ghostPink == null) ghostPink = new Ghost(x, y);
                    g.drawImage(ghostPink.getImagePink(), x, y, width_height, width_height, this);
                } else if (board[i][j] == 6) {
                    if (ghostYellow == null) ghostYellow = new Ghost(x, y);
                    g.drawImage(ghostYellow.getImageYellow(), x, y, width_height, width_height, this);
                } else if (board[i][j] == 7) {
                    if (ghostGreen == null) ghostGreen = new Ghost(x, y);
                    g.drawImage(ghostGreen.getImageGreen(), x, y, width_height, width_height, this);
                } else if (board[i][j] == 8) {
                    if (ghostRed == null) ghostRed = new Ghost(x, y);
                    g.drawImage(ghostRed.getImageRed(), x, y, width_height, width_height, this);
                }

            }
        }
        if (pacMan == null) pacMan = new PacMan();
        g.drawImage(pacMan.getImage(),pacMan.getPoint().x,pacMan.getPoint().y,width_height,width_height,this);

    }
    public void upDateMoveUp(PacMan pacMan){
        pacMan.getPoint().y -= speed;
        pacMan.image = new ImageIcon("src/Images/pacmanUp.jpg");
    }
    public void upDateMoveDown(PacMan pacMan){
        pacMan.getPoint().y += speed;
        pacMan.image = new ImageIcon("src/Images/pacmandown.jpg");
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
                {0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
                {1,1,1,1,1,1,2,1,1,0,0,1,1,0,0,1,1,0,0,1,1,2,1,1,1,1,1,1},
                {0,0,0,0,0,0,2,0,0,0,0,1,5,6,7,8,1,0,0,0,0,2,0,0,0,0,0,0},
                {1,1,1,1,1,1,2,1,1,0,0,1,1,1,1,1,1,0,0,1,1,2,1,1,1,1,1,1},
                {0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
                {0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
                {0,0,0,0,0,1,2,1,1,0,0,1,1,1,1,1,1,0,0,1,1,2,1,0,0,0,0,0},
                {1,1,1,1,1,1,2,1,1,0,0,1,1,1,1,1,1,0,0,1,1,2,1,1,1,1,1,1},
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
            Coins.upDateCoins(pacMan,numOfElement);
            repaint();

            try {
                Thread.sleep(1000);
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
