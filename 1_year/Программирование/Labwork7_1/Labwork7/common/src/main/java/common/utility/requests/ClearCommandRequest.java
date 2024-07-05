package common.utility.requests;

import common.user.User;

public class ClearCommandRequest extends Request{
    public ClearCommandRequest(User user){
        super("clear", user);
    }
}
