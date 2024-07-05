package environment;

public enum Floors {
    FIRST_FLOOR("Первый этаж"),
    SECOND_FLOOR("Второй этаж"),
    THIRD_FLOOR("Третий этаж");
    private String name;
    Floors(String name){
        this.name=name;
    }
    public String getFloors(){
        return name;
    }
}
