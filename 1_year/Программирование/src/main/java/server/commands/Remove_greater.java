package server.commands;

import common.collections.Person;
import common.exceptions.*;
import server.managers.CollectionManager;
import server.managers.PersonAdder;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;
/**
 * Класс, содержащий команду "remove_greater". Удаляет элементы коллекции, значение поля height которых превышает введённое пользователем число
 */
public class Remove_greater extends AbstractCommand {
    private final CollectionManager collMan;
    private final PersonAdder personAdder;
    public Remove_greater(CollectionManager collMan, PersonAdder personAdder){
        super("remove_greater","убрать элементы коллекции, превышающий заданный");
        this.collMan=collMan;
        this.personAdder=personAdder;
    }    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
        Scanner sc=new Scanner(System.in);
        try{
           Person newElement = new Person(
                   collMan.generatorID(),
                   personAdder.setName(sc),
                   personAdder.setCoordinates(sc),
                   personAdder.setCreationDate(),
                   personAdder.setHeight(sc),
                   personAdder.setBirthdayDate(sc),
                   personAdder.setPassportID(sc),
                   personAdder.setNationality(sc),
                   personAdder.setLocation(sc)
           );
            Collection<Person> toRemove = collMan.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(person -> person.compareTo(newElement) >= 1)
                    .toList();
            collMan.removeAllElements(toRemove);
        }
        catch(IncorrectInputInScriptException ignored){
        }
        catch(OutOfLimitsException ignored){}
        catch(WrongDateFormatException ignored){}
        catch(UnknownCountryException ignored){}
        return true;
    }
}
