package Parameters;

public class Person {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer height; //Поле не может быть null, Значение поля должно быть больше 0
    private java.time.LocalDate birthday; //Поле может быть null
    private String passportID; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null
}
