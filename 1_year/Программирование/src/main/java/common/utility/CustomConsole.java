package common.utility;

import common.exceptions.ScriptRecursionException;
import server.CommandExecutor;
import server.managers.CommandManager;
import server.managers.PersonAdder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для работы с консолью
 */

public class CustomConsole {

    public static final String PS1 = "$ ";

    private ArrayDeque<String> array = new ArrayDeque<>();
    private final PersonAdder personAdder;
    private final Scanner userScanner;
    private CommandExecutor commandExecutor;
    public CustomConsole(PersonAdder personAdder,Scanner userScanner){
        this.personAdder = personAdder;
        this.userScanner= userScanner;
    }
    public ResponseResult scriptMode(String argument) {
        String[] userCommand;
        ResponseResult commandStatus;
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
                CustomConsole.printLn(CustomConsole.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : array) {
                        if (userCommand[1].equals(script))throw new ScriptRecursionException();
                    }
                }
                commandStatus = commandExecutor.executeCommand(userScanner.nextLine(), userScanner.nextLine(), userScanner.nextLine());
            } while (commandStatus == null && userScanner.hasNextLine());
            personAdder.setUserScanner(tmpScanner);
            personAdder.setUserMode();
            if (commandStatus != null && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                CustomConsole.printLn("Проверьте скрипт на корректность данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            CustomConsole.printError("Файл не найден!");
        }catch (ScriptRecursionException exception) {
            CustomConsole.printError("Скрипт не может быть рекурсивным!");
        } catch (NoSuchElementException exception) {
            CustomConsole.printError("Файл скрипта пустой!");
        } catch (IllegalStateException exception) {
            CustomConsole.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            array.remove(array.size()-1);
        }
        return ResponseResult.OK;
    }

    /**
     * Функция для приёма и работы с вводом пользователя
     */
    public void interactiveMode() {
        String[] userCommand;
        ResponseResult commandStatus;
        try {
            do {
                CustomConsole.print(PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = commandExecutor.executeCommand(userScanner.nextLine(), userScanner.nextLine(), userScanner.nextLine());
            } while (commandStatus != ResponseResult.OK);
        } catch (NoSuchElementException exception) {
            CustomConsole.printError("Ввод пользователя не найден!");
        } catch (IllegalStateException exception) {
            CustomConsole.printError("Непредвиденная ошибка!");
        }
    }


    /**
     * Функция для исполнения скрипта
     * @param argument название файла, в котором находится скрипт
     * @return возвращает значение статуса команды
     */


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

}
