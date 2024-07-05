package server.commands;

import common.collections.Person;
import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "HeightSum". Подсчитывает сумму всех значений полей height элементов коллекции
 */
public class HeightSum extends AbstractCommand{
    private final CollectionManager collMan;
    public HeightSum(CollectionManager collMan){
        super("height_sum","вывести сумму значений поля height для всех элементов коллекции");
        this.collMan=collMan;
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
         int heightSum=0;
        for(Person person: collMan.getCollection()){
            heightSum= heightSum+person.getHeight();
        }
        CustomConsole.printLn(heightSum);
        return true;
    } catch (WrongAmountOfElementsException exception) {
        CustomConsole.printLn("Использование: '" + getName() + "'");
    }
        return false;
}}
