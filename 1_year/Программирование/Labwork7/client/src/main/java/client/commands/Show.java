package client.commands;

import client.UDPclient;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;
import common.utility.UserIDNumber;
import common.utility.requests.ShowCommandRequest;
import common.utility.response.ShowCommandResponse;

import java.io.IOException;

import static client.commands.Login.UID;

/**
 * Класс, содержащий команду "show". Вывести на экран все коллекции
 */
public class Show extends AbstractCommand {
    private final UDPclient client;
    public Show(UDPclient client){
        super("show","вывести все элементы коллекции");
        this.client= client;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments)  {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var response = (ShowCommandResponse) client.sendAndReceiveCommand(new ShowCommandRequest());

            if (response.person.isEmpty()) {
                CustomConsole.printLn("Коллекция пуста!");
                return true;
            }

            for (var person : response.person) {
                CustomConsole.printLn(person + "\n");
            }
            return true;
        } catch(IOException e) {
           CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
        catch (WrongAmountOfElementsException exception) {
            CustomConsole.printError("Неправильное количество аргументов!");
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }
        catch(ClassCastException e){
            CustomConsole.printError("Не зареган");
        }
        return false;
    }
    public UserIDNumber getUID(){
        return UID;
    }
}
