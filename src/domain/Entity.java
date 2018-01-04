package domain;

//import java.io.Serializable;

//public abstract class Entity implements Serializable {
public abstract class Entity{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}