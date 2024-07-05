package client.commands;
import common.utility.CustomConsole;
import common.utility.UserIDNumber;

import static client.commands.Login.UID;

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
    public boolean execute(String [] arguments){
        if (!arguments[1].isEmpty()) {
            CustomConsole.printLn("Использование: '" + getName() + "'");
            return false;
        }

        CustomConsole.printLn("Завершение выполнения...");
        return true;
    }
    public UserIDNumber getUID(){
        return UID;
    }
}
