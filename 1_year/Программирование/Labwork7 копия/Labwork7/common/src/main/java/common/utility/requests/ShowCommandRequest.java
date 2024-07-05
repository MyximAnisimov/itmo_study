package common.utility.requests;

import common.user.User;

public class ShowCommandRequest extends Request{
    public ShowCommandRequest(User user){
        super("show", user);
    }
}
