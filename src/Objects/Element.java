package Objects;

import java.awt.*;

public interface Element {
    Point getPoint(); // return Element location
    void setPoint(int x,int y);
    boolean getIsEaten();
    Image getImage();


}
