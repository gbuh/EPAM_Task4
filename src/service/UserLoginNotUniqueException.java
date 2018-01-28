package service;

public class UserLoginNotUniqueException extends ServiceException {
    private static final long serialVersionUID = 1L;
    private String login;

    public UserLoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}