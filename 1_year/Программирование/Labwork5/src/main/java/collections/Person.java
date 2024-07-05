package collections;
import tools.Validatable;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Validatable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer height; //Поле не может быть null, Значение поля должно быть больше 0
    private LocalDate birthday; //Поле может быть null
    private String passportID; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(int id, String name, Coordinates coordinates, LocalDate creationDate, Integer height, LocalDate birthday, String passportID, Country nationality, Location location) {
        this.id = 1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
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
    public LocalDate creationDate(){
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
    @Override
    public boolean validate(){
        if(id<0){
            return false;
        }
        if(name==null||name.isEmpty()){
            return false;
        }
        if(coordinates==null){
            return false;
        }
        if(creationDate==null){
            return false;
        }
        if(height==null||height<0){
            return false;
        }
        if(passportID==null){
            return false;
        }
      return location !=null;
    }
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        else if(o==null||o.getClass()!=getClass()){
            return false;
        }
        Person pers=(Person) o;
        return Objects.equals(id,pers.id)&& Objects.equals(name,pers.name)&&Objects.equals(coordinates,pers.coordinates)
                &&Objects.equals(creationDate,pers.creationDate)&&Objects.equals(height,pers.height)&&
                Objects.equals(birthday,pers.birthday)&&Objects.equals(passportID,pers.passportID)&&
                Objects.equals(nationality,pers.nationality)&&Objects.equals(location,pers.location);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id,name,coordinates,creationDate,height,birthday,passportID,nationality,location);
    }
    @Override
    public String toString(){
        return "Person: { id= "+id+", "+ "name= "+name+", "+ "coordinates= "+coordinates+", "+
                "creationDate= "+creationDate+", "+"height= "+height+", "+"birthday= "+birthday+", "+
                "passportID= "+passportID+", "+"nationality= "+nationality+", "+"location= "+location+" }";}
}

