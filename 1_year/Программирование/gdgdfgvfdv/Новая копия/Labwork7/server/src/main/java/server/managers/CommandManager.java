package server.managers;

import common.utility.Packet;
import server.commands.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для создания и организации рабочих команд
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * @return Словарь команд.
     */
//    public ArrayList<Packet> getPacketCommands(ArrayList<Packet> lostOfPAckets, int user_id){
//        var ans = new ArrayList<Packet>();
//    }
    public Map<String, Command> getCommands() {
        return commands;
   }}
