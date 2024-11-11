package Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawLine(1, 5, 50, 50);
        g.setColor(Color.cyan);
        g.drawRect(50,50,150,150);

    }


}
