package common.utility.requests;

import common.collections.Person;

public class UpdateByIdCommandRequest extends Request{
    public final Person updatedPerson;
    public final int person_id;
    public UpdateByIdCommandRequest(Person updatedPerson, int person_id){
        super("update_by_id");
        this.person_id=person_id;
        this.updatedPerson=updatedPerson;
    }
}
