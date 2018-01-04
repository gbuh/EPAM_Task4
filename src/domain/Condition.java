package domain;

public enum Condition {
    GOOD      (0, "Исправный"),
    DEFECTIVE (1, "Неисправный");

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
}