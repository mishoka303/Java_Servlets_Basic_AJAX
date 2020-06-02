package twitch.models;

public class tmpUser {
    String username;
    String password;
    String cpassword;

    public tmpUser() {}

    //For register
    public tmpUser(String username, String password, String cpassword) {
        this.username = username;
        this.password = password;
        this.cpassword = cpassword;
    }

    //For login
    public tmpUser(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }
}
