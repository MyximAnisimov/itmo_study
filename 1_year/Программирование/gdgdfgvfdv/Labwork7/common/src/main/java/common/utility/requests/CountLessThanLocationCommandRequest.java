package common.utility.requests;

import common.user.User;

public class CountLessThanLocationCommandRequest extends Request{
    public String locationName;
    public CountLessThanLocationCommandRequest(String locationName, User user){
        super("count_less_than_location", user);
        this.locationName=locationName;
    }
}
