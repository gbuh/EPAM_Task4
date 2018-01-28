package domain;

/**
 * Type condition enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Condition {
    GOOD      (0, "condition.good"),
    DEFECTIVE (1, "condition.defective");

    private int index;
    private String name;

    Condition(int index, String name) {
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