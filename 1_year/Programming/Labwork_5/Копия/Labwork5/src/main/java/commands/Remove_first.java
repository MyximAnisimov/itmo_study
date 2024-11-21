package commands;

import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import tools.Console;

/**
 * Класс, содержащий команду "remove_first". Удаляет первый элемент коллекции
 */
public class Remove_first extends AbstractCommand{
    private final CollectionManager collMan;
    public Remove_first(CollectionManager collMan){
        super("remove_first","убрать первый элемент из коллекции");
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
        collMan.removeFirst();
        return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
    return false;
}
}
