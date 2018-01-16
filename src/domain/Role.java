package domain;

/**
 * Type role enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Role {
    ADMIN      (0, "role.admin"),
    DISPATCHER (1, "role.dispatcher"),
    DRIVER     (2, "role.driver");

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