package server;

import common.utility.Request;
import common.utility.Response;
import common.utility.ResponseResult;
import server.managers.CommandManager;
import server.utility.ResponseOutputer;

import java.io.Serializable;

public class CommandExecutor {
private CommandManager commandManager;
public CommandExecutor(CommandManager manager){
    this.commandManager= manager;
}
    public Response executeCommand(Request request){
        ResponseResult responseResult = executeCommand(
                request.getCommandName(),
                request.getCommandStringArgument(),
                request.getCommandObjectArgument());
        return new Response(responseResult, ResponseOutputer.clearBuffer());
    }
    public ResponseResult executeCommand(String command, String commandStringArgument, Serializable commandObjectArgument) {
        switch (command) {
            case "":
                break;
            case "info":
                if (!commandManager.info(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "show":
                if (!commandManager.show(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "add":
                if (!commandManager.add(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "head":
                if(!commandManager.head(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "remove_first":
                if(!commandManager.remove_first(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "exit":
                if(!commandManager.exit(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "height_sum":
                if(!commandManager.heightSum(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "print_field_descending_passport_id":
                if(!commandManager.print_field_descending_passport_idCommand(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "remove_greater":
                if(!commandManager.removeGreater(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "save":
                if(!commandManager.save(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "count_less_than_location":
                if(!commandManager.count_less_than_location(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "clear":
                if(commandManager.clear(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "help":
                if(!commandManager.help(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "execute_script":
                if(!commandManager.execute_script(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "remove_by_id":
                if(!commandManager.removeById(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "update_by_id":
                if(!commandManager.updateById(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            default:
                if (!commandManager.noCommand(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
        }
        return ResponseResult.OK;
    }
}
