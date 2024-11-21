package client.commands;

import server.managers.CollectionManager;
import common.exceptions.*;
import client.personadder.PersonAdder;
import client.console.Console;
import common.data.Person;
/**
 * Класс, содержащий команду "head". Выводит первый элемент коллекции
 */
public class Head extends AbstractCommand {
    private final CollectionManager collMan;
    public Head(CollectionManager collMan){
super("head","вывод первого элемента коллекции");
        this.collMan=collMan;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
try{
    if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
    Console.printLn(collMan.getFirstElement());
        return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;
}
    }

