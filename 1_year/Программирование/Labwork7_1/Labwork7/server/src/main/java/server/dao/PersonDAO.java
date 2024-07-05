package server.dao;

import common.collections.Country;
import common.collections.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name="persons")
@Table(name="persons",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class PersonDAO implements Serializable {
public PersonDAO() {}

public PersonDAO(Person person){
this.name = person.getName();
this.x=person.getCoordinates().getX();
this.y=person.getCoordinates().getY();
this.creationDate=person.getCreationDate();
this.height=person.getHeight();
this.localDate=person.getBirthday();
this.passportID=person.getPassportID();
this.nationality=person.getNationality();}
    public void update(Person person) {
        this.name = person.getName();
        this.x=person.getCoordinates().getX();
        this.y=person.getCoordinates().getY();
        this.creationDate=person.getCreationDate();
        this.height=person.getHeight();
        this.localDate=person.getBirthday();
        this.passportID=person.getPassportID();
        this.nationality=person.getNationality();

    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true, length=11)
    private int id;

    @NotBlank(message = "Название продукта не должно быть пустым.")
    @Column(name="name", nullable=false)
    private String name; // Поле не может быть null, Строка не может быть пустой

    @Column(name="x", nullable=false)
    private double x;

    @Column(name="y", nullable=false)
    private int y;

    @Column(name="creation_date", nullable=false)
    private LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Min(value = 1, message = "Рост должен быть больше нуля.")
    @Column(name="height", nullable=false)
    private Integer height; // Поле не может быть null, Значение поля должно быть больше 0

    @Column(name="birthday")
    private LocalDate localDate; // Строка не может быть пустой, Поле может быть null

    @Column(name="passportID", nullable=false)
    private String passportID; // Поле может быть null

    @Column(name="nationality")
    @Enumerated(EnumType.STRING)
    private Country nationality;

    @ManyToOne
    @JoinColumn(name="location_id")
    private LocationDAO location;
    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private UserDAO creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public LocalDate getBirthday() {
        return localDate;
    }

    public void setBirthday(LocalDate birthday) {
        this.localDate = birthday;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public LocationDAO getLocation(){
        return location;
    }
    public void setLocation(LocationDAO location){
        this.location=location;
    }
    public UserDAO getCreator() {
        return creator;
    }

    public void setCreator(UserDAO creator) {
        this.creator = creator;
    }

}
