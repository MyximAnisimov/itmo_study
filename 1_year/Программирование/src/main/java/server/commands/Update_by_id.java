package server.commands;

import common.collections.Person;
import common.exceptions.*;
import server.managers.CollectionManager;
import server.managers.PersonAdder;
import common.utility.CustomConsole;

import java.io.Serializable;
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
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
            int id = Integer.parseInt(commandStringArgument);
            Scanner sc = new Scanner(System.in);
            try{
                Person person = new Person(
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
                collMan.updateById(id, person);}
           catch(IncorrectInputInScriptException ignored){
    }
            catch(OutOfLimitsException ignored){}
            catch(WrongDateFormatException ignored){}
            catch(UnknownCountryException ignored){}
            CustomConsole.printLn("Коллекция изменена");
            return true;
    }
}
