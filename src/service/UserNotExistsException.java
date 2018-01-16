package service;

public class UserNotExistsException extends ServiceException {
    private static final long serialVersionUID = 1L;
    private Long id;

    public UserNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}