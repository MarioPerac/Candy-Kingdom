package factory.model;

public class UserInfo {
    private String username;
    private String email;
    private String phone;
    private String company;
    private String status;

    public UserInfo(String username, String email, String phone, String company, String status) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
