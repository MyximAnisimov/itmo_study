package environment;

public enum floors {
    FIRST_FLOOR("Первый этаж"),
    SECOND_FLOOR("Второй этаж"),
    THIRD_FLOOR("Третий этаж");
    private String name;
    floors(String name){
        this.name=name;
    }
    public String getFloors(){
        return name;
    }
}
