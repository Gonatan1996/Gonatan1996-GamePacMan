package Users;

//import Graphics.ScreenRecorder;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class User {
    public static User user;
 //   public ArrayList<ScreenRecorder> recorders = new ArrayList<>();
    public ArrayList<User> users = new ArrayList<>();
    public String name;



    private User() throws AWTException, FileNotFoundException {
 //       this.recorders.add(new ScreenRecorder(new Rectangle( 680,156,575,720)));
        this.users = new ArrayList<>();
    }

    public static User newUser() throws FileNotFoundException, AWTException {
        if (User.user == null){
            User.user = new User();
        }
        return User.user;
    }
    private User(String name) {
        this.name = name;
    }

    public void addUser(String name){
        this.users.add(new User(name));
    }
}
