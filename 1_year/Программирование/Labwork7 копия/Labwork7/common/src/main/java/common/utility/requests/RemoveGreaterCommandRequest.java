package common.utility.requests;

import common.user.User;

public class RemoveGreaterCommandRequest extends Request{
    public final int height;
    public RemoveGreaterCommandRequest(int height, User user){
        super("removeGreater", user);
        this.height=height;
    }
}
