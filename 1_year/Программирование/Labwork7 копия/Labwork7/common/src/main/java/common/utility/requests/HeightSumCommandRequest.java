package common.utility.requests;

import common.user.User;

public class HeightSumCommandRequest extends Request{
    public HeightSumCommandRequest(User user){
        super("heightSum", user);
    }
}
