package org.example;

import java.util.*;

public class IDComparator implements Comparator<Person> {
    List<Person> list = new ArrayList<>();
    @Override
    public int compare(Person person1, Person person2){
        Integer id1=person1.getId();
        Integer id2= person2.getId();
        return id1.compareTo(id2);
    }

}
