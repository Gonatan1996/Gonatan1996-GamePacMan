package Player;

import java.awt.*;

public interface Element {
    public Point getPoint(int x,int y); // return Element location
    public boolean getIsEaten();
    public Image getImage();
    public int getImageWidth();
    public int getImageHeight();


}
