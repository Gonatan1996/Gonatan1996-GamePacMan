package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Block extends GeneralElement {
    public static Block block;
    ArrayList<Block> blocks = new ArrayList<>();

    private Block() {
    }
    public Block(int x,int y) {
        setPoint(x,y);
    }

    public Block addBlock(Block block){
        blocks.add(block);
        return block;
    }
    public static Block newBlock(){
        if (Block.block == null){
            Block.block = new Block();
        }
        return Block.block;
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
        ImageIcon imageIcon = new ImageIcon("src/Images/block.jpg");
        return imageIcon.getImage();
    }

}
