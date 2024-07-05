package server;

import common.utility.CustomConsole;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import server.commands.*;
import server.managers.AuthorizationManager;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.PersistenceManager;
import server.utility.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs","s379791", "mK509Zk9bkpmQA7N");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final int port = 11111;
    public static Logger logger = LogManager.getLogger("ServerLogger");
public static Dotenv dotenv;
    public static void main(String[] args) throws SQLException {
SessionFactoryImpl sessionFactory = (SessionFactoryImpl) getHibernateSessionFactory();
var session = sessionFactory.getCurrentSession();
var statement = connection.createStatement();
Runtime.getRuntime().addShutdownHook(new Thread(sessionFactory::close));
var persistenceManager = new PersistenceManager(sessionFactory);
var authManager = new AuthorizationManager(sessionFactory,"pepper");

        var collMan = new CollectionManager(persistenceManager);
        var commandManager = initializeCommandManager(collMan, authManager);
        if(!collMan.validateAll()) {
            CustomConsole.printError("Невалидные продукты в загруженном файле!");
            System.exit(2);
        }
        try {
            var server = new UDPDatagramServer(InetAddress.getLocalHost(), port, new CommandExecutor(commandManager, authManager));
            statement.executeQuery("insert into aboba(name) values('eklfsef')");
            server.run();
        } catch (SocketException e) {
            CustomConsole.printError("Случилась ошибка сокета");
        } catch (UnknownHostException e) {
            CustomConsole.printError("Неизвестный хост");
        }}
    public static SessionFactory getHibernateSessionFactory(){
        loadEnv();
        var url = "jdbc:postgresql://176.59.4.135:4343/postgres";
        var user = "postgres";
        var password = "1234";
        if(url == null||url.isEmpty()||user == null || user.isEmpty()||password == null|| password.isEmpty()){
            System.out.println("в .env файле не обнаружены данные для подключения к базе данных");
            System.exit(1);}
        return HibernateUtil.getSessionFactory(url, user, password);
    }
        private static CommandManager initializeCommandManager(CollectionManager collMan, AuthorizationManager authManager){

       return new CommandManager() {{
           register("register",new Register(authManager));
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
        }};}
        private static void loadEnv(){
            var environmentFile = ".env.dev";
            var isProduction = System.getenv("VAR");
            if(isProduction !=null&&isProduction.equals("true")){
                environmentFile = ".env";
            }
            dotenv = Dotenv.configure()
                    .filename(environmentFile)
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        }


  }