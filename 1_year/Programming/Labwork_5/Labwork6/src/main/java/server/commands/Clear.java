package server.commands;

import client.console.Console;
import common.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;

/**
 * Класс, содержащий команду "clear". Очищает коллекцию
 */
public class Clear extends AbstractCommand {
    private final CollectionManager collMan;
    public Clear(CollectionManager collMan){
        super("clear","очистить коллекцию");
        this.collMan=collMan;
    }
    /**
     * Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
        try {
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
        collMan.clearCollection();
        Console.printLn("Коллекция очищена!");
        return true;
    } catch (WrongAmountOfElementsException e){
        Console.printError("Использование (" + argument + ") в " + getName());
    }
        return false;
}
    }

