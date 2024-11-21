package FMap;

public enum Map {

    FlowerCity("Цветочный город"),
    Pavilion("Павильон"),
    Room("Кабина");
    private String place;

    Map(String place){
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
