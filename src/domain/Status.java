package domain;

/**
 * Type status enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Status {
    DONE      (0, "status.done"),
    EXECUTION (1, "status.execution"),
    ACCEPT    (2, "status.accept");

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
}