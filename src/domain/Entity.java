package domain;

/**
 * This class describes the base entity 
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public abstract class Entity{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}