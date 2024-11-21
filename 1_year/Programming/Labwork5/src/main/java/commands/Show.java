package commands;

import collections.Console;
import collections.Person;
import managers.CollectionManager;

import java.lang.reflect.Field;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Show implements Command{
    private final CollectionManager ComMan;
    private Console console;
    public Show(Console console,CollectionManager ComMan){
        this.ComMan=ComMan;
    }
    @Override
public String descr(){
        return "Show - показать все элементы коллекции";
    }
    @Override
    public void execute()  {

console.println(ComMan);
        }}

