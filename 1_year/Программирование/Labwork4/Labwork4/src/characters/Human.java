package characters;

import environment.Floors;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Human implements Forgetable{
    private String name;
    private int age;
    private int id;
    private Floors floor;
public Human(String name, int age,int id){
    this.name=name;
    this.age=age;
    this.id=id;
}
public static class humanArray{
    LinkedList<Crowd> Array=new LinkedList<>();
    public humanArray(LinkedList<Crowd> List){
        for(int i=0;i<List.size();i++)
        this.Array.add(List.get(i));

    }
    public void getCrowd(){
        System.out.println(Array);
    }

}
public String getName(){
    return name;
}
public void setID(int id){
    this.id=id;
}
public int getID(){
    return id;
}
public void setFloors(Floors floor){
    this.floor=floor;
}
@Override
public int hashCode(){
    return id;
}
@Override
    public String toString(){
    return "Character{" +
            "name= "+name+
    ", age= "+age+'}';}
}
