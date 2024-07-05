package common.utility.requests;

import common.user.User;

public class HelpCommandRequest extends Request {
    public HelpCommandRequest(User user){
        super("help", user);
    }
}
