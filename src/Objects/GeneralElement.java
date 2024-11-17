package Objects;

import javax.swing.*;
import java.awt.*;

public abstract class  GeneralElement implements Element{
    protected Point point;
    protected boolean isEaten;
    protected int width = 25,height = 25;
    public ImageIcon image;
    public void setPreferredDirection(String preferredDirection) {
    }

    public String getPreferredDirection() {
        return "";
    }

    public void setDirection(String direction) {
    }

    public String getDirection() {
        return "";
    }


}
