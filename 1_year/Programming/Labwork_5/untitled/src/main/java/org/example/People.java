package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class People<T> implements Generics{
    public List<T> list=new ArrayList<>();
    private String name;
    public People(String name){
        this.name=name;
    }
    @Override
    public void GenericMethod(){
       for(T object:list){
           System.out.println(object);
       }
    }
}

