package server.commands;

import client.console.Console;
import common.exceptions.WrongAmountOfElementsException;
import server.managers.CollectionManager;

/**
 * Класс, содержащий команду "class_less_than_location". Подсчитывает количество элементов коллекции,
 * у которых поле name класса location меньше, чем введённое пользователем с клавиатуры
 */
public class Count_less_than_location extends AbstractCommand {
    private final CollectionManager collMan;
    public Count_less_than_location(CollectionManager collMan){
        super("сount_less_than_location", "вывести количество элементов, значение поля location которых меньше заданного");
        this.collMan=collMan;
    }
    /**
     * Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
        String location_name=argument;
        collMan.countElementsLessThanLocation(location_name);
        return true;
    }
    catch (WrongAmountOfElementsException e){
        Console.printError("Использование (" + argument + ") в " + getName());
    }
        return false;
}}
