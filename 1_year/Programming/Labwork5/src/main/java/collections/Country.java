package collections;

public enum Country {
    UNITED_KINGDOM("Великобритания"),
    INDIA("Индия"),
    VATICAN("Ватикан"),
    ITALY("Италия"),
    JAPAN("Япония");
    private String name;
    Country(String name){
        this.name=name;
    }
    public String getCountry(){
        return name;
    }
}
