package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private String login;
    private String password;
    private int role;
    private boolean blocked;

    public User(String login, String password, int role){
        this.login = login;
        this.password = password;
        this.role = role;
        blocked = false;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
        if ((role & Access.BLOCKED) == 1) {
            blocked = true;
        } else {
            blocked = false;
        }
    }

    @JsonIgnore
    public boolean isBlocked() {
        return ((role & Access.BLOCKED) == 1);
    }

    @Override
    public String toString() {
        return String.format("Користувач: логін: '%s', пароль: '%s', роль: '%s', статус: '%s'",
                login, password, Access.roleString(role), (blocked ? "заблокований" : "дозволений"));
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role));
    }

    @Override
    public int hashCode(){
        return Objects.hash(login, password, role);
    }
}
