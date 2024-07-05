package server.commands;

import common.utility.requests.RegisterRequest;
import common.utility.requests.Request;
import common.utility.response.RegisterResponse;
import common.utility.response.Response;
import org.postgresql.util.PSQLException;
import server.managers.AuthorizationManager;

public class Register extends AbstractCommand{
    private final AuthorizationManager authManager;
    private final int MAX_USERNAME_LENGTH = 40;

    public Register(AuthorizationManager authManager) {
        super("register", "зарегистрировать пользователя");
        this.authManager = authManager;
    }

    /**
     * Выполняет команду
     * @param request Запрос к серверу.
     * @return Ответ сервера.
     */
    @Override
    public Response execute(Request request) {
        var req = (RegisterRequest) request;
        var user = req.getUser();
        if (user.getName().length() >= MAX_USERNAME_LENGTH) {
            return new RegisterResponse(user, "Длина имени пользователя должна быть < " + MAX_USERNAME_LENGTH);
        }

        try {
            var newUserId = authManager.userReg(user.getName(), user.getPassword());

            if (newUserId <= 0) {
                return new RegisterResponse(user, "Не удалось создать пользователя.");
            } else {
                return new RegisterResponse(user.copy(newUserId), null);
            }
        } catch (PSQLException e) {
            var message = "Ошибка PostgreSQL: " + e.getMessage();
            if (e.getMessage().contains("duplicate key value violates unique constraint \"users_name_key\"")) {
                message = "Неуникальное имя пользователя! Попробуйте другое.";
            }
            return new RegisterResponse(user, message);
        } catch (Exception e) {
            return new RegisterResponse(user, e.toString());
        }
    }
}
