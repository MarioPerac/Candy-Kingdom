package factory.model;

import factory.service.UserService;

public class UserInfo {
    private String username;
    private String email;

    private static UserInfo inctance;

    public UserInfo() {
    }

    public static UserInfo getInstance() {
        if (inctance == null) {
            inctance = new UserInfo();
        }
        return inctance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
