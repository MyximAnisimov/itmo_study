package managers;

import collections.*;
import exceptions.IncorrectInputInScriptException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class CollectionManager {
    private Queue<Person> collection = new ArrayDeque<Person>();
    private Console console;
    private FileManager filemanager;
    private LocalDateTime lastTimeInit;
    private Scanner sc;
    public CollectionManager(FileManager filemanager){
        this.lastTimeInit=null;
        this.filemanager=filemanager;
        loadCollection();
    }
    public void setCollection(ArrayDeque<Person> collection){
        for (Person person : collection) {
            person.setName(person.getName());
            person.setCoordinates(person.getCoordinates());
            person.setHeight(person.getHeight());
            person.setBirthday(person.getBirthday());
            person.setPassportID(person.getPassportID());
            person.setNationality(person.getNationality());
        person.setLocation(person.getLocation());}

    }
    public Queue<Person> getCollection() {
        return collection;
    }

    public Person getFirst() {
        if (getCollection().isEmpty()) return null;
        return collection.peek();
    }
    public LocalDateTime getInitTime(){
        return lastTimeInit;
    }
    public Object[] getElements()
    {
        return collection.toArray();
    }
    private void loadCollection() {
        collection = (PriorityQueue<Person>) filemanager.readCollection();
    }
    public String getType(){
        return collection.getClass().getName();
    }
    public void addElement(Person person) {
        collection.add(person);
    }
}
