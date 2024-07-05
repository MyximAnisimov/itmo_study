package server;

import common.utility.requests.Request;
import common.utility.response.BadCredentialsResponse;
import common.utility.response.ErrorResponse;
import common.utility.response.Response;
import org.apache.logging.log4j.Logger;
import server.commands.Command;
import server.managers.AuthorizationManager;
import server.managers.CommandManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static server.Main.logger;

public class CommandExecutor {
private CommandManager commandManager;
    private final CommandManager manager;
    private final AuthorizationManager authManager;

    private final Logger logger = Main.logger;

    public CommandExecutor(CommandManager manager, AuthorizationManager authManager) {
        this.manager = manager;
        this.authManager = authManager;
    }
    public Response handle(Request request) {
        if (!request.isAuth()) {
            var user = request.getUser();
            try {
                if (user == null || authManager.userAuth(user.getName(), user.getPassword()) <= 0) {
                    return new BadCredentialsResponse("Неверные учетные данные. Пожалуйста, войдите в свой аккаунт.");
                }
            } catch (SQLException e) {
                logger.error("Невозможно выполнить запрос к БД о аутентификации пользователя.", e);
                return new ErrorResponse("sql_error", "Невозможно выполнить запрос к БД о аутентификации пользователя.");
            }
        }
        var command = commandManager.getCommands().get(request.getName());
        return command.execute(request);
}}

