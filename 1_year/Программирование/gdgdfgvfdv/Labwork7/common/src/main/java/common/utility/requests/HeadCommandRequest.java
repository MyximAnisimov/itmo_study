package common.utility.requests;

import common.user.User;

public class HeadCommandRequest extends Request{
    public HeadCommandRequest(User user){
        super("head", user);
    }
}
