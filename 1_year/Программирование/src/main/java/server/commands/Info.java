package server.commands;

import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "info". Выводит информацию о коллекции
 */

public class Info extends AbstractCommand{
    private final CollectionManager collMan;
public Info(CollectionManager collMan){
    super("info","вывод информации о коллекции");
    this.collMan=collMan;
}
    /**
     *Выполняет команду
     *
     * @return Успешное выполнение команды
     */
@Override
public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
    try{
        if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
    CustomConsole.printLn("Тип коллекции: "+collMan.getType());
    CustomConsole.printLn("Количество элементов коллекции: "+collMan.getCollection().size());
    CustomConsole.printLn("Дата создания коллекции: "+collMan.getCreationDate());
    return true;
} catch (WrongAmountOfElementsException exception) {
        CustomConsole.printLn("Использование: '" + getName() + "'");
    }
        return false;
}

}
