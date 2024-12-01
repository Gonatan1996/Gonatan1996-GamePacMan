package Users;

import java.io.File;
import java.util.ArrayList;

public class User {
    public ArrayList<User> users = new ArrayList<>();
    public String name;
    public ArrayList<File> filesGame;


    public User() {
        this.users = new ArrayList<>();
        this.filesGame = new ArrayList<>();
    }

    private User(String name) {
        this.name = name;
    }

    public void addUser(String name){
        this.users.add(new User(name));
    }
}
