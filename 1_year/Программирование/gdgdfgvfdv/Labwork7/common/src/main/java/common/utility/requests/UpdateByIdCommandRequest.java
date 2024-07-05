package common.utility.requests;

import common.collections.Person;
import common.user.User;

public class UpdateByIdCommandRequest extends Request{
    public final int id;
    public final Person updatedPerson;
    public UpdateByIdCommandRequest(int id, Person updatedPerson, User user){
        super("update_by_id", user);
        this.id=id;
        this.updatedPerson=updatedPerson;
    }
}
