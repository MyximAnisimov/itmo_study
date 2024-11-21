package server.managers;

import client.commands.Command;
import common.request.Request;
import org.gradle.internal.impldep.org.apache.commons.lang.SerializationUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandExecuteManager {
    private Map<String, Command> command;
private CommandManager manager;
    public CommandExecuteManager(CommandManager manager){
        this.manager=manager;
    }
    public Object commandExecute(Request request){
       command = manager.getCommands();
        return command.get(request.getName());
    }
}
