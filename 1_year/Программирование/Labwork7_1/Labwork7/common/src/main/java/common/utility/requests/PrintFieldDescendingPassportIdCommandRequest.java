package common.utility.requests;

import common.user.User;

public class PrintFieldDescendingPassportIdCommandRequest extends Request{
    public PrintFieldDescendingPassportIdCommandRequest(User user){
        super("print_field_descending_passport_id", user);
    }
}
