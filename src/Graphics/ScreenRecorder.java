package Graphics;

import Users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ScreenRecorder implements Runnable {
    public ArrayList<BufferedImage> frames = new ArrayList<>();
    public String name = "";
    // אחסון פריימים בזיכרון
    public boolean recording = true;
    Thread recorderThread;
    Robot robot;
    Rectangle screenRect;

    public ScreenRecorder(Rectangle screenRect) throws AWTException {
        robot = new Robot();
        this.screenRect = screenRect;
    }

    @Override
    public void run() {
        System.out.println("Recording started...");
        while (recording) {
            // הקלטת פריים והוספתו לזיכרון
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            frames.add(screenCapture);
            try {
                Thread.sleep(20); // הקלטת בקצב 10FPS
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        //playRecording();
    }
    // התחל שרשור הקלטה

    public void startRecord(){
        recorderThread = new Thread(this);
        recorderThread.start();
    }

    public void playRecording() {
        // יצירת חלון להצגת ה"וידאו"
        JFrame frame = new JFrame("Playback");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(575, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // הצגת כל פריים
        for (BufferedImage img : this.frames) {
            ImageIcon icon = new ImageIcon(img);
            label.setIcon(icon);
            label.repaint();
            try {
                Thread.sleep(20); // השהיה בין פריימים
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Playback finished.");
        frame.dispose();
    }

    public void ifSave(User user){
        user.recorders.getLast().recording = false;
        System.out.println("ההקלטה הסתיימה");
        int response = JOptionPane.showConfirmDialog(null,"Do you want to save this record?","question",JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION){
            user.recorders.getLast().recording = false;
            String nickName = "";
            while (nickName.trim().isEmpty()) {
                nickName = JOptionPane.showInputDialog(null, "what your nickName for the game");
            }
            user.recorders.getLast().name = nickName;
        }else {
            user.recorders.remove(user.recorders.getLast());
        }

    }
}
