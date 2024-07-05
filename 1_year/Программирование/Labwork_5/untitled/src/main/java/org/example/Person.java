package org.example;

public class Person implements Comparable<Person>{
    private String name;
    private int id;
    public Person(int id, String name){
        this.id=id;
        this.name=name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    @Override
    public int compareTo(Person person){
        return this.getName().length()-person.getName().length();
    }
    @Override
    public int hashCode(){
        return id;
    }
}
