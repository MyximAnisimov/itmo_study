package server.commands;

import client.console.Console;
import common.data.Person;
import common.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;

/**
 * Класс, содержащий команду "show". Вывести на экран все коллекции
 */
public class Show extends AbstractCommand {
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
       for(Person person : collMan.getCollection()){
           Console.printLn(person);
       }
       return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;

}}
