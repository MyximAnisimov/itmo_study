package server;
import com.google.common.collect.Iterables;
import common.collections.Person;
import common.utility.CustomConsole;
import common.utility.ProductComparator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, управляющий коллекцией
 */

public class CollectionManager {
    public Deque<Person> collection;
    private LocalDate creationDate;

    public CollectionManager(DBhandler dbhandler) throws SQLException {
creationDate=LocalDate.now();
collection = dbhandler.downloadCurrentCollection();
//load();

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
//    public void saveCollection(){
//    fileManager.writeCollection(collection);
//}

    /**
     * Функция. Подсчитывает количество элементов коллекции,
     * у которых поле name класса location меньше, чем аргумент функции
     * @param location_name Название локации, с которой сравнивается поля name класса location
     */
    public int countElementsLessThanLocation(String location_name){
        int counter=0;
      for(int i=0;i<collection.size();i++){
        }
          return counter;
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
    public boolean validateAll() {
        for(var person : new ArrayList<>(get())) {
            if (!person.validate()) {
                CustomConsole.printLn("Продукт с id=" + person.getID() + " имеет невалидные поля.");
                return false;
            }
            if (!person.getCoordinates().validate()) {
                CustomConsole.printLn("Введённые координаты" + " имеют невалидные поля.");
                return false;
            }
            if (person.getLocation() != null) {
                if(!person.getLocation().validate()) {
                    CustomConsole.printLn("Введённая локация"+" имеет невалидные поля.");
                    return false;
                }
            }
        };
        CustomConsole.printLn("Загруженные продукты валидны.");
        return true;
    }

    /**
     * @return коллекция.
     */
    public Queue<Person> get() {
        return collection;
    }


    /**
     * @return Имя типа коллекции.
     */
    public String type() {
        return collection.getClass().getName();
    }

    /**
     * @return Размер коллекции.
     */
    public int size() {
        return collection.size();
    }

    /**
     * @return Первый элемент коллекции (null если коллекция пустая).
     */
    public Person first() {
        if (collection.isEmpty()) return null;
        return sorted().get(0);
    }

    /**
     * @return Последний элемент коллекции (null если коллекция пустая).
     */
    public Person last() {
        if (collection.isEmpty()) return null;
        return Iterables.getLast(sorted());
    }

    /**
     * @return Отсортированная коллекция.
     */
    public List<Person> sorted() {
        return new ArrayList<>(collection)
                .stream()
                .sorted(new ProductComparator())
                .collect(Collectors.toList());
    }
    public List<Person> getSorted(){
        return this.get().stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());}


    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id) {
        return getById(id) != null;
    }


    /**
     * Добавляет элемент в коллекцию
     * @param element Элемент для добавления.
     * @return id нового элемента
     */
    public void add(Person element) {
      collection.add(element);
    }

    /**
     * Удаляет элемент из коллекции.
     * @param id ID элемента для удаления.
     */
    public void remove(int id) {
        for(Person person: this.getCollection()){
            if(person.getID()==id){
                this.removeElement(person);
            }
    }}


    /**
     * Сохраняет коллекцию в файл
     */
//    public void save() {
//        fileManager.writeCollection(collection);
//    }


}


