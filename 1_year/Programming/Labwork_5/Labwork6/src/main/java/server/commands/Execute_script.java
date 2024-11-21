package server.commands;

import client.console.Console;
import common.exceptions.WrongAmountOfElementsException;
/**
 * Класс, содержащий команду "execute_script". Выполнить скрипт из укзанного текстового файла
 */
public class Execute_script extends AbstractCommand {
    public Execute_script(){
        super("execute_script","считать и исполнить скрипт из указанного файла");
    }
    /**
     * Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.printLn("Исполнение скрипта '" + argument + "'");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }

}
