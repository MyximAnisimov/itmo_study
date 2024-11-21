import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;
import managers.PersonAdder;
import tools.Console;
import tools.Validator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName="../data/" + System.getenv("VAR");
        System.out.println(fileName);
        FileManager fm=new FileManager("data/JSON.json");
   CollectionManager collMan=new CollectionManager(fm);
        Scanner sc = new Scanner(System.in);
        PersonAdder personAdder = new PersonAdder(collMan);

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
                new Save(collMan,fm),
                new Count_less_than_location(collMan),
                new Execute_script(),
                new Help(), new Clear(collMan), new Remove_by_id(collMan,personAdder),
                new Update_by_id(collMan, personAdder)
        );
        if(args.length == 0){
            collMan.setCollection(fm.readCollection());
        } else {
            if(fm.readCollection().size() != 0){
                collMan.setCollection(fm.readCollection());
            }
        }
        Validator validator =new Validator(collMan.getCollection());
        validator.validate();
        Console console = new Console(commandManager, sc,personAdder);
        console.interactiveMode();
}}