package server.commands;

import common.collections.Person;
import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

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
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument)  {
        try{
            if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
       for(Person person : collMan.getCollection()){
           CustomConsole.printLn(person);
       }
       return true;
    } catch (WrongAmountOfElementsException exception) {
        CustomConsole.printLn("Использование: '" + getName() + "'");
    }
        return false;

}}
