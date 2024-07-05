package client.commands;

import server.managers.CollectionManager;
import common.exceptions.*;
import client.personadder.PersonAdder;
import client.console.Console;
import common.data.Person;
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

