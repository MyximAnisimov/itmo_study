package client.utility;

import common.exceptions.*;
import common.utility.CustomConsole;
import common.utility.RawPersonCollection;
import common.utility.Request;
import common.utility.ResponseResult;
import org.apache.tools.ant.taskdefs.Local;
import server.managers.PersonAdder;
import common.collections.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserHandler {
    private Scanner userScanner;
    private final ArrayDeque<File> scriptQueue = new ArrayDeque<>();
    private final ArrayDeque<Scanner> scannerQueue = new ArrayDeque<>();

    public UserHandler(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Receives user input.
     *
     * @param serverResponseResult Last server's response code.
     * @return New request to server.
     */
    public Request handle(ResponseResult serverResponseResult) {
        String userInput;
        String[] userCommand;
        ProcessingResult processingResult;
        int rewriteAttempts = 0;
        try{
            do{
                try{
                    if(fileMode() && (serverResponseResult == ResponseResult.ERROR ||
                            serverResponseResult == ResponseResult.SERVER_EXIT))
                        throw new IncorrectInputInScriptException();
                    while(fileMode() && !userScanner.hasNextLine()){
                        userScanner.close();
                        userScanner = scannerQueue.pop();
                        CustomConsole.printLn("Going back to the script '" + scriptQueue.pop().getName() + "'...");
                    }
                    if(fileMode()){
                        userInput = userScanner.nextLine();
                        if(!userInput.isEmpty()){
                            CustomConsole.printLn(userInput);
                        }
                    } else {
                        userInput = userScanner.nextLine();
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    CustomConsole.printError("An error occurred while entering the command!");
                    userCommand = new String[]{"", ""};
                    rewriteAttempts++;
                    int maxRewriteAttempts = 3;
                    if (rewriteAttempts >= maxRewriteAttempts) {
                        CustomConsole.printError("Exceeded the number of input attempts!");
                        System.exit(0);
                    }
                }
                processingResult = processCommand(userCommand[0], userCommand[1]);
            } while (processingResult == ProcessingResult.ERROR && !fileMode() || userCommand[0].isEmpty());
            try{
                if(fileMode() && (serverResponseResult == ResponseResult.ERROR || processingResult == ProcessingResult.ERROR))
                    throw new IncorrectInputInScriptException();
                switch (processingResult){
                    case OBJECT:
                        RawPersonCollection personAddRaw = generateOrganizationAdd();
                        return new Request(userCommand[0], userCommand[1], personAddRaw);
                    case UPDATE_OBJECT:
                        RawPersonCollection personUpdateRaw = generateOrganizationUpdate();
                        return new Request(userCommand[0], userCommand[1], personUpdateRaw);
                    case SCRIPT:
                        File scriptFile = new File(userCommand[1]);
                        if(!scriptFile.exists()) throw new FileNotFoundException();
                        if(!scriptQueue.isEmpty())
                            throw new ScriptRecursionException();
                        scannerQueue.push(userScanner);
                        scriptQueue.push(scriptFile);
                        userScanner = new Scanner(scriptFile);
                        CustomConsole.printLn("Execute script '" + scriptFile.getName() + "'...");
                        break;
                }
            } catch (FileNotFoundException exception){
                CustomConsole.printError("The script file was not found!");
            }
            catch (OutOfLimitsException exception){
                CustomConsole.printLn("Выход за лимит");
            }
            catch (WrongDateFormatException exception){
                CustomConsole.printLn("Неправильно написана дата");
            }
            catch (UnknownCountryException exception){
                CustomConsole.printLn("Такой страны нет в списке");
            }
            catch (ScriptRecursionException exception){
                CustomConsole.printError("Scripts cannot be called recursively!");
                throw new IncorrectInputInScriptException();
            }
        } catch (IncorrectInputInScriptException exception){
            CustomConsole.printError("Script execution aborted!");
            while (!scannerQueue.isEmpty()) {
                userScanner.close();
                userScanner = scannerQueue.pop();
            }
            scriptQueue.clear();
            return new Request();
        }
        return new Request(userCommand[0], userCommand[1]);
    }

    /**
     * Processes the entered command.
     *
     * @return Status of code.
     */
    private ProcessingResult processCommand(String command, String commandArgument) {
        try{
            switch (command){
                case "":
                    return ProcessingResult.ERROR;
                case "add":
                case "add_if_max":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException("{element}");
                    return ProcessingResult.OBJECT;
                case "clear":
                case "exit":
                case "help":
                case "head":
                case "info":
                case "print_ascending":
                case "print_descending":
                case "print_field_descending":
                case "save":
                case "server_exit":
                case "show":
                case "shuffle":
                    if (!commandArgument.isEmpty()) throw new CommandUsageException();
                    break;
                case "execute_script":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<file_name>");
                    return ProcessingResult.SCRIPT;
                case "remove_at":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<Position>");
                    break;
                case "remove_by_id":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<ID>");
                    break;
                case "update":
                    if (commandArgument.isEmpty()) throw new CommandUsageException("<ID> {element}");
                    return ProcessingResult.UPDATE_OBJECT;
                default:
                    CustomConsole.printLn("Command '" + command + "' was not found. Type 'help' for help.");
                    return ProcessingResult.ERROR;
            }
        } catch (CommandUsageException exception){
            if (exception.getMessage() != null) command += " " + exception.getMessage();
            CustomConsole.printLn("Usage: '" + command + "'");
            return ProcessingResult.ERROR;
        }
        return ProcessingResult.OK;
    }

    /**
     * Generates organization to add.
     *
     * @return Organization to add.
     * @throws IncorrectInputInScriptException When something went wrong in script.
     */
    private RawPersonCollection generateOrganizationAdd() throws IncorrectInputInScriptException, OutOfLimitsException, WrongDateFormatException, UnknownCountryException {
        PersonAdder personAsker = new PersonAdder(userScanner);
        if (fileMode()) personAsker.setScriptMode();
        return new RawPersonCollection(
                personAsker.setName(userScanner),
                personAsker.setCoordinates(userScanner),
                personAsker.setCreationDate(),
                personAsker.setHeight(userScanner),
                personAsker.setBirthdayDate(userScanner),
                personAsker.setPassportID(userScanner),
                personAsker.setNationality(userScanner),
                personAsker.setLocation(userScanner)
        );
    }

    /**
     * Generates organization to update.
     *
     * @return Organization to update.
     * @throws IncorrectInputInScriptException When something went wrong in script.
     */
    private RawPersonCollection generateOrganizationUpdate() throws IncorrectInputInScriptException, OutOfLimitsException, WrongDateFormatException, UnknownCountryException{
        PersonAdder personAsker = new PersonAdder(userScanner);
        if (fileMode()) personAsker.setScriptMode();
        String name = personAsker.askQuestion("Do you want to change the organization's name?") ?
        personAsker.setName(userScanner) : null;
        Coordinates coordinates = personAsker.askQuestion("Do you want to change the coordinates of the organization?") ?
                personAsker.setCoordinates(userScanner) : null;
        LocalDate creationDate = personAsker.askQuestion("Want to change the organization's annual turnover?") ?
                personAsker.setCreationDate() : null;
        Integer height= personAsker.askQuestion("Do you want to change the number of employees in the organization?") ?
                personAsker.setHeight(userScanner) : -1;
        LocalDate birthday = personAsker.askQuestion("Do you want to change the type of organization?") ?
                personAsker.setBirthdayDate(userScanner) : null;
        String passportID = personAsker.askQuestion("Do you want to change the address of the organization?") ?
                personAsker.setPassportID(userScanner) : null;
        Country nationality = personAsker.askQuestion("Do you want to change the address of the organization?") ?
                personAsker.setNationality(userScanner) : null;
        Location location = personAsker.askQuestion("Do you want to change the address of the organization?") ?
                personAsker.setLocation(userScanner) : null;
        return new RawPersonCollection(
                name,
                coordinates,
                creationDate,
                height,
                birthday,
                passportID, nationality, location
        );
    }

    /**
     * Checks if UserHandler is in file mode now.
     *
     * @return Is UserHandler in file mode now boolean.
     */
    private boolean fileMode() {
        return !scannerQueue.isEmpty();
    }
}

