package common.utility.response;

import common.user.User;

public class RegisterResponse extends Response{
    public final User user;

    public RegisterResponse(User user, String error) {
        super("register", error);
        this.user = user;
    }
}
