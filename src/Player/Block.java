package Player;

import java.awt.*;

public class Block extends GeneralElement {
    @Override
    public Point getPoint(int x, int y) {
        return null;
    }

    @Override
    public boolean getIsEaten() {
        return false;
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
}
