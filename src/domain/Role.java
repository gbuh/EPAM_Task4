package domain;

/**
 * Type role enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Role {
    DRIVER     (0, "role.driver"),
    DISPATCHER (1, "role.dispatcher"),
    ADMIN      (2, "role.admin");

    private int index;
    private String name;

    Role(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }
}