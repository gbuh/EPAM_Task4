package domain;

/**
 * Type status enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Status {
    ACCEPT    (0, "status.accept"),
    EXECUTION (1, "status.execution"),
    DONE      (2, "status.done");

    private int index;
    private String name;

    Status(int index, String name) {
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