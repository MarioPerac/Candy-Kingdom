package factory.model;

public class Login {

    private boolean successful;
    private String userStatus;

    public Login(boolean successful, String userStatus) {
        this.successful = successful;
        this.userStatus = userStatus;
    }

    public Login() {
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
