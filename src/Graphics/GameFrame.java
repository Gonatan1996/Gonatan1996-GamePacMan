package Graphics;



import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GameFrame extends JFrame{


    JPanel main = new JPanel();
    GamePanel gamePanel = GamePanel.newGamePanel();
    TopPanel gameTopPanel = new TopPanel();
    BottomPanel gameBottomPanel = new BottomPanel();
    StartFrame startFrame = new StartFrame(this);
    JPanel currentPanel;

    public GameFrame(int i) throws AWTException, FileNotFoundException {
        super();
    }
    public GameFrame() throws AWTException, FileNotFoundException {
        screenGame();
        setInstructions();
        this.add(startFrame);
        gameTopPanel.upDateScoreStart();
        gameBottomPanel.startUpDateLife();
        gamePanel.startGame();
    }

    public void screenGame(){
        main.setLayout(new BorderLayout());
        main.add(gameTopPanel, BorderLayout.NORTH);      // פאנל עליון
        main.add(gamePanel, BorderLayout.CENTER);// פאנל המשחק במרכז
        main.add(gameBottomPanel, BorderLayout.SOUTH);   // פאנל תחתון
    }

    private void setInstructions() throws AWTException {
        this.setTitle("PacMan Nachum");
        this.setLayout(new BorderLayout());
        this.setSize(575, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setCurrentPanel(JPanel newPanel){
        if (currentPanel != null){
            this.remove(currentPanel);
            this.add(newPanel);
            revalidate();
            repaint();
        }else currentPanel = newPanel;
        gamePanel.requestFocus();
    }
    public void newFrame(){
        this.remove(main);
        this.add(startFrame);
        revalidate();
        repaint();

    }

}
