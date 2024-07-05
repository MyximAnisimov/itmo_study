package server;

import server.commands.*;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.FileManager;
import server.managers.PersonAdder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager("data/JSON.json");
        CollectionManager collMan = new CollectionManager(fileManager);
        Scanner sc = new Scanner(System.in);
        PersonAdder personAdder = new PersonAdder(sc);
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
        CommandExecutor commandExecutor = new CommandExecutor(commandManager);
        try{
        UDPserver server = new UDPserver(InetAddress.getLocalHost(), 1234, commandExecutor);
        server.run();}
        catch(UnknownHostException e){
            System.out.println("Ошибка в мэйне!");
        }
    }
}