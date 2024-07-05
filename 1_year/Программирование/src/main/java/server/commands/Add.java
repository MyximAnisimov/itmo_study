package server.commands;

import common.collections.Person;
import common.exceptions.*;
import server.managers.CollectionManager;
import server.managers.PersonAdder;
import common.utility.CustomConsole;
import common.utility.Validator;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс, содержащий команду "add". Добавляет новый элемент в коллекцию
 */
public class Add extends AbstractCommand {
    private final PersonAdder personAdder;
    private final CollectionManager collMan;
    public Add( CollectionManager collMan, PersonAdder personAdder){
        super("add ","Добавление нового элемента в коллекцию");
        this.collMan=collMan;
        this.personAdder=personAdder;
    }

    /**
     * Функция. Добавляет новый элемент в коллекцию
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String commandStringArgument, Serializable commandObjectArgument){
            Scanner sc=new Scanner(System.in);
        try{
            if(!commandStringArgument.isEmpty()) throw new WrongAmountOfElementsException();
                collMan.addToCollection(new Person(
                        collMan.generatorID(),
                        personAdder.setName(sc),
                        personAdder.setCoordinates(sc),
                        personAdder.setCreationDate(),
                        personAdder.setHeight(sc),
                        personAdder.setBirthdayDate(sc),
                        personAdder.setPassportID(sc),
                        personAdder.setNationality(sc),
                        personAdder.setLocation(sc)
                ));
            }catch(IncorrectInputInScriptException ignored){
            } catch (WrongAmountOfElementsException e){
            CustomConsole.printError("Использование (" + commandStringArgument+ ") в " + getName());
        }
            catch(OutOfLimitsException ignored){}
            catch(WrongDateFormatException ignored){}
            catch(UnknownCountryException ignored){}
        Validator validator=new Validator(personAdder.getCollectionManager());
        validator.validate();
        return true;
    }}
