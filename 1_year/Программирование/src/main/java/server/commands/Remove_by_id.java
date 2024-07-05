package server.commands;

import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "remove_by_id". Удаляет элемент коллекции по введённому пользователем id
 */
public class Remove_by_id extends AbstractCommand{
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
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try{
            if(commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
        int id = Integer.parseInt(commandStringArgument);
            if(collMan.getById(id) == null) throw new MustBeNotEmptyException();
        collMan.removeElementByID(id);
        CustomConsole.printLn("Элемент был удалён успешно!");
        return true;
    } catch (WrongAmountOfElementsException e){
        CustomConsole.printError("Аргумент в " + getName()+" не введён");
    } catch (NumberFormatException e) {
        CustomConsole.printError("id должно быть в формате int");
    } catch (MustBeNotEmptyException e) {
        CustomConsole.printError("Человек с введённым id не найден");
    }
        return false;
}
    }

