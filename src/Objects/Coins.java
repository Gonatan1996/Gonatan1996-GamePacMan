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



    public static void upDateCoins(PacMan pacMan,int[][] arr,int x,int y){
//     if (arr[x][y] == 2){
//         arr[x][y] = 0;
//     }
        arr[2][1] = 0;
     pacMan.score += 10;
    }
}
