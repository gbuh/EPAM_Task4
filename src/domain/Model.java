package domain;

/**
 * Type model enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Model {
    AUTOMOBILE (0, "model.automobile"),
    TRUCK      (1, "model.truck"),
    BUS        (2, "model.bus");

    private int index;
    private String name;

    Model(int index, String name) {
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