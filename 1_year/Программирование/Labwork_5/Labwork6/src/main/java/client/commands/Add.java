package client.commands;

import client.clientUDP.UDPclient;
import server.managers.CollectionManager;
import common.exceptions.*;
import client.personadder.PersonAdder;
import client.console.Console;
import common.data.Person;
import common.validator.Validator;
import java.util.Scanner;

/**
 * Класс, содержащий команду "add". Добавляет новый элемент в коллекцию
 */
public class Add extends AbstractCommand {
    private final UDPclient client;
    private final Console console;
    public Add( UDPclient client, Console console){
        super("add ","Добавление нового элемента в коллекцию");
        this.client = client;
        this.console = console;
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
