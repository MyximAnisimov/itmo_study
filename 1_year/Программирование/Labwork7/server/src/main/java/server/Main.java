package server;

import common.utility.CustomConsole;
import server.commands.*;
import server.managers.CommandManager;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class Main{

    public static final int port = 11111;
    public static void main(String[] args) throws SQLException, IOException, ExecutionException, InterruptedException {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");}
        catch(ClassNotFoundException e){
            CustomConsole.printLn("Ошибка");
        }
        var DBHandler = new DBhandler();
        var collMan = new CollectionManager(DBHandler);

        CommandManager commandManager =  new CommandManager() {{
            register("help", new Help(this));
            register("info", new Info(collMan));
            register("show", new Show(collMan));
            register("add", new Add(collMan));
            register("remove_by_id", new Remove_by_id(collMan));
            register("clear", new Clear(collMan));
            register("head", new Head(collMan));
            register("count_less_than_location", new Count_less_than_location(collMan));
            register("heightSum", new HeightSum(collMan));
            register("print_field_descending_passport_id", new Print_field_descending_passport_id(collMan));
            register("remove_first", new Remove_first(collMan));
            register("remove_greater", new Remove_greater(collMan));
            register("update_by_id", new Update_by_id(collMan));
            register("login", new Login());
            register("no_command", new NoCommand());
            register("sign_up", new SignUp());
        }};
        System.out.println(collMan.collection);
        var server = new UDPDatagramServer(InetAddress.getLocalHost(),port, new CommandExecutor(commandManager));
        server.run();


    }

 }

