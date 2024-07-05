package server.commands;

import common.utility.*;
import common.utility.requests.LoginCommandRequest;
import common.utility.requests.Request;
import common.utility.response.LoginCommandResponse;
import common.utility.response.Response;
import server.*;
import server.managers.CommandManager;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import static server.Main.logger;

public class Login extends AbstractCommand{
    private int user_id;
    public Login() {
        super("login", "вывести информацию о коллекции", Visibility.ALL_USERS);
    }
    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        var req = (LoginCommandRequest) request;

      try{
          CommandExecutor CE = new CommandExecutor(new CommandManager());
            DBhandler dBhandler = new DBhandler();
            Tokenizer tokenizer = new Tokenizer();
            int connected_user_id = dBhandler.checkUser(req.user, req.password);
            req.setUser(connected_user_id);
            if (connected_user_id != -1) {
                var token = tokenizer.SHA384(req.user + req.password + Date.from(Instant.parse("1970-01-01T10:15:00.00Z")));
                CommandExecutor.token.add(token);
                var response =new LoginCommandResponse(req.user, req.password, connected_user_id,"ОШибка");
                response.setSuccLogin(true);
                response.token = token;
CustomConsole.printLn("Пользователь найден!");
return response;
            } else {
                var response =new LoginCommandResponse(req.user, req.password, 0,"ОШибка");
                response.SuccLogin = false;
                 logger.info("Неправильный логин или пароль");
            }
        return null;
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
