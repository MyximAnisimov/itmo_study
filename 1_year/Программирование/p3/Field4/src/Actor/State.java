package Actor;

public enum State {

    FLying("В невесомости"),
    OnGround("На земле");


    private String scan;

    State(String scan){
        this.scan = scan;
    }

    public String getState() {
        return scan;
    }




}
