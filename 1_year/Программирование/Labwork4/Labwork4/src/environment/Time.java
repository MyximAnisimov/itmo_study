package environment;

public enum Time {
    EARLY_TIME("Время раннее"),
    LATE_TIME("Время стало позднее");
    public String name;
   Time(String name){
        this.name=name;
    }
    public String getTime(){
        return name;
    }
}
