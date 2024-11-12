package Objects;

import java.awt.*;

public interface Element {
    public Point getPoint(); // return Element location
    public void setPoint(int x,int y);
    public boolean getIsEaten();
    public Image getImage();
    public int getImageWidth();
    public int getImageHeight();


}
