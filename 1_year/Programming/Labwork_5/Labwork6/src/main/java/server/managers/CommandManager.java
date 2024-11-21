package server.managers;

import client.commands.Command;
import client.console.Console;

import java.util.*;

/**
 * Класс для создания и организации рабочих команд
 */
public class CommandManager {

        private final Map<String, Command> commands = new HashMap<>();

        /**
         * Добавляет команду.
         * @param commandName Название команды.
         * @param command Команда.
         */
        public void register(String commandName, Command command) {
            commands.put(commandName, command);
        }

        /**
         * @return Словарь команд.
         */
        public Map<String, Command> getCommands() {
            return commands;
        }
}
