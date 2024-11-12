package Graphics;

import Objects.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    int x,y;
    final int width_height = 25;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PacMan pacMan = null;
        Ghost ghostPink = null,ghostYellow = null,ghostGreen = null,ghostRed = null;

        int[][] board = numOfElement();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                x = j * width_height;
                y = i * width_height;
                if (board[i][j] == 1) {
                    g.setColor(Color.cyan);
                    g.fillRect(x,y,25,25);
                    Block block = new Block(x,y);
                }
                else if (board[i][j] == 2) {
                    g.setColor(Color.orange);
                    g.fillOval(x+7,y+7,13,13);
                    Coins coins = new Coins(x,y);
                }
                else if (board[i][j] == 3) {
                    g.setColor(Color.red);
                    g.fillOval(x+5,y+3,20,20);
                    BigCoins bigCoins = new BigCoins(x,y);
                } else if (board[i][j] == 4) {
                    if (pacMan == null)pacMan = new PacMan(x,y);
                    g.drawImage(pacMan.getImage(),x,y,width_height,width_height,this);
                } else if (board[i][j] == 5) {
                    if (ghostPink == null)ghostPink = new Ghost(x,y);
                    g.drawImage(ghostPink.getImagePink(),x,y,width_height,width_height,this);
                } else if (board[i][j] == 6) {
                    if (ghostYellow == null)ghostYellow = new Ghost(x,y);
                    g.drawImage(ghostYellow.getImageYellow(),x,y,width_height,width_height,this);
                } else if (board[i][j] == 7) {
                     if (ghostGreen == null)ghostGreen = new Ghost(x,y);
                    g.drawImage(ghostGreen.getImageGreen(),x,y,width_height,width_height,this);
                } else if (board[i][j] == 8) {
                     if (ghostRed == null)ghostRed = new Ghost(x,y);
                    g.drawImage(ghostRed.getImageRed(),x,y,width_height,width_height,this);
                }

            }
        }

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
                {1,3,2,2,1,1,2,2,2,2,2,2,2,4,2,2,2,2,2,2,2,2,1,1,2,2,3,1},
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


}
