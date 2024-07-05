package server.commands;
import common.exceptions.*;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "exit". Выход из программы
 */
public class Exit extends AbstractCommand {

    public Exit(){
      super("exit","выход из программы");
  }
    /**
     *Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try{
        if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException(); {
        System.exit(0);
        return true;}}
        catch (WrongAmountOfElementsException exception) {
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }

        return false;
}}
