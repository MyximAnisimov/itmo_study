package Actor;

public enum StateOfMind {

    HAPPY("счастлив"),
    ANGRY("зол");

    private String mind;

    StateOfMind(String mind){
        this.mind = mind;
    }

    public String getMind() {
        return mind;
    }




}
