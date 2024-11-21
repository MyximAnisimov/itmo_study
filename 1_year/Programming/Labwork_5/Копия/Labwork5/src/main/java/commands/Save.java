package commands;

import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import managers.FileManager;
import tools.Console;

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
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
collMan.saveCollection();
return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;
    }
}
