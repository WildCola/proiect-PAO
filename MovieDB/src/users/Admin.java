package users;

public class Admin extends User{
    private int workHours[] = new int[2];
    private boolean maxAuthority;

    public Admin() {
        super();
        this.workHours[0] = -1;
        this.workHours[1] = -1;
        this.maxAuthority = false;
    }

    public Admin(String uName, String fName, String lName, String password, String email, int[] workHours, boolean maxAuthority) {
        super(uName, fName, lName, password, email);
        this.workHours = workHours;
        this.maxAuthority = maxAuthority;
    }

    public int[] getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int[] workHours) {
        this.workHours = workHours;
    }

    public boolean isMaxAuthority() {
        return maxAuthority;
    }

    public void setMaxAuthority(boolean maxAuthority) {
        this.maxAuthority = maxAuthority;
    }
}