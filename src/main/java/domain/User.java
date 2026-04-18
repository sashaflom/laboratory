package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class User {

    private String login;
    private String password;
    private int role;
    private UserStatus status;

    public User(String login, String password, int role, UserStatus status){
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User(){}

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
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

    public void setRole(int role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public boolean isBlocked() {
        return status == UserStatus.BLOCKED;
    }

    @Override
    public String toString() {
        return String.format("Користувач: логін: '%s', пароль: '%s', роль: '%s', статус: '%s'",
                login, password, Access.roleString(role), status);
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(status, user.status));
    }

    @Override
    public int hashCode(){
        return Objects.hash(login, password, role, status);
    }
}
