package server.commands;

import common.exceptions.*;
import common.utility.CustomConsole;

import java.io.Serializable;

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
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try {
            if (!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException exception) {
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }

}
