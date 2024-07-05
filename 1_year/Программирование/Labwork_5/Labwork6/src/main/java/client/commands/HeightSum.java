package client.commands;

import server.managers.CollectionManager;
import common.exceptions.*;
import client.personadder.PersonAdder;
import client.console.Console;
import common.data.Person;

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
    public boolean execute(String argument){
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
         int heightSum=0;
        for(Person person: collMan.getCollection()){
            heightSum= heightSum+person.getHeight();
        }
        Console.printLn(heightSum);
        return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;
}}
