package characters;

import environment.searchable;
import environment.sensable;

public abstract class human implements forgetable,Interface{
    private String name;
    private int age;
    private int id;
public human(String name, int age, int id){
    this.name=name;
    this.age=age;
    this.id=id;
}

public String getName(){
    return name;
}
public void setID(int id){
    this.id=id;
}
@Override
public void getName2(String name){
    System.out.println(name);
}
public int getID(){
    return id;
}
@Override
    public String toString(){
    return "Character{" +
            "name= "+name+
    ", age= "+age+'}';}
}
