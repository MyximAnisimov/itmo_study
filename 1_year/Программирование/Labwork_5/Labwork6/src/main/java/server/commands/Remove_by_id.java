package server.commands;

import client.console.Console;
import common.exceptions.MustBeNotEmptyException;
import common.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;

/**
 * Класс, содержащий команду "remove_by_id". Удаляет элемент коллекции по введённому пользователем id
 */
public class Remove_by_id extends AbstractCommand {
    private final CollectionManager collMan;
    public Remove_by_id(CollectionManager collMan){
        super("remove_by_id","удалить элемент из коллекции по его id");
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
            if(argument.isEmpty()) throw new WrongAmountOfElementsException();
        int id = Integer.parseInt(argument);
            if(collMan.getById(id) == null) throw new MustBeNotEmptyException();
        collMan.removeElementByID(id);
        Console.printLn("Элемент был удалён успешно!");
        return true;
    } catch (WrongAmountOfElementsException e){
        Console.printError("Аргумент в " + getName()+" не введён");
    } catch (NumberFormatException e) {
        Console.printError("id должно быть в формате int");
    } catch (MustBeNotEmptyException e) {
        Console.printError("Человек с введённым id не найден");
    }
        return false;
}
    }

