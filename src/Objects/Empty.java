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
    public boolean getIsEaten() {
        return false;
    }

    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon("src/Images/רקע שחור.jpg");
        return imageIcon.getImage();
    }
}
