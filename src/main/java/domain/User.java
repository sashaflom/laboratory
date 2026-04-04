package domain;

public class User {

    private String login;
    private String password;
    private Role role;
    private UserStatus status;

    public User(String login, String password, Role role, UserStatus status){
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
