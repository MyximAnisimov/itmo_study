package client.commands;

import client.UDPclient;
import common.utility.CustomConsole;
import common.utility.VisibilityArgument;
import common.utility.requests.InfoCommandRequest;
import common.utility.requests.NoCommand;
import common.utility.response.InfoCommandResponse;
import common.utility.response.NoCommandResponse;
import common.utility.response.Response;

import java.io.IOException;

/**
 * Класс, содержащий команду "info". Выводит информацию о коллекции
 */

public class Info extends AbstractCommand {
    private final UDPclient client;
public Info(UDPclient client){
    super("info","вывод информации о коллекции");
    this.client = client;
}
    /**
     *Выполняет команду
     *
     * @return Успешное выполнение команды
     */
@Override
public boolean execute(String [] arguments){

        if(!arguments[1].isEmpty()) {
            CustomConsole.printError("Неправильное количество аргументов");
        return false;}
            try {

                var response = (InfoCommandResponse) client.sendAndReceiveCommand(new InfoCommandRequest());

                CustomConsole.printLn(response.infoMessage);
                return true;
            }
         catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
            catch(ClassCastException e){
                CustomConsole.printError("Пользователь не зарегистрирован!");
            }
        return false;

}}
