package server;

import client.commands.*;
import client.personadder.PersonAdder;
import org.gradle.internal.impldep.org.apache.commons.lang.UnhandledException;
import server.managers.CollectionManager;
import server.managers.CommandExecuteManager;
import server.managers.CommandManager;
import server.managers.FileManager;
import server.serverUDP.UDPserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

       try{
           String fileName = "../data/JSON.json";
           System.out.println(fileName);
           FileManager fm=new FileManager("data/JSON.json");
           CollectionManager collMan=new CollectionManager(fm);
           Scanner sc = new Scanner(System.in);
           PersonAdder personAdder = new PersonAdder(collMan);
           var commandManager = new CommandManager() {{
               register("add", new Add(collMan, personAdder));
               register("clear", new Clear(collMan));
               register("count_less_than_location", new Count_less_than_location(collMan));
               register("execute_script", new Execute_script());
               register("head", new Head(collMan));
               register("height_sum", new HeightSum(collMan));
               register("help", new Help());
               register("info", new Info(collMan));
               register("print_field_descending_passport", new Print_field_descending_passport_id(collMan));
               register("remove_by_id", new Remove_by_id(collMan));
               register("remove_first", new Remove_first(collMan));
               register("remove_greater", new Remove_greater(collMan, personAdder));
               register("show", new Show(collMan));
           }};
           CommandExecuteManager CEM = new CommandExecuteManager(commandManager);
           UDPserver serv = new UDPserver(CEM,1234);
           serv.run();
       }
       catch(UnknownHostException e){
           System.out.println("Ошибка!");
       }
       catch(IOException e){
           System.out.println("Ошибка!");
       }
    }
}