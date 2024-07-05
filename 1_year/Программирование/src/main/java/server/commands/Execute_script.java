package server.commands;

import common.exceptions.*;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "execute_script". Выполнить скрипт из укзанного текстового файла
 */
public class Execute_script extends AbstractCommand {
    public Execute_script(){
        super("execute_script","считать и исполнить скрипт из указанного файла");
    }
    /**
     * Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument) {
        try {
            if (commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
            CustomConsole.printLn("Исполнение скрипта '" + commandStringArgument + "'");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }

}
