package Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Block extends GeneralElement {
    public static Block block;
    private ArrayList<Block> blocks = new ArrayList<>();

    private Block() {
    }
    public Block(int x,int y) {
        setPoint(new Point(x,y));
    }

    public Block addBlock(Block block){
        blocks.add(block);
        return block;
    }
    public synchronized static Block newBlock(){
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
    public boolean getIsEaten() {
        return false;
    }
    @Override
    public Image getImage() {
        ImageIcon imageIcon = new ImageIcon("src/Images/block.jpg");
        return imageIcon.getImage();
    }

}
