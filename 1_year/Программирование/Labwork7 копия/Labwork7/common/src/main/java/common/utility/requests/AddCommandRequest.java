package common.utility.requests;

import common.collections.Person;
import common.user.User;

public class AddCommandRequest extends Request{
    public final Person person;
    public AddCommandRequest(Person person, User user){
        super("add",user);
        this.person=person;
    }
}
