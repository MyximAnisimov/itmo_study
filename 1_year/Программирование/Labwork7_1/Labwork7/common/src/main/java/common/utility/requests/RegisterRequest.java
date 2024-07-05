package common.utility.requests;

import common.user.User;

public class RegisterRequest extends Request {
    public RegisterRequest(User user) {
        super("register", user);
    }

    @Override
    public boolean isAuth() {
        return true;
    }
}
