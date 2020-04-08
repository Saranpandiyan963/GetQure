package app.saran.getqure;

public class user {
    private String userId;
    private String Email;
    private String Name;

    public user(){

    }

    public user(String userId, String email, String name) {
        this.userId = userId;
        this.Email = email;
        this.Name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
