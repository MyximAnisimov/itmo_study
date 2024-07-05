package commands;

import collections.Person;
import managers.CollectionManager;
import managers.PersonAdder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AddCollection implements Command{
    private final CollectionManager CollMan;
    Scanner sc = new Scanner(System.in);
    Person prsn;
    private List<Person> PersonList=new ArrayList<>();

    public AddCollection(CollectionManager CollMan){
        this.CollMan=CollMan;
    }
    @Override
    public String descr(){
        return "Add - добавление элемента в коллекцию ";
    }
    @Override
    public void execute(){

        CollMan.addElement(new PersonAdder(CollMan).getPerson(sc));
    }

}
