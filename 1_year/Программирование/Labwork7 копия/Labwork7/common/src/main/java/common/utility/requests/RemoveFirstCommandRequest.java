package common.utility.requests;

import common.user.User;

public class RemoveFirstCommandRequest extends Request{
    public RemoveFirstCommandRequest(User user){
        super("remove_first", user);
    }
}
