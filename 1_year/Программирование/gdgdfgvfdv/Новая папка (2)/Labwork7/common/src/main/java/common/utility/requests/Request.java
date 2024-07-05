package common.utility.requests;


import common.utility.VisibilityArgument;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    public int user_id;
    public VisibilityArgument VA;

    public Request(String commandName) {
        this.commandName = commandName;
    }
    public String getName() {
        return commandName;
    }
}
