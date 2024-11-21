package environment;

public enum time {
    EARLY_TIME("Время раннее"),
    LATE_TIME("Время стало позднее");
    private String name;
   time(String name){
        this.name=name;
    }
    public String getTime(){
        return name;
    }
}
