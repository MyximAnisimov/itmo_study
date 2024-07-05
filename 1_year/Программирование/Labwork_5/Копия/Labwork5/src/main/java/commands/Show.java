package commands;

import collections.Person;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import tools.Console;

import java.util.ArrayList;
/**
 * Класс, содержащий команду "show". Вывести на экран все коллекции
 */
public class Show extends  AbstractCommand {
    private final CollectionManager collMan;
    public Show(CollectionManager collMan){
        super("show","вывести все элементы коллекции");
        this.collMan=collMan;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument)  {
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
ArrayList<Person> array =new ArrayList<>(collMan.getCollection());
       for(Person person : array){
           Console.printLn(person);
       }
       return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;

}}
