package server.commands;

import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "clear". Очищает коллекцию
 */
public class Clear extends AbstractCommand{
    private final CollectionManager collMan;
    public Clear(CollectionManager collMan){
        super("clear","очистить коллекцию");
        this.collMan=collMan;
    }
    /**
     * Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try {
            if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
        collMan.clearCollection();
        CustomConsole.printLn("Коллекция очищена!");
        return true;
    } catch (WrongAmountOfElementsException e){
        CustomConsole.printError("Использование (" + commandStringArgument + ") в " + getName());
    }
        return false;
}
    }

