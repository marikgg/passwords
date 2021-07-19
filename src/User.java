import java.util.*;

public class User {
    private String userName;
    private String pass;
    User(String userName, String pass){
        this.userName = userName;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

