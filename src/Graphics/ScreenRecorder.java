package Graphics;

import Users.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//public class ScreenRecorder implements Runnable {
//    public String name = "";
//    public boolean recording = true;
//    Thread recorderThread;
//    Robot robot;
//    Rectangle screenRect;
//    ZipOutputStream zipOut;
//    File outputFileZip = new File("screenshot.zip");
//
//
//    public ScreenRecorder(Rectangle screenRect) throws AWTException, FileNotFoundException {
//        zipOut = new ZipOutputStream(new FileOutputStream(outputFileZip));
//        robot = new Robot();
//        this.screenRect = screenRect;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("Recording started...");
//        int count=0;
//        while (recording) {
//            // הקלטת פריים והוספתו לזיכרון
//            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
//            String imageName = "screenshot_" + count +" .png";
//            try {
//                zipOut.putNextEntry(new ZipEntry(imageName));
//                ImageIO.write(screenCapture,"png", zipOut);
//                zipOut.closeEntry();
//               // System.out.println(outputFileZip.getAbsolutePath());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                Thread.sleep(20); // הקלטת בקצב 10FPS
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//            count++;
//        }
//        try {
//            playRecording();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    // התחל שרשור הקלטה
//
//    public void startRecord(){
//        recorderThread = new Thread(this);
//        recorderThread.start();
//    }
//
//    public void playRecording() throws IOException {
//
//        // יצירת חלון להצגת ה"וידאו"
//        JFrame frame = new JFrame("Playback");
//        JLabel label = new JLabel();
//        frame.add(label);
//        frame.setSize(575, 720);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//        // הצגת כל פריים
//        File zipFile = new File("screenshot.zip");
//        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile));
//        ZipEntry entry;
//        while ((entry = zipIn.getNextEntry()) != null){
//            BufferedImage image = ImageIO.read(zipIn);
//            ImageIcon icon = new ImageIcon(image);
//            label.setIcon(icon);
//            label.repaint();
//            try {
//                Thread.sleep(20); // השהיה בין פריימים
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Playback finished.");
//        frame.dispose();
//    }
//
//    public void ifSave(User user){
//        System.out.println("ההקלטה הסתיימה");
//        int response = JOptionPane.showConfirmDialog(null,"Do you want to save this record?","question",JOptionPane.YES_NO_OPTION);
//        if (response == JOptionPane.YES_OPTION){
//            user.recorders.getLast().recording = false;
//            String nickName = "";
//            while (nickName.trim().isEmpty()) {
//                nickName = JOptionPane.showInputDialog(null, "what your nickName for the game");
//            }
//            user.recorders.getLast().name = nickName;
//        }else {
//            user.recorders.remove(user.recorders.getLast());
//        }
//
//    }
//}
