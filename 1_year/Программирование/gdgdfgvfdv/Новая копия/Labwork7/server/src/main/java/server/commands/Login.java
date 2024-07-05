package server.commands;

import common.utility.*;
import common.utility.requests.LoginCommandRequest;
import common.utility.requests.Request;
import common.utility.response.LoginCommandResponse;
import common.utility.response.Response;
import server.CollectionManager;
import server.CommandExecutor;
import server.DBhandler;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static server.Main.logger;

public class Login extends AbstractCommand{
    private final CollectionManager collMan;
public VisibilityArgument VA;
    public Login(CollectionManager collMan) {
        super("login", "вывести информацию о коллекции", Visibility.ALL_USERS);
        this.collMan=collMan;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        var req = (LoginCommandRequest) request;
        var response =new LoginCommandResponse(req.user, req.password);
      try{
            DBhandler dBhandler = new DBhandler();
            Tokenizer tokenizer = new Tokenizer();
            int connected_user_id = dBhandler.checkUser(req.user, req.password);
            if (connected_user_id != -1) {
                var token = tokenizer.SHA384(req.user + req.password + Date.from(Instant.parse("1970-01-01T10:15:00.00Z")));
                CommandExecutor.token.add(token);
                response.SuccLogin = true;
                response.token = token;
                VA.setGlobalArgumentTrue();
CustomConsole.printLn("Пользователь найден!");
            } else {
                response.SuccLogin = false;
                 logger.info("Неправильный логин или пароль");
            }
        return response;
        }
        catch(NoSuchAlgorithmException e){
            CustomConsole.printLn("Ошибка алгоритма");
        }
        catch(SQLException e) {
            CustomConsole.printLn("Ошибка скуэль");
        }
        catch (NumberFormatException exception) {
            CustomConsole.printError("ID должен быть представлен числом!");
        }
        return null;
    }
}
