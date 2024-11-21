package commands;

import collections.Person;
import exceptions.*;
import managers.CollectionManager;
import managers.PersonAdder;
import tools.Console;

import java.util.Scanner;
/**
 * Класс, содержащий команду "update_id". Позволяет обновить элемент коллекции по введённому пользователем id
 */
public class Update_by_id extends AbstractCommand {
    private final CollectionManager collMan;
    private final PersonAdder personAdder;
    public Update_by_id(CollectionManager collMan, PersonAdder personAdder){
        super("update_id","изменение элемента коллекции по его id");
        this.collMan=collMan;
        this.personAdder=personAdder;
    }
    /**
     * Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
            int id = Integer.parseInt(argument);
            Scanner sc = new Scanner(System.in);
            try{
                Person person = new Person(
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
                collMan.updateById(id, person);}
           catch(IncorrectInputInScriptException ignored){
    }
            catch(OutOfLimitsException ignored){}
            catch(WrongDateFormatException ignored){}
            catch(UnknownCountryException ignored){}
            Console.printLn("Коллекция изменена");
            return true;
    }
}
