package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame{
JFrame frame = new JFrame();
JPanel panel = new JPanel();
JButton buttonStart = new JButton("Click me to start game");

    public StartFrame() {
        frame.setSize(575,700);
        frame.setLayout(new BorderLayout());
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame();
                frame.dispose();
            }
        });
        panel.setLayout(new GridBagLayout());
        panel.add(buttonStart);
        panel.setBackground(Color.BLACK);
        frame.add(panel,BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
