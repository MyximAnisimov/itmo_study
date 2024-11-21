package server.commands;

import client.personadder.PersonAdder;
import common.data.Person;
import common.exceptions.IncorrectInputInScriptException;
import common.exceptions.OutOfLimitsException;
import common.exceptions.UnknownCountryException;
import common.exceptions.WrongDateFormatException;
import server.managers.CollectionManager;

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
    public boolean execute(String argument){
        Scanner sc=new Scanner(System.in);
        try{
           Person newElement = new Person(
                   personAdder.setID(),
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
