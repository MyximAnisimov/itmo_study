package server.commands;

import client.console.Console;
import common.exceptions.WrongAmountOfElementsException;

/**
 * Класс, содержащий команду "exit". Выход из программы
 */
public class Exit extends AbstractCommand {

    public Exit(){
      super("exit","выход из программы");
  }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
        try{
        if(!argument.isEmpty()) throw new WrongAmountOfElementsException(); {
        System.exit(0);
        return true;}}
        catch (WrongAmountOfElementsException exception) {
            Console.printLn("Использование: '" + getName() + "'");
        }

        return false;
}}
