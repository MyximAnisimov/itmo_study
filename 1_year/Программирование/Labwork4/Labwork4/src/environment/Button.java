package environment;

public enum Button {
    BUTTON1("Первая кнопка"),BUTTON2("Вторая кнопка"),BUTTON3("Третья кнопка");
    private String name;
    Button(String name){
 this.name=name;
    }
    public String getButton(){
        return name;
    }
}
