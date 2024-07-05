package common.utility.requests;

import common.user.User;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private final String name;
    private final User user;

    public Request(String name, User user) {
        this.name = name;
        this.user = user;
    }
    public boolean isAuth() {
        return false;
    }
    public User getUser() {
        return user;
    }
    public String getName() {
        return commandName;
    }
}
