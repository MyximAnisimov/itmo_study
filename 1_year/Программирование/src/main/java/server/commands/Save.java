package server.commands;
import common.exceptions.*;
import server.managers.CollectionManager;
import server.managers.FileManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "save". Сохраняет коллекцию в файл
 */
public class Save extends AbstractCommand {
    private final CollectionManager collMan;
    private final FileManager fileManager;
    public Save(CollectionManager collMan, FileManager fileManager){
        super("save","сохранить коллекцию в файл");
        this.collMan=collMan;
        this.fileManager=fileManager;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try{
            if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
collMan.saveCollection();
return true;
    } catch (WrongAmountOfElementsException exception) {
        CustomConsole.printLn("Использование: '" + getName() + "'");
    }
        return false;
    }
}
