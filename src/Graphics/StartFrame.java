package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JPanel{
JButton buttonStart = new JButton("Start Game");

    public StartFrame(GameFrame gameFrame) {
        gameFrame.currentPanel = this;
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setCurrentPanel(gameFrame.main);
            }
        });
        buttonStart.setPreferredSize(new Dimension(200,80));
        buttonStart.setBackground(Color.GREEN);
        this.setLayout(new GridBagLayout());
        this.add(buttonStart);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
}
