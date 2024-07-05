package FMap;

public enum DayTime {

    DAY("День"),
    NIGHT("Ночь");


    private String time;

    DayTime(String time){
        this.time = time;
    }


    public String getTime() {
        return time;
    }
}
