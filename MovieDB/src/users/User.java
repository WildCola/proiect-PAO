package users;

public class User {
    protected static int id = 0;
    protected String uName;
    protected String fName;
    protected String lName;
    protected String password;
    protected String email;

    public User() {
        this.uName = "User " + id;
        this.password = "password";
        id++;
    }

    public User(String uName, String fName, String lName, String password, String email) {
        this.uName = uName;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
