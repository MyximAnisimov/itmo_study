package FMap;

import Actor.Human;

import java.util.LinkedList;
import java.util.List;

public class World {




    private static String dayTime = DayTime.DAY.getTime();
    private String name;
    public World(String name){
        this.name=name;
    }

    private LinkedList<Human> people = new LinkedList<Human>();

    public World(String name , Human[] p){
        this.name = name;
        people.addAll(List.of(p));
        for (Human human : p) {
            human.setCurrent_Location(name);
        }
    }

    // метод , который перенаправляет людей , берет в себя обджект и место
    // добавлять людей
    // удалять человека при перемещении , а метод удаления сокрыть
    // ...


    public void movePeople(World toPlace, Human human ){
        if (!this.people.contains(human)){
            System.out.println("человека нет в команте , кого вы пытаетесь перместить ?/ ");
        }else if (toPlace.people.contains(human)){
            System.out.println("Вы в своем уме? Вы хотите себя отправить на тоже место где вы уже есть?");
        }else if (toPlace.name == "Кабина" && toPlace.people.size() == 1){

            this.delPeople(human);
            System.out.println("В кабине 2 человека и Знайка заметил Грабителя");
            toPlace.addPeople(human);
            human.setCurrent_Location(toPlace.name);

        }else {
        this.delPeople(human);
        toPlace.addPeople(human);
        System.out.println("Вы отправили персонажа : " + human.getName() +" --> В локацию: " + toPlace.getName() );
        human.setCurrent_Location(toPlace.name);

        }




    }

    void addPeople(Human human){
        people.add(human);

    }
    void delPeople(Human human){
        people.remove(human);
    }


    public LinkedList<Human> getPeople() {
        return people;
    }













    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getDayTime() {
        return dayTime;
    }
}
