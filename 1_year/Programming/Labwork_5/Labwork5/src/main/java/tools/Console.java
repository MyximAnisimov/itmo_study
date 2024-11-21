package tools;

import collections.Person;
import com.sun.jdi.ArrayReference;
import exceptions.ScriptRecursionException;
import managers.CommandManager;
import managers.PersonAdder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс для работы с консолью
 */

public class Console {

    public static final String PS1 = "$ ";

    private final CommandManager commandManager;
    private ArrayDeque<String> array = new ArrayDeque<>();
    private final PersonAdder personAdder;
    private final Scanner userScanner;
    public Console(CommandManager commandManager, Scanner userScanner, PersonAdder personAdder) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
this.personAdder=personAdder;
    }

    /**
     * Функция для исполнения скрипта
     * @param argument название файла, в котором находится скрипт
     * @return возвращает значение статуса команды
     */
    public int scriptMode(String argument) {
        String[] userCommand;
        int commandStatus;
        array.add(argument);
        try (Scanner userScanner = new Scanner(new File(argument))) {
            if (!userScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = personAdder.getUserScanner();
            personAdder.setUserScanner(userScanner);
            personAdder.setScriptMode();
            do {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (userScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.printLn(Console.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : array) {
                        if (userCommand[1].equals(script))throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == 0 && userScanner.hasNextLine());
            personAdder.setUserScanner(tmpScanner);
            personAdder.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.printLn("Проверьте скрипт на корректность данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printError("Файл не найден!");
        }catch (ScriptRecursionException exception) {
            Console.printError("Скрипт не может быть рекурсивным!");
        } catch (NoSuchElementException exception) {
            Console.printError("Файл скрипта пустой!");
        } catch (IllegalStateException exception) {
            Console.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            array.remove(array.size()-1);
        }
        return 1;
    }

    /**
     * Функция для приёма и работы с вводом пользователя
     */
    public void interactiveMode() {
        String[] userCommand;
        int commandStatus;
        try {
            do {
                Console.print(PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printError("Ввод пользователя не найден!");
        } catch (IllegalStateException exception) {
            Console.printError("Непредвиденная ошибка!");
        }
    }

    /**
     * Выводит принятый аргумент в стандартный поток вывода
     * @param toOut информация, которую необходимо вывести
     */
    public static void print(Object toOut){
        System.out.print(toOut);
    }

    /**
     * Выводит принятый аргумент на консоль
     * @param toOut информация, которую необходимо вывести
     */
    public static void printLn(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Выводит ошибку на консоль
     *@param toOut ошибка, которую необходимо вывести
     */
    public static void printError(Object toOut) {
        System.out.println("Ошибка: " + toOut);
    }

    /**
     * Функция для приёма ввода пользователя и исполнения команды (Если таковая существует)
     * @param userCommand ввод имени команды пользователем
     * @return статус команды
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "head":
                if(!commandManager.head(userCommand[1])) return  1;
                break;
            case "remove_first":
                if(!commandManager.remove_first(userCommand[1])) return 1;
                break;
            case "exit":
                if(!commandManager.exit(userCommand[1])) return 1;
                break;
            case "height_sum":
                if(!commandManager.heightSum(userCommand[1])) return 1;
                break;
                case "print_field_descending_passport_id":
                if(!commandManager.print_field_descending_passport_idCommand(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if(!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "save":
                if(!commandManager.save(userCommand[1])) return 1;
                break;
            case "count_less_than_location":
                if(!commandManager.count_less_than_location(userCommand[1])) return 1;
                break;
            case "clear":
                if(commandManager.clear(userCommand[1])) return 1;
                break;
            case "help":
                if(!commandManager.help(userCommand[1])) return 1;
                break;
            case "execute_script":
                if(!commandManager.execute_script(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "remove_by_id":
                if(!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "update_by_id":
                if(!commandManager.updateById(userCommand[1])) return 1;
                break;
default:
                if (!commandManager.noCommand(userCommand[0])) return 1;
}
        return 0;
    }

}
