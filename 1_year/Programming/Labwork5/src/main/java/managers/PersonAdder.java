package managers;

import collections.Coordinates;
import collections.Country;
import collections.Location;
import collections.Person;

import java.time.LocalDate;
import java.util.Scanner;

import static java.util.List.of;

public class PersonAdder {
    private Scanner scanner;
    CollectionManager CollMan;
    public PersonAdder(CollectionManager CollMan){
        this.CollMan=CollMan;
    }
    public int setID(){
        int id=31;
        return id;
    }
    public static String setName(Scanner sc){
        sc=new Scanner(System.in);
        System.out.println("Введите имя человека: ");
        String name=sc.nextLine();
        return name;
    }
    public static Coordinates setCoordinates(Scanner sc){
        sc=new Scanner(System.in);
        System.out.println("Введите координату х: ");
        int x=sc.nextInt();
        System.out.println("Введите координату y: ");
        int y=sc.nextInt();
        Coordinates coord=new Coordinates(x,y);
        return coord;
    }
public static LocalDate setCreationDate(){
        return LocalDate.now();
}
public static Integer setHeight(Scanner sc){
    sc=new Scanner(System.in);
    System.out.println("Введите рост человека: ");
    Integer height=sc.nextInt();
    return height;
}
public static LocalDate setBirthdayDate(Scanner sc){
    sc=new Scanner(System.in);
    System.out.println("Введите год рождения человека: ");
    int year=sc.nextInt();
    sc=new Scanner(System.in);
    System.out.println("Введите месяц рождения человека: ");
    int month=sc.nextInt();
    sc=new Scanner(System.in);
    System.out.println("Введите день рождения человека: ");
    int day=sc.nextInt();
    LocalDate localdate=LocalDate.of(year,month,day);
return localdate;
}
public static String setPassportID(Scanner sc){
    sc=new Scanner(System.in);
    System.out.println("Введите паспортные данные человека: ");
    String passportID=sc.nextLine();
    return passportID;
}
public static Country setNationality(Scanner sc){
        sc=new Scanner(System.in);
        System.out.println("Укажите национальность человека, представленную в списке: ");
        System.out.println(Country.INDIA);
    System.out.println(Country.ITALY);
    System.out.println(Country.JAPAN);
    System.out.println(Country.VATICAN);
    System.out.println(Country.UNITED_KINGDOM);
    String country=sc.nextLine();
    if(country=="Japan"){
        return Country.JAPAN;
    }
    else if(country=="India"){
        return Country.INDIA;
    }
    else if(country=="Italy"){
        return Country.ITALY;
    }
    else if(country=="Vatican"){
        return Country.VATICAN;
    }
    else if(country=="United kingdom"){
        return Country.UNITED_KINGDOM;
    }
    return null;
}
public static Location setLocation(Scanner sc){
    sc=new Scanner(System.in);
    System.out.println("Введите координату х места: ");
    Long x=sc.nextLong();
    System.out.println("Введите координату у места: ");
    int y=sc.nextInt();
    System.out.println("Введите координату z места: ");
    Float z=sc.nextFloat();
    System.out.println("Введите название места: ");
    String name=sc.nextLine();
    Location location=new Location(x,y,z,name);
    return location;}

    public Person getPerson(Scanner sc){
        String name=PersonAdder.setName(sc);
        Coordinates coordinates=PersonAdder.setCoordinates(sc);
        LocalDate creationDate=PersonAdder.setCreationDate();
        Integer height=PersonAdder.setHeight(sc);
        LocalDate birthday = PersonAdder.setBirthdayDate(sc);
        String passportID=PersonAdder.setPassportID(sc);
        Country nationality = PersonAdder.setNationality(sc);
        Location location = PersonAdder.setLocation(sc);
        return new Person(6,name,new Coordinates(coordinates.getX(), coordinates.getY()),creationDate,height,birthday,passportID,nationality,new Location(location.getX(), location.getY(), location.getZ(), location.getName()));
    }
}

