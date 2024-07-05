package server;

import common.utility.VisibilityArgument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.commands.*;
import server.managers.CommandManager;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;


public class Main {
    public static final Logger logger=LogManager.getLogger("svs");

    public static final int port = 11111;
    public static void main(String[] args) throws SQLException, IOException {
        var DBHandler = new DBhandler();
        var collMan = new CollectionManager(DBHandler);
        Login login = new Login(collMan);
        CommandManager commandManager = new CommandManager();
        if(login.VA.getGlobalArgumentTrue()){
            commandManager.register("help", new Help(commandManager));
            commandManager.register("login", new Login(collMan));
        }
        else{ commandManager = new CommandManager() {{
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
            register("login", new Login(collMan));
            register("no_command", new NoCommand());
        }};}
        System.out.println(collMan.collection);
        var server = new UDPDatagramServer(InetAddress.getLocalHost(),port, new CommandExecutor(commandManager));

        server.run();
    }

 }

