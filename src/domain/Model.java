package domain;

/**
 * Type model enumeration
 * 
 * @version 1.0 15 Jan 2018
 * @author  Igor Lipko
 */
public enum Model {
    AUTOMOBILE (0, "Легковой автомобиль"),
    TRUCK      (1, "Грузовой автомобиль"),
    BUS        (2, "Автобус");

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
}