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


public class Login extends AbstractCommand{
    private int user_id;
    private UDPserver server;
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
int count = 0;
      try{
          CommandExecutor CE = new CommandExecutor(new CommandManager());
            DBhandler dBhandler = new DBhandler();
            Tokenizer tokenizer = new Tokenizer();
            int connected_user_id = dBhandler.checkUser(req.user, req.password);
          int user_id = dBhandler.getID(req.user);
            req.setUser(user_id);
          req.setUserId(user_id);


            if (connected_user_id != -1) {

                UDPserver.user_id.setGlobalUserId(user_id);
                if(UDPserver.user_id.globalUserId==UDPserver.user_id.globalUserId2) {
                    CustomConsole.printLn("Вы пытаетесь дважды зайти на один аккаунт!");
                }

                var token = tokenizer.SHA384(req.user + req.password + Date.from(Instant.parse("1970-01-01T10:15:00.00Z")));
                CommandExecutor.token.add(token);
                count++;
                UDPserver.user_id.setCountable(count);
                req.setCount(count);
                var response =new LoginCommandResponse(req.user, req.password, req.getUserId(),req.count,"ОШибка");
                response.setSuccLogin(true);

Response.ID.countable = UDPserver.user_id.countable;
                response.token = token;
                    CustomConsole.printLn("Пользователь найден!");
                    return response;}


            else {
                var response =new LoginCommandResponse(req.user, req.password, 0,req.count,"ОШибка");
                response.SuccLogin = false;
                CustomConsole.printLn("Неправильный логин или пароль");
                 return response;}
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
