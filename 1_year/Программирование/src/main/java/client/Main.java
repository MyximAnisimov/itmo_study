package client;

import client.utility.UserHandler;
import common.exceptions.OutOfLimitsException;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;
import server.CommandExecutor;
import server.commands.*;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.FileManager;
import server.managers.PersonAdder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    private static final int RECONNECTION_TIMEOUT = 5 * 1000;
    private static final int MAX_RECONNECTION_ATTEMPTS = 5;

    private static String host;
    private static int port;

    /**
     * It takes a String array of two elements,
     * and if the array has exactly two elements, it parses the second element as an integer,
     * and if the integer is in the range [0, 65535], it sets the host and port variables to the
     * corresponding values
     *
     * @param hostAndPortArgs the array of arguments passed to the main method.
     * @return Nothing.
     */
    private static boolean initializeConnectionAddress(String[] hostAndPortArgs) {
        try {
            if (hostAndPortArgs.length != 2) throw new WrongAmountOfElementsException();
            host = hostAndPortArgs[0];
            port = Integer.parseInt(hostAndPortArgs[1]);
            if (port < 0) throw new OutOfLimitsException();
            return true;
        } catch (WrongAmountOfElementsException exception) {
            String jarName = new java.io.File(Main.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath())
                    .getName();
            CustomConsole.printLn("Usage: 'java -jar " + jarName + " <host> <port>'");
        } catch (NumberFormatException exception) {
            CustomConsole.printError("The port must be represented by a number!");
        } catch (OutOfLimitsException exception) {
            CustomConsole.printError("The port cannot be negative!");
        }
        return false;
    }
    public static void main(String[] args) {
FileManager fileManager = new FileManager("data/JSON.json");
    CollectionManager collMan = new CollectionManager(fileManager);

    Scanner sc = new Scanner(System.in);
    PersonAdder personAdder = new PersonAdder(sc);
    CustomConsole customConsole = new CustomConsole(personAdder,sc);
        UserHandler userHandler = new UserHandler(sc);
        CommandManager commandManager = new CommandManager(
                new Add(collMan, personAdder),
                new Info(collMan),
                new Show(collMan),
                new Head(collMan),
                new Remove_first(collMan),
                new Exit(),
                new HeightSum(collMan),
                new Print_field_descending_passport_id(collMan),
                new Remove_greater(collMan,personAdder),
                new Save(collMan,fileManager),
                new Count_less_than_location(collMan),
                new Execute_script(),
                new Help(), new Clear(collMan), new Remove_by_id(collMan),
                new Update_by_id(collMan, personAdder)
        );
        try {
            UDPclient client = new UDPclient(InetAddress.getLocalHost(), host, port, RECONNECTION_TIMEOUT, MAX_RECONNECTION_ATTEMPTS, userHandler);
            client.run();
        customConsole.interactiveMode();}
        catch(UnknownHostException e){
            System.out.println("Ошибка!");}

    }
}
