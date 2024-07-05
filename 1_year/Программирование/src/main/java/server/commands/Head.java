package server.commands;

import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;
import server.utility.ResponseOutputer;

import java.io.Serializable;

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
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try {
            if (!commandStringArgument.isEmpty() || commandObjectArgument != null) throw new WrongAmountOfElementsException();
            ResponseOutputer.appendLn(collMan.getFirstElement());
            return true;
        } catch (WrongAmountOfElementsException e){
            ResponseOutputer.appendLn("Usage: '" + getName() + "'");
        }
        return false;
}
    }

