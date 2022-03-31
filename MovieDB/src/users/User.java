package users;

public class User {
    protected static int id;
    protected String uName;
    protected String fName;
    protected String lName;
    protected String password;
    protected String email;

    public User() {
        this.uName = "User" + id;
        this.password = "password";
    }

    public User(String uName, String fName, String lName, String password, String email) {
        this.uName = uName;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.email = email;
    }
}
