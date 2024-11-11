package Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawRect(50,50,150,150);
    }

    public GamePanel() {
//        JFrame jFrame = new JFrame();
//        jFrame.setSize(700,700);
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setLayout(null);
//        jFrame.getContentPane().setBackground(Color.BLACK);
//        jFrame.setVisible(true);








    }




}
