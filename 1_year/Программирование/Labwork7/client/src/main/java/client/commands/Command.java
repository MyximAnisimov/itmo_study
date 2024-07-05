package client.commands;

import common.utility.UserIDNumber;

/**
 * Интерфейс для создание всех команд
 */
public interface Command {
    public String getName();
    public String getDescription();
    public UserIDNumber getUID();
public abstract boolean execute(String [] userCommand);
}
