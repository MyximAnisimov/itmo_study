package server.managers;

import common.data.Person;
import client.console.Console;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

/**
 * Класс, управляющий коллекцией
 */

public class CollectionManager {
    public Deque<Person> collection = new ArrayDeque<>();
    private final FileManager fileManager;
    private LocalDate creationDate;

    public CollectionManager(FileManager fileManager){
        this.fileManager=fileManager;
creationDate=LocalDate.now();
    }

    /**
     * @return первый элемент коллекции (null, если коллекция пустая)
     */
    public Person getFirstElement(){
        if(collection.isEmpty()){
            return null;
        }
return collection.peekFirst();
    }

    /**
     * @return Тип коллекции
     */
    public String getType(){
        return collection.getClass().getTypeName();
    }

    /**
     * @return дата создания коллекции
     */
    public LocalDate getCreationDate(){
        return creationDate;
    }

    /**
     * Устанавливает коллекцию, принятую в качестве аргумента функции
     * @param person Коллекция элементов класса Person
     */
    public void setCollection(ArrayDeque<Person> person) {
        this.collection = person;
    }

    /**
     * @return коллекция
     */
    public Deque<Person> getCollection(){
        return collection;
    }

    /**
     * Функция, удаляющая первый элемент коллекции
     */
    public void removeFirst(){
        collection.removeFirst();
    }

    /**
     *Добавляет новый элемент коллекции класса Person
     * @param person новый элемент класса Person
     */
    public void addToCollection(Person person){

        collection.add(person);
    }

    /**
     * Функция, возвращающая элемент класса Person по его id
     * @param id поле id класса Person
     * @return элемент класса Person (null, если элемент с данным id не найден)
     */
    public Person getById(int id){
        for (Person person: collection) {
            if(person.getID() == id) return person;
        }
        return null;
    }

    /**
     * Функция. Удаляет элемент коллекции
     * @param person элемент коллекции, который необходимо удалить
     */
    public void removeElement(Person person){
        collection.remove(person);
    }

    /**
     * Функция. Удаляет элемент коллекции по его id
     * @param id поле id класса Person
     */
    public void removeElementByID(int id){
        for(Person person: this.getCollection()){
            if(person.getID()==id){
                this.removeElement(person);
            }
        }
    }

    /**
     * Функция. Меняет элемент коллекции
     * @param id поле id элемента коллекции, который необходимо поменять
     * @param newPerson элемент с новыми параматерами
     */
    public void updateById(int id, Person newPerson){
        Person pastElement = this.getById(id);
        this.removeElement(pastElement);
        this.addToCollection(newPerson);
        }

    /**
     * Функция, удаляющая элементы коллекции
     * @param collection название коллекции, элементы которой надо удалить
     */
    public void removeAllElements(Collection<Person> collection){this.collection.removeAll(collection);}

    /**
     * Функция, сохраняющая коллекцию в файл
     */
    public void saveCollection(){
    fileManager.writeCollection(collection);
}

    /**
     * Функция. Подсчитывает количество элементов коллекции,
     * у которых поле name класса location меньше, чем аргумент функции
     * @param location_name Название локации, с которой сравнивается поля name класса location
     */
    public void countElementsLessThanLocation(String location_name){
        int counter=0;
      for(int i=0;i<collection.size();i++){
          if(collection.element().getLocation().getName().length()<location_name.length()){
              counter++;
          }}
          Console.printLn(counter);
    }

    /**
     * Очищает коллекцию
     */
    public void clearCollection(){
        this.collection.clear();
    }

    /**
     * Генерирует id для элементов коллекции
     * @return id каждого элемента коллекции
     */
    public int generatorID(){
        int id = collection.stream()
                .mapToInt(Person::getID)
                .filter(person -> person >= 0)
                .max().orElse(0);
        return id + 1;
    }
}


