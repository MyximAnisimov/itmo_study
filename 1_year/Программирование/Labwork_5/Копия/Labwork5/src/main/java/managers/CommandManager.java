package managers;

import commands.Command;
import tools.Console;

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
private final Command removeById;
private final Command updateById;
    public CommandManager(Command addCommand, Command infoCommand, Command showCommand, Command headCommand, Command removeFirstCommand,  Command exitCommand, Command heightSumCommand, Command print_field_descending_passport_idCommand, Command removeGreater, Command saveCommand, Command count_less_than_location, Command execute_script, Command helpCommand, Command clearCommand, Command removeById, Command updateById) {
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
this.removeById=removeById;
this.updateById=updateById;
        commands = new ArrayList<>(Arrays.asList(addCommand, infoCommand, showCommand, headCommand, removeFirstCommand,  exitCommand, heightSumCommand, print_field_descending_passport_idCommand, removeGreater, saveCommand, count_less_than_location, execute_script, helpCommand, clearCommand, removeById, updateById));
    }

    /**
     * Выводит информацию о коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean info(String argument) {
        infoCommand.execute(argument);
        return true;
    }
    /**
     * Выводит на экран все элементы коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean show(String argument) {
        showCommand.execute(argument);
        return true;
    }
    /**
     * Добавляет элемент в коллекцию
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean add(String argument) {

        return addCommand.execute(argument);
    }
    /**
     * Выводит первый элемент коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean head(String argument) {
        return headCommand.execute(argument);
    }
    /**
     * Удаляет первый элмемент коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean remove_first(String argument) {
        return removeFirstCommand.execute(argument);
    }
    /**
     * Производит выход из программы
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean exit(String argument){
        return exitCommand.execute(argument);
    }
    /**
     * Подсчитывает сумму поля height всех элементов коллекции
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean heightSum(String argument){
        return heightSumCommand.execute(argument);
    }

    /**
     * Выводит элементы коллекции, отсортированными по полю passportID в порядке убывания
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean print_field_descending_passport_idCommand(String argument){
        return print_field_descending_passport_idCommand.execute(argument);
    }

    /**
     * Удаляет элементы коллекции, значение поля height которых превышает введённое пользователем число
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean removeGreater(String argument){
        return removeGreater.execute(argument);
    }
    /**
     * Сохраняет коллекцию в файл
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean save(String argument){
        return saveCommand.execute(argument);
    }
    /**
     * Подсчитывает количество элементов коллекции,
     * у которых поле name класса location меньше, чем введённое пользователем с клавиатуры
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean count_less_than_location(String argument){
        return count_less_than_location.execute(argument);
    }
    /**
     * Выводит справку по командам
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean help(String argument){
        if (!helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printLn(command.getName() + " - " + command.getDescription());
            }
            return true;
        } else return false;
    }
    /**
     * Выводит сообщение о том, что введённой команды нет
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean noCommand(String argument) {
        Console.printLn("Команда '" + argument + " 'не найдена.");
        return false;
    }
    /**
     * Исполняет скрипт из файла
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean execute_script(String argument){
        return execute_script.execute(argument);
    }
    /**
     * Очищает коллекцию
     * @param argument аргумент команды
     * @return Успешность выполнения команды.
     */
    public boolean clear(String argument){
        return clearCommand.execute(argument);
    }
public boolean removeById(String argument) {
        return removeById.execute(argument);
}
public boolean updateById(String argument){
        return updateById.execute(argument);
}
}
