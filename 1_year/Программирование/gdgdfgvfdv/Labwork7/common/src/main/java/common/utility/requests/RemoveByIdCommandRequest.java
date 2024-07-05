package common.utility.requests;

import common.user.User;

public class RemoveByIdCommandRequest extends Request{
    public final int id;
    public RemoveByIdCommandRequest(int id, User user){
        super("remove_by_id", user);
        this.id=id;
    }
}
