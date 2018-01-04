package domain;

public class Car extends Entity{
    private Model model;
    private byte places;
    private byte carrying;
    private Condition condition;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public byte getPlaces() {
        return places;
    }

    public void setPlaces(byte places) {
        this.places = places;
    }

    public byte getCarrying() {
        return carrying;
    }

    public void setCarrying(byte carrying) {
        this.carrying = carrying;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}