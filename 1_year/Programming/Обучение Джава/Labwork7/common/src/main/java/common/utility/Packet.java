package common.utility;

import java.util.ArrayList;

public class Packet {
   private ArrayList<Packet> arguments;
private String commandName;
    private String token = "unlogged_user";
    public Packet(String commandName){
this.commandName=commandName;

    }
    public ArrayList<Packet> wrapIntoArray(){
return new ArrayList<Packet>();
    }
}
