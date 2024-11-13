package Objects;

import java.awt.*;
import java.util.ArrayList;

public class Coins extends GeneralElement implements Eatable{


    public Coins(int x,int y) {
        setPoint(x,y);
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public void setPoint(int x, int y) {
        this.point = new Point(x, y);
    }

    @Override
    public boolean getIsEaten() {
        return true;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public int getImageWidth() {
        return width;
    }

    @Override
    public int getImageHeight() {
        return height;
    }

    @Override
    public int getValue() {
        return 10;
    }



    public static int[][] upDateCoins(PacMan pacMan,int[][] arr){
        int x = pacMan.getPoint().x/ pacMan.width,
            y = pacMan.getPoint().y/ pacMan.height;
        System.out.println(x);
        System.out.println(y);
        System.out.println(arr[y][x]);
     if (arr[y][x] == 2){
         arr[y][x] = 0;
         pacMan.score += 10;
     }
     return arr;
    }
}
