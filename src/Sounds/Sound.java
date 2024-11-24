package Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements Runnable{
    String soundFilePath;
    Thread thread;
    public Sound(String soundFilePath) {
        this.soundFilePath = soundFilePath;
        this.thread = new Thread(this);
        thread.start();
    }

    public void playSound(){
        File soundFile = new File(soundFilePath);
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            Thread.sleep(3000);
            clip.drain();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        playSound();
    }
}
