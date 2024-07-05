package server.commands;

import client.console.Console;
import client.personadder.PersonAdder;
import common.data.Person;
import common.exceptions.*;
import common.validator.Validator;
import server.managers.CollectionManager;

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
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String argument){
            Scanner sc=new Scanner(System.in);
        try{
            if(!argument.isEmpty()) throw new WrongAmountOfElementsException();
                collMan.addToCollection(new Person(
                        personAdder.setID(),
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
            Console.printError("Использование (" + argument + ") в " + getName());
        }
            catch(OutOfLimitsException ignored){}
            catch(WrongDateFormatException ignored){}
            catch(UnknownCountryException ignored){}
        Validator validator=new Validator(personAdder.getCollectionManager());
        validator.validate();
        return true;
    }}
