package server.managers;

import server.commands.Command;
import common.utility.CustomConsole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания и организации рабочих команд
 */
public class CommandManager {
    private final List<Command> commands;
    private final Command addCommand;
    private final Command infoCommand;
    private final Command showCommand;
    private final Command headCommand;
    private final Command removeFirstCommand;
   private final Command exitCommand;
    private final Command heightSumCommand;
    private final Command print_field_descending_passport_idCommand;
private final Command removeGreater;
private final Command saveCommand;
private  final Command count_less_than_location;
private final Command execute_script;
private final Command helpCommand;
private final Command clearCommand;
private final Command removeByIdCommand;
private final Command updateById;
    public CommandManager(Command addCommand, Command infoCommand, Command showCommand, Command headCommand, Command removeFirstCommand,  Command exitCommand, Command heightSumCommand, Command print_field_descending_passport_idCommand, Command removeGreater, Command saveCommand, Command count_less_than_location, Command execute_script, Command helpCommand, Command clearCommand, Command removeByIdCommand, Command updateById) {
        this.addCommand = addCommand;
        this.showCommand = showCommand;
        this.infoCommand = infoCommand;
        this.headCommand = headCommand;
        this.removeFirstCommand = removeFirstCommand;
this.exitCommand=exitCommand;
this.heightSumCommand=heightSumCommand;
this.print_field_descending_passport_idCommand=print_field_descending_passport_idCommand;
this.removeGreater=removeGreater;
this.saveCommand=saveCommand;
this.count_less_than_location=count_less_than_location;
this.execute_script=execute_script;
this.helpCommand=helpCommand;
this.clearCommand=clearCommand;
this.removeByIdCommand=removeByIdCommand;
this.updateById=updateById;
        commands = new ArrayList<>(Arrays.asList(addCommand, infoCommand, showCommand, headCommand, removeFirstCommand,  exitCommand, heightSumCommand, print_field_descending_passport_idCommand, removeGreater, saveCommand, count_less_than_location, execute_script, helpCommand, clearCommand, removeByIdCommand, updateById));
    }

    /**
     * Выводит информацию о коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean info(String commandStringArgument, Serializable commandObjectArgument) {
        infoCommand.execute(commandStringArgument, commandObjectArgument);
        return true;
    }
    /**
     * Выводит на экран все элементы коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean show(String commandStringArgument, Serializable commandObjectArgument) {
        showCommand.execute(commandStringArgument, commandObjectArgument);
        return true;
    }
    /**
     * Добавляет элемент в коллекцию
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean add(String commandStringArgument, Serializable commandObjectArgument) {

        return addCommand.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Выводит первый элемент коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean head(String commandStringArgument, Serializable commandObjectArgument) {
        return headCommand.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Удаляет первый элмемент коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean remove_first(String commandStringArgument, Serializable commandObjectArgument) {
        return removeFirstCommand.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Производит выход из программы
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean exit(String commandStringArgument, Serializable commandObjectArgument){
        return exitCommand.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Подсчитывает сумму поля height всех элементов коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean heightSum(String commandStringArgument, Serializable commandObjectArgument){
        return heightSumCommand.execute(commandStringArgument, commandObjectArgument);
    }

    /**
     * Выводит элементы коллекции, отсортированными по полю passportID в порядке убывания
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean print_field_descending_passport_idCommand(String commandStringArgument, Serializable commandObjectArgument){
        return print_field_descending_passport_idCommand.execute(commandStringArgument, commandObjectArgument);
    }

    /**
     * Удаляет элементы коллекции, значение поля height которых превышает введённое пользователем число
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean removeGreater(String commandStringArgument, Serializable commandObjectArgument){
        return removeGreater.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Сохраняет коллекцию в файл
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean save(String commandStringArgument, Serializable commandObjectArgument){
        return saveCommand.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Подсчитывает количество элементов коллекции,
     * у которых поле name класса location меньше, чем введённое пользователем с клавиатуры
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean count_less_than_location(String commandStringArgument, Serializable commandObjectArgument){
        return count_less_than_location.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Выводит справку по командам
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean help(String commandStringArgument, Serializable commandObjectArgument){
        if (!helpCommand.execute(commandStringArgument, commandObjectArgument)) {
            for (Command command : commands) {
                CustomConsole.printLn(command.getName() + " - " + command.getDescription());
            }
            return true;
        } else return false;
    }
    /**
     * Выводит сообщение о том, что введённой команды нет
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean noCommand(String commandStringArgument, Serializable commandObjectArgument) {
        CustomConsole.printLn("Команда '" + commandStringArgument + " 'не найдена.");
        return false;
    }
    /**
     * Исполняет скрипт из файла
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean execute_script(String commandStringArgument, Serializable commandObjectArgument){
        return execute_script.execute(commandStringArgument, commandObjectArgument);
    }
    /**
     * Очищает коллекцию
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean clear(String commandStringArgument, Serializable commandObjectArgument){
        return clearCommand.execute(commandStringArgument, commandObjectArgument);
    }
public boolean removeById(String commandStringArgument, Serializable commandObjectArgument) {
        return removeByIdCommand.execute(commandStringArgument, commandObjectArgument);
}
public boolean updateById(String commandStringArgument, Serializable commandObjectArgument){
        return updateById.execute(commandStringArgument, commandObjectArgument);
}

public List<Command> getCommands(){
    return commands;
}}
