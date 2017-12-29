package domain;

public class User extends Entity {
    private String login;
    private String password;
    private String lastName;
    private String fistName;
    private String middleName;
    private Role role;

    public String getLogin() {
            return login;
    }

    public void setLogin(String login) {
            this.login = login;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Role getRole() {
            return role;
    }

    public void setRole(Role role) {
            this.role = role;
    }
}
