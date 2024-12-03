package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JPanel{
int x = 220,y = 250;
JButton buttonStart = new JButton("Guest");
JButton buttonInstructions = new JButton("instruction");
JButton buttonBack = new JButton("Back");
JButton buttonAddUser = new JButton("AddUser");
JButton buttonSignIn = new JButton("Sign In");


JTextArea textArea;

    public StartFrame(GameFrame gameFrame) {
        settings();
        addButton(buttonAddUser);
        addButton(buttonSignIn);
        addButton(buttonStart);
        addButton(buttonInstructions);
        gameFrame.currentPanel = this;
        buttonAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = "";
                while (userName.trim().isEmpty()) {
                    userName = JOptionPane.showInputDialog("Enter User Name");
                }
                gameFrame.gamePanel.user.addUser(userName);
     //           ifRecord(gameFrame);
                gameFrame.gameTopPanel.name = userName;
                gameFrame.setCurrentPanel(gameFrame.main);
            }
        });

        buttonSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = "";
                while (userName == null || userName.trim().isEmpty()) {
                    userName = JOptionPane.showInputDialog("Enter User Name");
                }
                boolean b = true;
                for (int i = 0; i < gameFrame.gamePanel.user.users.size(); i++) {
                    if (userName.equals(gameFrame.gamePanel.user.users.get(i).name)){
                        JOptionPane.showMessageDialog(null,"Login successful !","Success",JOptionPane.INFORMATION_MESSAGE);
                        gameFrame.gameTopPanel.name = userName;
//                        try {
//                            ifShowRecorders(gameFrame);
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
 //                       ifRecord(gameFrame);
                        gameFrame.setCurrentPanel(gameFrame.main);
                        b = false;
                        break;
                    }
                }
                if (b)JOptionPane.showMessageDialog(null,"Login Filed.","Error",JOptionPane.ERROR_MESSAGE);

            }
        });

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setCurrentPanel(gameFrame.main);
            }
        });

        buttonInstructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartFrame.this.remove(buttonStart);
                StartFrame.this.remove(buttonInstructions);

               textArea = new JTextArea("חוקי המשחק: \n" +
                       "בתחילת המשחק פקמן יתחיל בנקודה קבועה:\n" +
                       "עם ניקוד 0, ו-3 חיים. \n" +
                       "מטרת המשחק היא לאכול את כל הנקודות על המסך\n" +
                       "ולהתקדם שלב.\n" +
                       "במשחק יש שלושה שלבים כאשר בכל שלב רמת\n" +
                       "הקושי עולה על פי פרמטרים שיפורטו בהמשך.\n" +
                       "כאשר לפקמן נגמרים החיים המשחק נגמר. \n" +
                       "בכל שלב פקמן מקבל חיים נוסף.\n" +
                       "במקביל לנקודות הקטנות ישנן 4 נקודות חזקות\n" +
                       "גדולות יותר בקצוות המסך  \n" +
                       "כאשר פקמן אוכל את אחת מהנקודות הגדולות הרוחות יברחו ממנו, מהירותם תרד ופקמן יוכל לאכול אותן.\n" +
                       "הרוחות נמצאות בריבוע שלהן באמצע וכל פעם שרבע מהנוקודות במסך נאכלות תשתחרר עוד רוח. \n" +
                       "במהלך השלב יופיעו במסך פירות (דובדבן, תות, תפוז, תפוח ומלון) במקום רנדומלי בלוח לזמן קצר. \n" +
                       "כל פרי מזכה בכמות נקודות שונה וככל שכמות הנק' יותר גדולה כך הוא יעלם יותר מהר. \n" +
                       "להלן פירוט הנקודות במשחק:\n" +
                       "1.\tנקודה קטנה -  10\n" +
                       "2.\tנקודה גדולה – 50\n" +
                       "3.\tרוח שנאכלת – 200\n" +
                       "4.\tדובדבן – 100\n" +
                       "5.\tתות – 300\n" +
                       "6.\tתפוז – 500\n" +
                       "7.\tתפוח – 700\n" +
                       "8.\tמלון – 1000\n" +
                       "\n" +
                       "כאשר רוח אוכלת את פקמן הוא יחזור לנקודת ההתחלה שלו והרוחות יחזרו לריבוע שלהן. "
               );
               textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
               textArea.setBounds(50,50,400,400);
               textArea.setBackground(Color.black);
               textArea.setForeground(Color.GREEN);
               StartFrame.this.setLayout(new BorderLayout());
               StartFrame.this.add(textArea,BorderLayout.CENTER);
               StartFrame.this.add(buttonBack,BorderLayout.SOUTH);
               revalidate();
               repaint();

               buttonBack.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       StartFrame.this.remove(buttonBack);
                       StartFrame.this.remove(textArea);
                       StartFrame.this.x = 220;
                       StartFrame.this.y = 250;
                       revalidate();
                       repaint();
                       settings();
                       addButton(buttonStart);
                       addButton(buttonInstructions);

                   }
               });
            }
        });
    }
    public void addButton(JButton button){
        button.setBounds(x,y,100,50);
        button.setBackground(Color.GREEN);
        button.setBorderPainted(false);
        this.add(button);
        y += 60;
    }
    public void settings (){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
//    public void ifRecord(GameFrame gameFrame){
//        int response = JOptionPane.showConfirmDialog(null,"Do you want to start recording?","question",JOptionPane.YES_NO_OPTION);
//        if (response == JOptionPane.YES_OPTION){
//            System.out.println("ההקלטה החלה");
// //           gameFrame.gamePanel.user.recorders.getLast().startRecord();
//        }else {
//            System.out.println("ההקלטה לא התבצעה");
//        }
//    }
//    public void ifShowRecorders(GameFrame gameFrame) throws IOException {
//        int response = JOptionPane.showConfirmDialog(null,"Do you want to see your saved games ?","question",JOptionPane.YES_NO_OPTION);
//        if (response == JOptionPane.YES_OPTION && !gameFrame.gamePanel.user.recorders.isEmpty()){
//            int choice = JOptionPane.showOptionDialog(null,"choice game ","recorders",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,gameFrame.gamePanel.user.recorders.toArray(),gameFrame.gamePanel.user.recorders.toArray()[0]);
//            if (choice > 0) {
//                gameFrame.gamePanel.user.recorders.get(choice-1).playRecording();
//            }
//                    gameFrame.gamePanel.user.recorders.getLast().startRecord();
//        }else {
//            System.out.println("Game not found");
//        }
//    }
}
