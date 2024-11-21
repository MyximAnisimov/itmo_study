package collections;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Collection;

public class Example {
   public ArrayDeque<Person> excoll=new ArrayDeque<>();
   public void addElement(Person person){
      excoll.add(person);
   }
   public void PrintColl(){
      System.out.println(excoll);
   }
}
