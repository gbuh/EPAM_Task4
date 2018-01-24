package service;

public class RequestNotExistsException extends ServiceException {
    private Long id;

    public RequestNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}