package Objects;

import java.awt.*;
import java.util.ArrayList;

public class Block extends GeneralElement {
    ArrayList<Block> blocks = new ArrayList<>();

    public Block() {
    }

    public void addBlock(Block block){
        blocks.add(block);
    }

    public Block(int x,int y) {
       setPoint(x,y);
    }

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
