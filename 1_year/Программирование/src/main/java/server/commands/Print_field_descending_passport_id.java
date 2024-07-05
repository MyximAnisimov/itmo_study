package server.commands;

import common.collections.Person;
import common.exceptions.*;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;
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
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        try{
            if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
        ArrayList<Person> array = new ArrayList<>(collMan.getCollection());
array.sort(Collections.reverseOrder());
for(Person person:array){
    System.out.println(person);
}
        return true;
    } catch (WrongAmountOfElementsException exception) {
        CustomConsole.printLn("Использование: '" + getName() + "'");
    }
        return false;
}
    }
