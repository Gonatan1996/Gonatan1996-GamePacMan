package Objects;

import javax.swing.*;
import java.awt.*;

public class Fruit extends GeneralElement implements Eatable {
    boolean cherry,apple,orange,melon,strawberry;
    ImageIcon imageIcon = new ImageIcon();




    @Override
    public Point getPoint() {
        return null;
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
        if (cherry) imageIcon = new ImageIcon("src/Images/cherry.jpg");
        if (apple) imageIcon = new ImageIcon("src/Images/apple.webp");
        if (melon) imageIcon = new ImageIcon("src/Images/melon.webp");
        if (orange) imageIcon = new ImageIcon("src/Images/orange.jpg");
        if (strawberry) imageIcon = new ImageIcon("src/Images/strawberry.webp");
        return imageIcon.getImage();
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
        if (cherry)return 100;
        if (strawberry)return 300;
        if (orange)return 500;
        if (apple)return 700;
        if (melon)return 1000;
        return -1;
    }
}
