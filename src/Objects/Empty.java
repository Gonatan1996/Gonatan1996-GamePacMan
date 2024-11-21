package Objects;

import javax.swing.*;
import java.awt.*;

public class Empty extends GeneralElement{

    public Empty() {
    }

    public Empty(int x, int y) {
        this.point = new Point(x,y);
    }


    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public void setPoint(int x, int y) {

    }

    @Override
    public boolean getIsEaten() {
        return false;
    }

    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon("src/Images/רקע שחור.jpg");
        return imageIcon.getImage();
    }

    @Override
    public int getImageWidth() {
        return 0;
    }

    @Override
    public int getImageHeight() {
        return 0;
    }
}
