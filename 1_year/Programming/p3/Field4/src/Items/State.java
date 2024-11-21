package Items;

public enum State {

    WATERPROOF("Водостойкий"),
    FIREPROOF("Огнестойкий");

    private String state;

    State(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
