package common.utility;

import common.collections.Coordinates;
import common.collections.Country;
import common.collections.Location;

import java.io.Serializable;
import java.time.LocalDate;

public class RawPersonCollection implements Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer height; //Поле не может быть null, Значение поля должно быть больше 0
    private LocalDate birthday; //Поле может быть null
    private String passportID; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location;

    public RawPersonCollection( String name, Coordinates coordinates, LocalDate creationDate,  Integer height, LocalDate birthday, String passportID, Country nationality,  Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
    }

    public void setID(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setCoordinates(Coordinates coord){
        this.coordinates=coord;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public void setLocalDate(LocalDate LD){
        this.creationDate=LD;
    }
    public LocalDate getCreationDate(){
        return creationDate;
    }
    public void setHeight(Integer height){
        this.height=height;
    }
    public Integer getHeight(){
        return height;
    }
    public void setBirthday(LocalDate localdate){
        this.birthday=localdate;
    }
    public LocalDate getBirthday(){
        return birthday;
    }
    public void setPassportID(String passportID){
        this.passportID=passportID;
    }
    public String getPassportID(){
        return passportID;
    }
    public void setNationality(Country nationality){
        this.nationality=nationality;
    }
    public Country getNationality(){
        return nationality;
    }
    public void setLocation(Location location){
        this.location=location;
    }
    public Location getLocation(){
        return location;
    }
}
