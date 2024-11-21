package commands;

import collections.Person;
import exceptions.WrongAmountOfElementsException;
import managers.CollectionManager;
import tools.Console;

import java.util.ArrayList;
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

        ArrayList<Person> array = new ArrayList<Person>(collMan.getCollection());
         int heightSum=0;
        for(Person person: array){
            heightSum= heightSum+person.getHeight();
        }
        Console.printLn(heightSum);
        return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;
}}
