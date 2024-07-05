package client.commands;

import server.managers.CollectionManager;
import common.exceptions.*;
import client.personadder.PersonAdder;
import client.console.Console;
import common.data.Person;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Класс, содержащий команду "print_field_descending_passport_id". Сортирует элементы коллекции по ID паспорта в порядке убывания
 */
public class Print_field_descending_passport_id extends AbstractCommand {
    private final CollectionManager collMan;
    public Print_field_descending_passport_id(CollectionManager collMan){
        super("print_field_descending_passport_id","вывод значения поля passportID всех элементов в порядке убывания");
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
        ArrayList<Person> array = new ArrayList<>(collMan.getCollection());
array.sort(Collections.reverseOrder());
for(Person person:array){
    System.out.println(person);
}
        return true;
    } catch (WrongAmountOfElementsException exception) {
        Console.printLn("Использование: '" + getName() + "'");
    }
        return false;
}
    }
