//package server.commands;
//
//import common.utility.Packet;
//import common.utility.Visibility;
//import common.utility.requests.LoginCommandRequest;
//import common.utility.requests.Request;
//import common.utility.response.Response;
//import server.CollectionManager;
//import server.CommandExecutor;
//import server.managers.CommandManager;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//public class Checkout {
//    private String name;
//    private final CollectionManager collMan;
//    private CommandManager comMan;
//
//    public Checkout(String name, CollectionManager collMan, Visibility visibility) {
//       this.name=name;
//       visibility = Visibility.ALL_USERS;
//        this.collMan=collMan;
//    }
//    public Packet execute(ArrayList<Visibility>,) {
//var ans = new Packet("clear_clients_command");
//
//       var existingCommands = comMan.getCommands();
//       ArrayList<Command> filteredCommand=new ArrayList<>();
//for(int i=0; i<existingCommands.size();i++){
//  if(existingCommands.get(getName()).getVisibility()==Visibility.ALL_USERS){
//      filteredCommand.add(existingCommands.get(getName()));
//      ans.wrapIntoArray().add(new Packet("add_client_command"));
//  }
//
//}
//
//}
