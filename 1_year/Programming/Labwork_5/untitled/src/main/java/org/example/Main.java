package org.example;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.util.*;


public class Main {
    public static List<Person> list = new ArrayList<>();
    public static void main(String[] args) {
        TreeSet<Person> tree=new TreeSet<>();
        tree.add(new Person(5,"zaxim"));
        tree.add(new Person(1,"rekdowekdowekd"));
        tree.add(new Person(2,"akjjiljkijljljljlj"));
        list.add(new Person(5,"zaxim"));
        list.add(new Person(1,"rekdowekdowekd"));
        list.add(new Person(2,"a"));
       Collections.sort(list,new IDComparator());
//for(Person person : tree){
  //  System.out.println(person.getName());
//}
HashSet<Person> HS=new HashSet<>();
        HS.add(new Person(5,"zaxim"));
        HS.add(new Person(1,"rekdowekdowekd"));
        HS.add(new Person(2,"akjjiljkijljljljlj"));
        for(Person person:HS){
            //System.out.println(person.getName());
        }
        try(FileInputStream fis = new FileInputStream("lab.txt")){
                int i;
              while((i=fis.read())!=-1){
                 // System.out.print((char) i);
              }

        }
        catch(IOException e){
            System.out.println("Ошибка!");
        }
       People p1 = new People("sdfdgdf");
        p1.list.add("ergfrfd");
        p1.list.add("ergfrfd");
        p1.list.add("ergfrfd");
        p1.list.add("ergfrfd");
        p1.GenericMethod();
        Dima d1=new Dima();
 List<? extends People> list1= new ArrayList<People>();
 List<? extends People> list2 = new ArrayList<Dima>();
 Class cl1=d1.getClass();

        try(FileReader fis = new FileReader("lab.txt")){
            BufferedReader isr = new BufferedReader(fis);
            System.out.println(isr.readLine());
        }
        catch(FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        catch(IOException e){
            System.out.println("Error!");
        }

    }
    public static void print(){
        Iterator<Person> iterator= list.iterator();
        while(iterator.hasNext()){
            Person person = iterator.next();
            System.out.println(person.getName());
        }}

}