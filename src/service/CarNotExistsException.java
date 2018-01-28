package service;

public class CarNotExistsException extends ServiceException {
    private Long id;

    public CarNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}