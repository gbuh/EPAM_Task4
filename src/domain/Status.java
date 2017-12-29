package domain;

public enum Status {
    DONE      (0, "Выполнена"),
    EXECUTION (1, "На исполнении");

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
