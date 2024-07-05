package common.utility.requests;

import common.user.User;

public class InfoCommandRequest extends Request{
    public InfoCommandRequest(User user){
        super("info", user);
    }
}
