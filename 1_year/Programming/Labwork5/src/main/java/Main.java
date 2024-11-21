import collections.*;
import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;
import tools.Interrogator;
import tools.Runner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console=new standardConsole();
        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        var filemanager = new FileManager(args[0], console);
        var collectionManager = new CollectionManager(filemanager);

            Interrogator.setUserScanner(new Scanner(System.in));
        CommandManager ComMan = new CommandManager(){{
        register("head", new Head(collectionManager));
        register("info",new Info(collectionManager));
        register("show",new Show(console,collectionManager));
        register("ADD",new AddCollection(collectionManager));}};
        new Runner(console,ComMan).interactiveMode();
}}