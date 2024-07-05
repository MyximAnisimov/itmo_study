package client.commands;

import server.managers.CollectionManager;
import common.exceptions.*;
import client.personadder.PersonAdder;
import client.console.Console;
import common.data.Person;
/**
 * Класс, содержащий команду "help". Выводит справку по командам
 */
public class Help extends AbstractCommand {
    public Help(){
        super("help"," вывести все доступные команды");
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }

}
