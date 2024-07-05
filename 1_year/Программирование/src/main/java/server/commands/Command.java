package server.commands;

import common.utility.Request;
import common.utility.Response;

import java.io.Serializable;

/**
 * Интерфейс для создание всех команд
 */
public interface Command {
    public String getName();
    public String getDescription();
public abstract boolean execute(String commandStringArgument, Serializable commandObjectArgument);
}
