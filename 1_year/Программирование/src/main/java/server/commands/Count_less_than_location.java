package server.commands;

import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "class_less_than_location". Подсчитывает количество элементов коллекции,
 * у которых поле name класса location меньше, чем введённое пользователем с клавиатуры
 */
public class Count_less_than_location extends AbstractCommand{
    private final CollectionManager collMan;
    public Count_less_than_location(CollectionManager collMan){
        super("сount_less_than_location", "вывести количество элементов, значение поля location которых меньше заданного");
        this.collMan=collMan;
    }
    /**
     * Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try{
            if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
        String location_name=commandStringArgument;
        collMan.countElementsLessThanLocation(location_name);
        return true;
    }
    catch (WrongAmountOfElementsException e){
        CustomConsole.printError("Использование (" + commandStringArgument + ") в " + getName());
    }
        return false;
}}
